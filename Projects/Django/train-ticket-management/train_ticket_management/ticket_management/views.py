from django.shortcuts import render, redirect, get_object_or_404
from django.contrib.auth import login as auth_login, logout as auth_logout
from .forms import CustomUserCreationForm, TrainOperatorLoginForm, AdminLoginForm, JourneySearchForm, TrainJourneyForm, \
    TrainOperatorForm, TrainStationForm, TicketForm, CustomerForm, TrainJourneyAdminForm
from django.contrib.auth.forms import AuthenticationForm
from django.contrib.auth import authenticate
from django.contrib.auth.decorators import login_required
from .models import TrainStation, TrainOperator, TrainJourney, CustomUser, Ticket, Administrator


# Create your views here.
def home(request):
    return render(request, 'ticket_management/home.html')


def train_journey(request):
    form = JourneySearchForm(request.GET or None)
    journeys = TrainJourney.objects.all()

    if form.is_valid():
        origin_station = form.cleaned_data.get('origin_station')
        destination_station = form.cleaned_data.get('destination_station')
        date = form.cleaned_data.get('date')

        if origin_station:
            journeys = journeys.filter(origin_station__station_code__icontains=origin_station)
        if destination_station:
            journeys = journeys.filter(destination_station__station_code__icontains=destination_station)
        if date:
            journeys = journeys.filter(date=date)

    context = {
        'form': form,
        'journeys': journeys.order_by('date'),
    }

    return render(request, 'ticket_management/journeys.html', context)


def contact(request):
    return render(request, 'ticket_management/contact.html')


def register(request):
    if request.method == 'POST':
        form = CustomUserCreationForm(request.POST)
        if form.is_valid():
            user = form.save()
            auth_login(request, user)
            return redirect('home')
    else:
        form = CustomUserCreationForm()
    return render(request, 'ticket_management/register.html', {'form': form})


def custom_login(request):
    if request.method == 'POST':
        form = AuthenticationForm(request, data=request.POST)
        if form.is_valid():
            username = form.cleaned_data.get('username')
            password = form.cleaned_data.get('password')
            user = authenticate(username=username, password=password)
            if user is not None:
                auth_login(request, user)
                return redirect('account')
    else:
        form = AuthenticationForm()
    return render(request, 'ticket_management/login.html', {'form': form})


@login_required
def custom_logout(request):
    auth_logout(request)
    return redirect('home')


@login_required
def account_view(request):
    customer = request.user
    tickets = Ticket.objects.filter(custom_user=customer)
    context = {
        'customer': customer,
        'tickets': tickets,
    }
    return render(request, 'ticket_management/account.html', context)


def train_operator_login(request):
    if request.method == 'POST':
        form = TrainOperatorLoginForm(request.POST)
        if form.is_valid():
            username = form.cleaned_data.get('username')
            password = form.cleaned_data.get('password')
            try:
                operator = TrainOperator.objects.get(username=username)
                if operator.check_password(password):
                    request.session['operator_code'] = operator.operator_code
                    return redirect('operator_journeys')
                else:
                    form.add_error('password', 'Incorrect password')
            except TrainOperator.DoesNotExist:
                form.add_error('username', 'Username does not exist')
    else:
        form = TrainOperatorLoginForm()
    return render(request, 'ticket_management/train_operator_login.html', {'form': form})


def train_operator_logout(request):
    if 'operator_code' in request.session:
        del request.session['operator_code']
    return redirect('home')


def operator_journeys(request):
    operator_code = request.session.get('operator_code')
    operator = TrainOperator.objects.get(operator_code=operator_code)
    journeys = TrainJourney.objects.filter(train_operator=operator).order_by('date')

    form = JourneySearchForm(request.GET or None)
    if form.is_valid():
        origin_station = form.cleaned_data.get('origin_station')
        destination_station = form.cleaned_data.get('destination_station')
        date = form.cleaned_data.get('date')

        if origin_station:
            journeys = journeys.filter(origin_station__station_code__icontains=origin_station)
        if destination_station:
            journeys = journeys.filter(destination_station__station_code__icontains=destination_station)
        if date:
            journeys = journeys.filter(date=date)

    context = {
        'form': form,
        'journeys': journeys,
    }
    return render(request, 'ticket_management/operator_journeys.html', context)


def add_journey(request):
    operator_code = request.session.get('operator_code')
    train_operator = get_object_or_404(TrainOperator, operator_code=operator_code)
    if request.method == 'POST':
        form = TrainJourneyForm(request.POST)
        if form.is_valid():
            journey = form.save(commit=False)
            journey.train_operator = train_operator
            journey.save()
            return redirect('operator_journeys')
    else:
        form = TrainJourneyForm()
    return render(request, 'ticket_management/add-journey.html', {'form': form})


def edit_journey(request, journey_id):
    operator_code = request.session.get('operator_code')
    journey = get_object_or_404(TrainJourney, journey_id=journey_id)
    if request.method == 'POST':
        form = TrainJourneyForm(request.POST, instance=journey)
        if form.is_valid():
            form.save()
            return redirect('operator_journeys')
    else:
        form = TrainJourneyForm(instance=journey)
    return render(request, 'ticket_management/edit-journey.html', {'form': form, 'journey': journey})


