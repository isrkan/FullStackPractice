from django.shortcuts import render, redirect, get_object_or_404
from .forms import CustomUserCreationForm, TrainOperatorLoginForm, AdminLoginForm, JourneySearchForm, TrainJourneyForm, \
    TrainOperatorForm, TrainStationForm, TicketForm, CustomerForm, TrainJourneyAdminForm, CustomerUpdateForm, TicketUpdateForm, PurchaseTicketForm, PaymentForm
from django.contrib.auth.forms import AuthenticationForm
from django.contrib.auth import authenticate, login as auth_login, logout as auth_logout
from django.contrib.auth.mixins import LoginRequiredMixin
from .models import TrainStation, TrainOperator, TrainJourney, CustomUser, Ticket, Administrator
from django.views.generic import TemplateView, ListView, FormView, DetailView, UpdateView, CreateView
from django.views import View
from django.urls import reverse_lazy, reverse
from django.contrib import messages
import random
import string

class HomeView(TemplateView):  # Uses TemplateView to render a static page. TemplateView is used for static pages that don't require a dynamic queryset or context
    template_name = 'ticket_management/home.html'  # Specify the template to use

class ContactView(TemplateView):  # Uses TemplateView to render a static page
    template_name = 'ticket_management/contact.html'

class TrainJourneyListView(ListView):  # Uses ListView to render a list of objects. ListView is used when we need to display multiple objects from a model
    model = TrainJourney  # Specifies the model to be used in the view
    template_name = 'ticket_management/journeys.html'  # Defines the template to be used for rendering the view
    context_object_name = 'journeys'  # Specifies the context object name to be used in the template

    # Overrides the default queryset to include search filtering logic and returns a list of items
    # A queryset is a collection of database queries that Django uses to retrieve objects from the database. It can be filtered, ordered, and manipulated to retrieve the desired set of objects.
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
        # Adds the search form to the context, making it accessible in the template
        context['form'] = JourneySearchForm(self.request.GET or None)
        return context


#############################################################################
########################## User class based views ###########################
#############################################################################

class RegisterView(FormView):  # Handles user registration using a form. FormView handles form display and processing. It provides methods for validating forms and handling form submissions.
    template_name = 'ticket_management/register.html'
    form_class = CustomUserCreationForm  # Specifies the form class to be used in this view

    # Called when the form is valid and successfully submitted
    def form_valid(self, form):
        user = form.save()  # Save the new user created by the form
        auth_login(self.request, user)  # Automatically log in the newly created user
        return redirect('login')  # Redirect to the login page after successful registration


class CustomLoginView(FormView):  # Handles user login using a form
    template_name = 'ticket_management/login.html'
    form_class = AuthenticationForm

    # Called when the form is valid and successfully submitted
    def form_valid(self, form):
        # Extract username and password from the form
        username = form.cleaned_data.get('username')
        password = form.cleaned_data.get('password')
        # Authenticate the user using the provided credentials
        user = authenticate(username=username, password=password)
        if user is not None:
            # Log in the authenticated user
            auth_login(self.request, user)
            # Redirect to the account page after successful login
            return redirect('account')
        else:
            # If authentication fails, render the form with errors
            return self.form_invalid(form)


class CustomLogoutView(LoginRequiredMixin, View):  # Handles user logout. LoginRequiredMixin ensures that the view can only be accessed by authenticated users. View is used to create custom views that doesn't fit into the generic class-based views.
    # Ensures that only authenticated users can access this view
    def get(self, request, *args, **kwargs):
        # Log out the current user
        auth_logout(request)
        return redirect('home')


class AccountView(LoginRequiredMixin, DetailView):  # Displays user account details. DetailView is used to display detailed information about a single object.
    model = CustomUser
    template_name = 'ticket_management/account.html'
    context_object_name = 'customer'  # Specifies the context object name to be used in the template

    # Returns the current user object for the detail view
    def get_object(self):
        return self.request.user

    # Adds additional context data to the template
    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)  # Get the default context data from the parent class
        context['tickets'] = Ticket.objects.filter(custom_user=self.request.user).exclude(booking_status=Ticket.BookingStatus.PENDING)  # Filter and exclude tickets with a status of 'PENDING'
        return context


