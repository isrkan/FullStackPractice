from django.shortcuts import render, redirect, get_object_or_404
from .forms import CustomUserCreationForm, TrainOperatorLoginForm, AdminLoginForm, JourneySearchForm, TrainJourneyForm, \
    TrainOperatorForm, TrainStationForm, TicketForm, CustomerForm, TrainJourneyAdminForm, CustomerUpdateForm, TicketUpdateForm
from django.contrib.auth.forms import AuthenticationForm
from django.contrib.auth import authenticate, login as auth_login, logout as auth_logout
from django.contrib.auth.mixins import LoginRequiredMixin
from .models import TrainStation, TrainOperator, TrainJourney, CustomUser, Ticket, Administrator
from django.views.generic import TemplateView, ListView, FormView, DetailView, UpdateView, CreateView
from django.views import View
from django.urls import reverse_lazy, reverse
from django.contrib import messages

class HomeView(TemplateView):  # Uses TemplateView to render a static page
    template_name = 'ticket_management/home.html'  # Specify the template to use

class ContactView(TemplateView):  # Uses TemplateView to render a static page
    template_name = 'ticket_management/contact.html'


class TrainJourneyListView(ListView):
    model = TrainJourney  # Specifies the model to be used in the view
    template_name = 'ticket_management/journeys.html'  # Defines the template to be used for rendering the view
    context_object_name = 'journeys'  # Specifies the context object name to be used in the template

    # Overrides the default queryset to include search filtering logic
    def get_queryset(self):
        queryset = super().get_queryset()  # Gets the base queryset from the superclass (all TrainJourney objects)
        form = JourneySearchForm(self.request.GET or None)  # Initializes the search form with GET data if present

        # Checks if the form data is valid
        if form.is_valid():
            # Retrieves cleaned data from the form
            origin_station = form.cleaned_data.get('origin_station')
            destination_station = form.cleaned_data.get('destination_station')
            date = form.cleaned_data.get('date')

            # Apply filters to the queryset based on form inputs
            if origin_station:
                queryset = queryset.filter(origin_station__station_code__icontains=origin_station)
            if destination_station:
                queryset = queryset.filter(destination_station__station_code__icontains=destination_station)
            if date:
                queryset = queryset.filter(date=date)

        return queryset.order_by('date')

    # Overrides the context data to include the search form in the template context
    def get_context_data(self, **kwargs):
        # Get the default context data from the parent class
        context = super().get_context_data(**kwargs)
        # Adds the search form to the context
        context['form'] = JourneySearchForm(self.request.GET or None)
        return context


class RegisterView(FormView):
    template_name = 'ticket_management/register.html'
    form_class = CustomUserCreationForm

    def form_valid(self, form):
        user = form.save()
        auth_login(self.request, user)
        return redirect('login')


class CustomLoginView(FormView):
    template_name = 'ticket_management/login.html'
    form_class = AuthenticationForm

    def form_valid(self, form):
        username = form.cleaned_data.get('username')
        password = form.cleaned_data.get('password')
        user = authenticate(username=username, password=password)
        if user is not None:
            auth_login(self.request, user)
            return redirect('account')
        else:
            return self.form_invalid(form)


class CustomLogoutView(LoginRequiredMixin, View):
    def get(self, request, *args, **kwargs):
        auth_logout(request)
        return redirect('home')


class AccountView(LoginRequiredMixin, DetailView):
    model = CustomUser
    template_name = 'ticket_management/account.html'
    context_object_name = 'customer'

    def get_object(self):
        return self.request.user

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        context['tickets'] = Ticket.objects.filter(custom_user=self.request.user)
        return context


class CustomerUpdateView(LoginRequiredMixin, UpdateView):
    model = CustomUser
    form_class = CustomerUpdateForm
    template_name = 'ticket_management/edit_customer.html'
    success_url = reverse_lazy('account')

    def get_object(self):
        return self.request.user

    def form_valid(self, form):
        form.save()
        return super().form_valid(form)