def cancel_journey(request, journey_id):
    journey = get_object_or_404(TrainJourney, journey_id=journey_id)
    journey.journey_status = 'Canceled'
    journey.save()
    return redirect('operator_journeys')


def admin_login(request):
    if request.method == 'POST':
        form = AdminLoginForm(request.POST)
        if form.is_valid():
            username = form.cleaned_data.get('username')
            password = form.cleaned_data.get('password')
            try:
                administrator = Administrator.objects.get(username=username)
                if administrator.check_password(password):
                    request.session['admin_id'] = administrator.admin_id
                    return redirect('admin_page')
                else:
                    form.add_error('password', 'Incorrect password')
            except Administrator.DoesNotExist:
                form.add_error('username', 'Username does not exist')
    else:
        form = AdminLoginForm()
    return render(request, 'ticket_management/admin_login.html', {'form': form})


def admin_logout(request):
    if 'admin_id' in request.session:
        del request.session['admin_id']
    return redirect('home')


def admin_page(request):
    admin_id = request.session.get('admin_id')
    administrator = get_object_or_404(Administrator, admin_id=admin_id)
    # administrator = request.user
    train_operators = TrainOperator.objects.all()
    stations = TrainStation.objects.all()
    journeys = TrainJourney.objects.all()
    customers = CustomUser.objects.all()
    tickets = Ticket.objects.all()

    context = {
        'administrator': administrator,
        'train_operators': train_operators,
        'stations': stations,
        'journeys': journeys,
        'customers': customers,
        'tickets': tickets
    }

    return render(request, 'ticket_management/admin-page.html', context)


# Edit Train Operator
def admin_edit_train_operator(request, operator_code):
    operator = get_object_or_404(TrainOperator, operator_code=operator_code)
    if request.method == 'POST':
        form = TrainOperatorForm(request.POST, instance=operator)
        if form.is_valid():
            form.save()
            return redirect('admin_page')
    else:
        form = TrainOperatorForm(instance=operator)
    return render(request, 'admin-control/admin-edit-operator.html', {'form': form, 'operator': operator})


# Edit Train Station
def admin_edit_train_station(request, station_code):
    station = get_object_or_404(TrainStation, station_code=station_code)
    if request.method == 'POST':
        form = TrainStationForm(request.POST, instance=station)
        if form.is_valid():
            form.save()
            return redirect('admin_page')
    else:
        form = TrainStationForm(instance=station)
    return render(request, 'admin-control/admin-edit-station.html', {'form': form, 'station': station})


# Edit Train Journey
def admin_edit_journey(request, journey_id):
    journey = get_object_or_404(TrainJourney, journey_id=journey_id)
    if request.method == 'POST':
        form = TrainJourneyAdminForm(request.POST, instance=journey)
        if form.is_valid():
            form.save()
            return redirect('admin_page')
    else:
        form = TrainJourneyAdminForm(instance=journey)
    return render(request, 'admin-control/admin-edit-journey.html', {'form': form, 'journey': journey})


# Add Train Journey
def admin_add_journey(request):
    if request.method == 'POST':
        form = TrainJourneyAdminForm(request.POST)
        if form.is_valid():
            form.save()
            return redirect('admin_page')
    else:
        form = TrainJourneyAdminForm()
    return render(request, 'admin-control/admin-add-journey.html', {'form': form})


# Delete Train Journey
def admin_delete_journey(request, journey_id):
    journey = get_object_or_404(TrainJourney, journey_id=journey_id)
    if request.method == 'POST':
        journey.delete()
    return redirect('admin_page')

# Edit Customer
def admin_edit_customer(request, customer_id):
    customer = get_object_or_404(CustomUser, id=customer_id)
    if request.method == 'POST':
        form = CustomerForm(request.POST, instance=customer)
        if form.is_valid():
            form.save()
            return redirect('admin_page')
    else:
        form = CustomerForm(instance=customer)
    return render(request, 'admin-control/admin-edit-customer.html', {'form': form, 'customer': customer})


# Edit Ticket
def admin_edit_ticket(request, ticket_id):
    ticket = get_object_or_404(Ticket, ticket_id=ticket_id)
    if request.method == 'POST':
        form = TicketForm(request.POST, instance=ticket)
        if form.is_valid():
            form.save()
            return redirect('admin_page')
    else:
        form = TicketForm(instance=ticket)
    return render(request, 'admin-control/admin-edit-ticket.html', {'form': form, 'ticket': ticket})

# Add Train Journey
def admin_add_ticket(request):
    if request.method == 'POST':
        form = TicketForm(request.POST)
        if form.is_valid():
            form.save()
            return redirect('admin_page')
    else:
        form = TicketForm()
    return render(request, 'admin-control/admin-add-ticket.html', {'form': form})

# Delete Ticket
def admin_delete_ticket(request, ticket_id):
    ticket = get_object_or_404(Ticket, ticket_id=ticket_id)
    if request.method == 'POST':
        ticket.delete()
    return redirect('admin_page')