class CustomerUpdateView(LoginRequiredMixin, UpdateView):  # Handles updating user details. UpdateView is used for updating existing objects. It provides a form pre-filled with the current object's data and handles form submission and validation.
    model = CustomUser
    form_class = CustomerUpdateForm  # Specifies the form class to be used for updating the user
    template_name = 'ticket_management/edit_customer.html'
    success_url = reverse_lazy('account')  # URL to redirect to after a successful form submission. reverse_lazy is a utility function used for URL resolution in a lazy manner. It allows to reference URLs by their name without needing to hardcode the URL path.

    # Returns the current user object to be updated
    def get_object(self):
        return self.request.user

    # Called when the form is valid and successfully submitted
    def form_valid(self, form):
        form.save()  # Save the updated user details
        return super().form_valid(form)  # Call the superclass method to handle redirection


class TicketUpdateView(UpdateView):  # Handles updating ticket details.
    model = Ticket
    form_class = TicketUpdateForm
    template_name = 'ticket_management/edit_ticket.html'
    success_url = reverse_lazy('account')

    # Returns the ticket object specified by ticket_id from the URL
    def get_object(self, queryset=None):
        return get_object_or_404(Ticket, ticket_id=self.kwargs['ticket_id'])  # get_object_or_404 is a helper function that retrieves an object based on some criteria or returns a 404 error if the object is not found. It simplifies object retrieval and error handling.

    def form_valid(self, form):
        form.save()
        return super().form_valid(form)


class CancelTicketView(View):  # Handles the cancellation of a ticket
    # Handles POST requests for canceling a ticket
    def post(self, request, ticket_id):
        ticket = get_object_or_404(Ticket, ticket_id=ticket_id)  # Retrieve the ticket object with the specified ticket_id or raise a 404 error if not found
        journey = ticket.train_journey  # Retrieve the associated train journey object from the ticket

        ticket.booking_status = 'CANCELED'  # Update the booking status to CANCELED
        journey.remaining_tickets += 1  # Increment the remaining tickets for the flight
        # Save the updated journey and ticket objects to the database
        journey.save()
        ticket.save()
        messages.success(request, "Ticket canceled successfully.")  # Add a success message

        return redirect('account')


class PurchaseTicketView(LoginRequiredMixin, View):  # Handles the purchase of tickets
    # Handles GET requests to display the purchase form
    def get(self, request, journey_id):
        journey = get_object_or_404(TrainJourney, journey_id=journey_id)
        customer = get_object_or_404(CustomUser, username=request.user.username)  # Retrieve the customuser object of the currently logged-in user
        form = PurchaseTicketForm()  # Initialize an empty purchase ticket form
        # Prepare context data to be passed to the template
        context = {
            'journey': journey,
            'customer': customer,
            'form': form
        }
        return render(request, 'ticket_management/purchase.html', context)

    # Handles POST requests to process the ticket purchase
    def post(self, request, journey_id):
        journey = get_object_or_404(TrainJourney, journey_id=journey_id)
        customer = get_object_or_404(CustomUser, username=request.user.username)
        form = PurchaseTicketForm(request.POST)  # Initialize a purchase ticket form with POST data

        if form.is_valid():
            ticket = form.save(commit=False)  # Create a new ticket object from the form data but do not save to the database yet
            # Assign additional attributes to the ticket
            ticket.ticket_id = generate_ticket_id()  # Generate a unique ticket ID
            ticket.train_journey = journey
            ticket.custom_user = customer
            ticket.price = 500  # Assuming a fixed price for now
            ticket.booking_status = 'PENDING'  # Set the initial booking status
            ticket.save()  # Save the ticket to the database

            messages.success(request, "Ticket saved.")
            return redirect('payment', ticket_id=ticket.ticket_id)  # Redirect to the payment page with the newly created ticket's id
        else:
            messages.error(request, "There was an error with your purchase. Please try again.")
        # Prepare context data to be passed to the template in case of form errors
        context = {
            'journey': journey,
            'customer': customer,
            'form': form
        }
        return render(request, 'ticket_management/purchase.html', context)

# Helper function to generate a unique ticket id
def generate_ticket_id():
    ticket_id = 'TCKT'  # Start the ID with a prefix
    for i in range(8):  # Generate an 8-character random string
        if i % 2 == 0:
            ticket_id += str(random.randint(0, 9))  # Add a random digit
        else:
            ticket_id += random.choice(string.ascii_uppercase)  # Add a random capital letter
    return ticket_id


