from django.shortcuts import render, redirect
from django.contrib.auth import login as auth_login, logout as auth_logout
from .forms import CustomUserCreationForm, TrainOperatorLoginForm, AdminLoginForm
from django.contrib.auth.forms import AuthenticationForm
from django.contrib.auth import authenticate
from django.contrib.auth.decorators import login_required
from .models import TrainOperator, TrainJourney, Ticket, Administrator

# Create your views here.
def home(request):
    return render(request, 'ticket_management/home.html')

def train_journey(request):
    journeyList = TrainJourney.objects.order_by('date')
    journey_dict = {'journeys': journeyList}
    return render(request, 'ticket_management/journeys.html', context=journey_dict)

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
                    return redirect('home')
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
                    return redirect('home')
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