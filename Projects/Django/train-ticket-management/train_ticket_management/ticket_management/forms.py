from django import forms
from django.contrib.auth.forms import UserCreationForm
from .models import CustomUser, TrainJourney, TrainOperator, TrainStation, Ticket

class CustomUserCreationForm(UserCreationForm):  # Custom form for creating a new user, extending Django's built-in UserCreationForm
    # UserCreationForm is a built-in form in Django that provides fields and validation for user creation, including password handling
    class Meta(UserCreationForm.Meta):  # Meta class to specify model and fields. It allows customization of the form's behavior and fields.
        model = CustomUser  # Model to associate with the form
        fields = ('username', 'first_name', 'last_name', 'address', 'phone_number', 'credit_card_number')  # Fields to include

    # Initialization method to set all fields as required
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        # Iterate over form fields and set each one as required
        for field in self.fields.values():
            field.required = True


class CustomerUpdateForm(forms.ModelForm):  # Form for updating customer information, using ModelForm for CustomUser model
    # ModelForm is a class in Django that automatically generates form fields based on the specified model
    class Meta:
        model = CustomUser
        fields = ['address', 'phone_number', 'credit_card_number']


class TicketUpdateForm(forms.ModelForm):  # Form for updating ticket information, using ModelForm for Ticket model
    class Meta:
        model = Ticket
        fields = ['class_type', 'seat_number']


class PurchaseTicketForm(forms.ModelForm):  # Form for purchasing a ticket, using ModelForm for Ticket model
    class Meta:
        model = Ticket
        fields = ['class_type', 'seat_number']


class PaymentForm(forms.Form):  # Form for processing payment information, using a standard form
    # Form is a class in Django used for creating forms without a corresponding model. It provides full control over form fields and validation
    expiry_date = forms.CharField(max_length=5, required=True, help_text="MM/YY")
    cvv = forms.CharField(max_length=3, required=True)
    ticket_id = forms.CharField(widget=forms.HiddenInput())


class TrainOperatorLoginForm(forms.Form):  # Form for train operator login
    username = forms.CharField(max_length=50)
    password = forms.CharField(widget=forms.PasswordInput)


class AdminLoginForm(forms.Form):  # Form for admin login
    username = forms.CharField(max_length=50)
    password = forms.CharField(widget=forms.PasswordInput)


class JourneySearchForm(forms.Form):  # Form for searching journeys
    origin_station = forms.CharField(label='Origin Station', max_length=100, required=False)
    destination_station = forms.CharField(label='Destination Station', max_length=100, required=False)
    date = forms.DateField(label='Date', required=False, widget=forms.TextInput(attrs={'type': 'date'}))  # Date field with HTML5 date input


class TrainJourneyForm(forms.ModelForm):  # Form for creating or editing train journeys, using ModelForm for train journey model
    class Meta:
        model = TrainJourney
        fields = ['journey_number', 'origin_station', 'destination_station', 'date', 'departure_time_local', 'arrival_time_local', 'remaining_tickets', 'journey_status']

        # Widgets to use specific HTML input types
        widgets = {
            'date': forms.DateInput(attrs={'type': 'date'}),  # Date input with HTML5 date input
            'departure_time_local': forms.TimeInput(attrs={'type': 'time'}),  # Time input with HTML5 time input
            'arrival_time_local': forms.TimeInput(attrs={'type': 'time'}),  # Time input with HTML5 time input
        }


class TrainOperatorForm(forms.ModelForm):  # Form for creating or editing train operators, using ModelForm for train operator model
    class Meta:
        model = TrainOperator
        fields = ['operator_code', 'operator_name', 'headquarters_location', 'username']


class TrainStationForm(forms.ModelForm):  # Form for creating or editing train stations, using ModelForm for train station model
    class Meta:
        model = TrainStation
        fields = ['station_code', 'station_name', 'city', 'country']


class CustomerForm(forms.ModelForm): # Form for creating or editing customers, using ModelForm for customUser model
    class Meta:
        model = CustomUser
        fields = ['first_name', 'last_name', 'address', 'phone_number', 'username']


class TrainJourneyAdminForm(forms.ModelForm):  # Form for creating or editing train journeys by admin, using ModelForm for train journey model
    class Meta:
        model = TrainJourney
        fields = ['journey_number', 'train_operator', 'origin_station', 'destination_station', 'date', 'departure_time_local', 'arrival_time_local', 'remaining_tickets', 'journey_status']

        widgets = {
            'date': forms.DateInput(attrs={'type': 'date'}),
            'departure_time_local': forms.TimeInput(attrs={'type': 'time'}),
            'arrival_time_local': forms.TimeInput(attrs={'type': 'time'}),
        }

class TicketForm(forms.ModelForm):  # Form for creating or editing tickets, using ModelForm for ticket model
    class Meta:
        model = Ticket
        fields = ['ticket_id', 'custom_user', 'train_journey', 'class_type', 'seat_number', 'booking_status', 'price']