class PaymentView(LoginRequiredMixin, FormView):  # Handles the payment process for a ticket purchase
    template_name = 'ticket_management/payment.html'
    form_class = PaymentForm

    # Pre-populates the form with initial data
    def get_initial(self):
        initial = super().get_initial()  # Call the parent method to get initial data
        ticket_id = self.kwargs.get('ticket_id')  # Retrieve the ticket_id from URL kwargs
        ticket = get_object_or_404(Ticket, pk=ticket_id)
        initial['ticket_id'] = ticket_id  # Set the ticket_id in the initial data for the form
        return initial

    # Provides additional context data for the template
    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)  # Get the default context from the parent class
        ticket_id = self.kwargs.get('ticket_id')  # Retrieve the ticket_id from URL kwargs
        ticket = get_object_or_404(Ticket, pk=ticket_id)
        customer = self.request.user  # Get the current logged-in user
        context['customer'] = customer  # Add the customer to the context
        context['ticket'] = ticket  # Add the ticket to the context
        return context

    # Handles form submission and processes payment
    def form_valid(self, form):
        ticket_id = form.cleaned_data['ticket_id']
        expiry_date = form.cleaned_data['expiry_date']
        cvv = form.cleaned_data['cvv']
        ticket = get_object_or_404(Ticket, pk=ticket_id)

        # Placeholder for actual payment logic
        payment_successful = self.validate_and_process_payment(expiry_date, cvv)

        if payment_successful:
            ticket.booking_status = Ticket.BookingStatus.BOOKED  # Update ticket status to BOOKED if payment is successful
            ticket.save()
            return redirect('confirmation', ticket_id=ticket_id)
        else:
            return self.form_invalid(form)  # Re-render the form with error messages if payment fails

    # Placeholder method for validating and processing payment
    def validate_and_process_payment(self, expiry_date, cvv):
        # Placeholder for real payment validation and processing
        return True


class ConfirmationView(LoginRequiredMixin, TemplateView):  # Shows the confirmation page after a successful payment
    template_name = 'ticket_management/confirmation.html'

    # Provides additional context data for the template
    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        ticket_id = self.kwargs['ticket_id']
        ticket = get_object_or_404(Ticket, pk=ticket_id)
        context['ticket'] = ticket
        context['train_journey'] = ticket.train_journey
        context['customer'] = ticket.custom_user
        return context


class MyCartView(LoginRequiredMixin, ListView):  # Displays a list of tickets that the user has added to their cart
    model = Ticket
    template_name = 'ticket_management/mycart.html'
    context_object_name = 'tickets'

    # Overrides the default queryset to include only tickets that are pending in the user's cart
    def get_queryset(self):
        return Ticket.objects.filter(
            custom_user=self.request.user,
            booking_status=Ticket.BookingStatus.PENDING
        )


class RemoveTicketFromCartView(LoginRequiredMixin, View):  # Handles the removal of a ticket from the user's cart
    # Handles the POST request to remove a ticket from the cart
    def post(self, request, ticket_id):
        ticket = get_object_or_404(Ticket, ticket_id=ticket_id)
        # Check if the ticket belongs to the current user and is in PENDING status
        if ticket.custom_user == request.user and ticket.booking_status == Ticket.BookingStatus.PENDING:
            ticket.delete()  # Delete the ticket from the database
            messages.success(request, "Ticket removed from cart.")
        else:
            messages.error(request, "You cannot remove this ticket.")

        return redirect('mycart')


#############################################################################
##################### Train operator class based views ######################
#############################################################################

class TrainOperatorLoginView(FormView):  # Handles the login process for train operators
    template_name = 'ticket_management/train_operator_login.html'
    form_class = TrainOperatorLoginForm
    success_url = reverse_lazy('operator_journeys')  # URL to redirect to upon successful login

    def form_valid(self, form):
        username = form.cleaned_data.get('username')
        password = form.cleaned_data.get('password')
        try:
            operator = TrainOperator.objects.get(username=username)  # Attempt to retrieve the train operator object based on the username
            # Check if the provided password matches the stored password
            if operator.check_password(password):
                self.request.session['operator_code'] = operator.operator_code  # Store the operator's code in the session to maintain login state
                return super().form_valid(form)  # Proceed with the form's valid action
            else:
                form.add_error('password', 'Incorrect password')
        except TrainOperator.DoesNotExist:
            form.add_error('username', 'Username does not exist')

        return self.form_invalid(form)  # Return invalid form response if login fails