class TicketUpdateView(UpdateView):
    model = Ticket
    form_class = TicketUpdateForm
    template_name = 'ticket_management/edit_ticket.html'
    success_url = reverse_lazy('account')

    def get_object(self, queryset=None):
        return get_object_or_404(Ticket, ticket_id=self.kwargs['ticket_id'])

    def form_valid(self, form):
        form.save()
        return super().form_valid(form)


class CancelTicketView(View):
    def post(self, request, ticket_id):
        ticket = get_object_or_404(Ticket, ticket_id=ticket_id)
        journey = ticket.train_journey

        # Update the booking status to CANCELED
        ticket.booking_status = 'CANCELED'
        # Increment the remaining tickets for the flight
        journey.remaining_tickets += 1
        journey.save()
        # Save the updated ticket
        ticket.save()
        # Add a success message
        messages.success(request, "Ticket canceled successfully.")

        return redirect('account')



class TrainOperatorLoginView(FormView):
    template_name = 'ticket_management/train_operator_login.html'
    form_class = TrainOperatorLoginForm
    success_url = reverse_lazy('operator_journeys')

    def form_valid(self, form):
        username = form.cleaned_data.get('username')
        password = form.cleaned_data.get('password')
        try:
            operator = TrainOperator.objects.get(username=username)
            if operator.check_password(password):
                self.request.session['operator_code'] = operator.operator_code
                return super().form_valid(form)
            else:
                form.add_error('password', 'Incorrect password')
        except TrainOperator.DoesNotExist:
            form.add_error('username', 'Username does not exist')

        return self.form_invalid(form)


class TrainOperatorLogoutView(View):
    def get(self, request, *args, **kwargs):
        if 'operator_code' in request.session:
            del request.session['operator_code']
        return redirect('home')



class OperatorJourneysView(TemplateView):
    template_name = 'ticket_management/operator_journeys.html'

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        operator_code = self.request.session.get('operator_code')
        operator = get_object_or_404(TrainOperator, operator_code=operator_code)
        journeys = TrainJourney.objects.filter(train_operator=operator).order_by('date')
        context['journeys'] = journeys
        return context



class AddJourneyView(FormView):
    template_name = 'ticket_management/add-journey.html'
    form_class = TrainJourneyForm
    success_url = '/operator-journeys/'

    def get_form_kwargs(self):
        kwargs = super().get_form_kwargs()
        kwargs['initial'] = {'operator_code': self.request.session.get('operator_code')}
        return kwargs

    def form_valid(self, form):
        operator_code = self.request.session.get('operator_code')
        train_operator = get_object_or_404(TrainOperator, operator_code=operator_code)
        journey = form.save(commit=False)
        journey.train_operator = train_operator
        journey.save()
        return redirect(self.get_success_url())


class EditJourneyView(UpdateView):
    model = TrainJourney
    form_class = TrainJourneyForm
    template_name = 'ticket_management/edit-journey.html'
    context_object_name = 'journey'
    success_url = '/operator-journeys/'

    def get_object(self, queryset=None):
        journey_id = self.kwargs.get('journey_id')
        return get_object_or_404(TrainJourney, journey_id=journey_id)

    def form_valid(self, form):
        form.save()
        return redirect(self.get_success_url())



class CancelJourneyView(View):
    def post(self, request, journey_id):
        journey = get_object_or_404(TrainJourney, journey_id=journey_id)
        journey.journey_status = 'Canceled'
        journey.save()
        return redirect(reverse_lazy('operator_journeys'))


class AdminLoginView(View):
    def get(self, request):
        form = AdminLoginForm()
        return render(request, 'ticket_management/admin_login.html', {'form': form})

    def post(self, request):
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
        return render(request, 'ticket_management/admin_login.html', {'form': form})