class TrainOperatorLogoutView(View): # Handles the logout process for train operators
    # Handles GET requests for logging out
    def get(self, request, *args, **kwargs):
        # Remove operator code from session if it exists
        if 'operator_code' in request.session:
            del request.session['operator_code']
        return redirect('home')


class OperatorJourneysView(TemplateView):  # Displays the list of journeys for the logged-in train operator
    template_name = 'ticket_management/operator_journeys.html'

    # Provides context data to the template
    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        operator_code = self.request.session.get('operator_code')  # Retrieve the operator code from the session
        operator = get_object_or_404(TrainOperator, operator_code=operator_code)
        journeys = TrainJourney.objects.filter(train_operator=operator).order_by('date')          # Filter train journey objects to include only those managed by the current operator
        context['journeys'] = journeys
        return context


class AddJourneyView(FormView):  # Handles the creation of new train journeys
    template_name = 'ticket_management/add-journey.html'
    form_class = TrainJourneyForm
    success_url = '/operator-journeys/'  # URL to redirect to upon successful form submission

    # Provide additional arguments to the form class
    def get_form_kwargs(self):
        kwargs = super().get_form_kwargs()  # Get the default form arguments
        kwargs['initial'] = {'operator_code': self.request.session.get('operator_code')}  # Set initial data for the form
        return kwargs

    # Handle successful form submission
    def form_valid(self, form):
        operator_code = self.request.session.get('operator_code')
        train_operator = get_object_or_404(TrainOperator, operator_code=operator_code)
        journey = form.save(commit=False)  # Create a train journey object without saving it yet
        journey.train_operator = train_operator  # Assign the current operator to the journey
        journey.save()  # Save the journey to the database
        return redirect(self.get_success_url())  # Redirect to the success URL after saving


class EditJourneyView(UpdateView):  # Handles the editing of existing train journeys
    model = TrainJourney
    form_class = TrainJourneyForm  # Form class used to handle journey updates
    template_name = 'ticket_management/edit-journey.html'
    context_object_name = 'journey'
    success_url = '/operator-journeys/'

    # Retrieve the specific train journey object to be edited
    def get_object(self, queryset=None):
        journey_id = self.kwargs.get('journey_id')  # Get the journey ID from URL parameters
        return get_object_or_404(TrainJourney, journey_id=journey_id)

    # Handle successful form submission
    def form_valid(self, form):
        form.save()
        return redirect(self.get_success_url())


class CancelJourneyView(View):  # Handles the cancellation of train journeys
    def post(self, request, journey_id):
        journey = get_object_or_404(TrainJourney, journey_id=journey_id)
        journey.journey_status = 'Canceled'  # Update the journey status to 'Canceled'
        journey.save()
        return redirect(reverse_lazy('operator_journeys'))

#############################################################################
######################### Admin class based views ###########################
#############################################################################

class AdminLoginView(View):  # Handles the login process for administrators
    # Render the login form on GET requests
    def get(self, request):
        form = AdminLoginForm()  # Create an empty login form
        return render(request, 'ticket_management/admin_login.html', {'form': form})  # Render the login template with the form

    # Handle form submission on POST requests
    def post(self, request):
        form = AdminLoginForm(request.POST)  # Create a form instance with submitted data
        if form.is_valid():
            username = form.cleaned_data.get('username')  # Extract username from cleaned data
            password = form.cleaned_data.get('password')  # Extract password from cleaned data
            try:
                administrator = Administrator.objects.get(username=username)  # Retrieve the administrator object
                if administrator.check_password(password):  # Check if the password is correct
                    request.session['admin_id'] = administrator.admin_id  # Store admin id in the session
                    return redirect('admin_page')  # Redirect to the admin page
                else:
                    form.add_error('password', 'Incorrect password')
            except Administrator.DoesNotExist:
                form.add_error('username', 'Username does not exist')
        return render(request, 'ticket_management/admin_login.html', {'form': form})  # Re-render the login template with errors


class AdminLogoutView(View):  # Handles the logout process for administrators
    # Handle GET requests to log out the admin
    def get(self, request, *args, **kwargs):
        if 'admin_id' in request.session:  # Check if 'admin_id' is in the session
            del request.session['admin_id']  # Remove 'admin_id' from the session to log out
        return redirect('home')


class AdminPageView(TemplateView):  # Renders the admin dashboard with various context data
    template_name = 'ticket_management/admin-page.html'

    # Provide context data for the admin page
    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)  # Get the default context data from the parent class
        admin_id = self.request.session.get('admin_id')  # Retrieve admin id from the session
        administrator = get_object_or_404(Administrator, admin_id=admin_id)
        context['administrator'] = administrator
        context['train_operators'] = TrainOperator.objects.all()  # Add all train operators to the context
        context['stations'] = TrainStation.objects.all()  # Add all train stations to the context
        context['journeys'] = TrainJourney.objects.all()  # Add all train journeys to the context
        context['customers'] = CustomUser.objects.all()  # Add all customers to the context
        context['tickets'] = Ticket.objects.all()  # Add all tickets to the context
        return context


class AdminEditTrainOperatorView(UpdateView):  # Admin control for editing train operator
    model = TrainOperator
    form_class = TrainOperatorForm
    template_name = 'admin-control/admin-edit-operator.html'
    context_object_name = 'operator'

    # Retrieves the specific train operator object based on operator code
    def get_object(self, queryset=None):
        operator_code = self.kwargs.get('operator_code')
        return get_object_or_404(TrainOperator, operator_code=operator_code)

    # Determines the URL to redirect to after a successful update
    def get_success_url(self):
        return reverse('admin_page')


class AdminEditTrainStationView(UpdateView):  # Admin control for editing train station
    model = TrainStation
    form_class = TrainStationForm
    template_name = 'admin-control/admin-edit-station.html'
    context_object_name = 'station'

    def get_object(self, queryset=None):
        station_code = self.kwargs.get('station_code')
        return get_object_or_404(TrainStation, station_code=station_code)

    def get_success_url(self):
        return reverse('admin_page')


class AdminEditTrainJourneyView(UpdateView):  # Admin control for editing train journey
    model = TrainJourney
    form_class = TrainJourneyAdminForm
    template_name = 'admin-control/admin-edit-journey.html'
    context_object_name = 'journey'

    def get_object(self, queryset=None):
        journey_id = self.kwargs.get('journey_id')
        return get_object_or_404(TrainJourney, journey_id=journey_id)

    def get_success_url(self):
        return reverse('admin_page')


class AdminAddJourneyView(CreateView):  # Admin control for adding train journey
    model = TrainJourney
    form_class = TrainJourneyAdminForm
    template_name = 'admin-control/admin-add-journey.html'

    # Determines the URL to redirect to after a successful creation
    def get_success_url(self):
        return reverse_lazy('admin_page')


class AdminDeleteJourneyView(View):  # Admin control for deleting train journey
    success_url = reverse_lazy('admin_page')

    # Handles the POST request to delete a train journey
    def post(self, request, journey_id):
        journey = get_object_or_404(TrainJourney, journey_id=journey_id)  # Fetch the journey or return 404
        journey.delete()
        return redirect(self.success_url)


class AdminEditCustomerView(UpdateView):  # Admin control for editing customer
    model = CustomUser
    form_class = CustomerForm
    template_name = 'admin-control/admin-edit-customer.html'
    context_object_name = 'customer'

    def get_object(self, queryset=None):
        customer_id = self.kwargs.get('customer_id')
        return get_object_or_404(CustomUser, id=customer_id)

    def get_success_url(self):
        return reverse('admin_page')


class AdminEditTicketView(UpdateView):  # Admin control for editing ticket
    model = Ticket
    form_class = TicketForm
    template_name = 'admin-control/admin-edit-ticket.html'
    context_object_name = 'ticket'

    def get_object(self, queryset=None):
        ticket_id = self.kwargs.get('ticket_id')
        return get_object_or_404(Ticket, ticket_id=ticket_id)

    def get_success_url(self):
        return reverse('admin_page')


class AdminAddTicketView(CreateView):  # Admin control for adding ticket
    model = Ticket
    form_class = TicketForm
    template_name = 'admin-control/admin-add-ticket.html'

    def get_success_url(self):
        return reverse_lazy('admin_page')


class AdminDeleteTicketView(View):  # Admin control for deleting ticket
    success_url = reverse_lazy('admin_page')

    def post(self, request, ticket_id):
        ticket = get_object_or_404(Ticket, ticket_id=ticket_id)
        ticket.delete()
        return redirect(self.success_url)