class AdminLogoutView(View):
    def get(self, request, *args, **kwargs):
        if 'admin_id' in request.session:
            del request.session['admin_id']
        return redirect('home')



class AdminPageView(TemplateView):
    template_name = 'ticket_management/admin-page.html'

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        admin_id = self.request.session.get('admin_id')
        administrator = get_object_or_404(Administrator, admin_id=admin_id)
        context['administrator'] = administrator
        context['train_operators'] = TrainOperator.objects.all()
        context['stations'] = TrainStation.objects.all()
        context['journeys'] = TrainJourney.objects.all()
        context['customers'] = CustomUser.objects.all()
        context['tickets'] = Ticket.objects.all()
        return context




# Edit Train Operator
class AdminEditTrainOperatorView(UpdateView):
    model = TrainOperator
    form_class = TrainOperatorForm
    template_name = 'admin-control/admin-edit-operator.html'
    context_object_name = 'operator'

    def get_object(self, queryset=None):
        operator_code = self.kwargs.get('operator_code')
        return get_object_or_404(TrainOperator, operator_code=operator_code)

    def get_success_url(self):
        return reverse('admin_page')




# Edit Train Station
class AdminEditTrainStationView(UpdateView):
    model = TrainStation
    form_class = TrainStationForm
    template_name = 'admin-control/admin-edit-station.html'
    context_object_name = 'station'

    def get_object(self, queryset=None):
        station_code = self.kwargs.get('station_code')
        return get_object_or_404(TrainStation, station_code=station_code)

    def get_success_url(self):
        return reverse('admin_page')




# Edit Train Journey
class AdminEditTrainJourneyView(UpdateView):
    model = TrainJourney
    form_class = TrainJourneyAdminForm
    template_name = 'admin-control/admin-edit-journey.html'
    context_object_name = 'journey'

    def get_object(self, queryset=None):
        journey_id = self.kwargs.get('journey_id')
        return get_object_or_404(TrainJourney, journey_id=journey_id)

    def get_success_url(self):
        return reverse('admin_page')




# Add Train Journey
class AdminAddJourneyView(CreateView):
    model = TrainJourney
    form_class = TrainJourneyAdminForm
    template_name = 'admin-control/admin-add-journey.html'

    def get_success_url(self):
        return reverse_lazy('admin_page')



# Delete Train Journey
class AdminDeleteJourneyView(View):
    success_url = reverse_lazy('admin_page')

    def post(self, request, journey_id):
        journey = get_object_or_404(TrainJourney, journey_id=journey_id)
        journey.delete()
        return redirect(self.success_url)




# Edit Customer
class AdminEditCustomerView(UpdateView):
    model = CustomUser
    form_class = CustomerForm
    template_name = 'admin-control/admin-edit-customer.html'
    context_object_name = 'customer'

    def get_object(self, queryset=None):
        customer_id = self.kwargs.get('customer_id')
        return get_object_or_404(CustomUser, id=customer_id)

    def get_success_url(self):
        return reverse('admin_page')




# Edit Ticket
class AdminEditTicketView(UpdateView):
    model = Ticket
    form_class = TicketForm
    template_name = 'admin-control/admin-edit-ticket.html'
    context_object_name = 'ticket'

    def get_object(self, queryset=None):
        ticket_id = self.kwargs.get('ticket_id')
        return get_object_or_404(Ticket, ticket_id=ticket_id)

    def get_success_url(self):
        return reverse('admin_page')




# Add Train Journey
class AdminAddTicketView(CreateView):
    model = Ticket
    form_class = TicketForm
    template_name = 'admin-control/admin-add-ticket.html'

    def get_success_url(self):
        return reverse_lazy('admin_page')



# Delete Ticket
class AdminDeleteTicketView(View):
    success_url = reverse_lazy('admin_page')

    def post(self, request, ticket_id):
        ticket = get_object_or_404(Ticket, ticket_id=ticket_id)
        ticket.delete()
        return redirect(self.success_url)