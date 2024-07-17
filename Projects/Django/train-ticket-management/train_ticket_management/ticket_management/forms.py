from django import forms
from django.contrib.auth.forms import UserCreationForm
from .models import CustomUser, TrainJourney

class CustomUserCreationForm(UserCreationForm):
    class Meta(UserCreationForm.Meta):
        model = CustomUser
        fields = ('username', 'first_name', 'last_name', 'address', 'phone_number', 'credit_card_number')

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        for field in self.fields.values():
            field.required = True

class TrainOperatorLoginForm(forms.Form):
    username = forms.CharField(max_length=50)
    password = forms.CharField(widget=forms.PasswordInput)


class AdminLoginForm(forms.Form):
    username = forms.CharField(max_length=50)
    password = forms.CharField(widget=forms.PasswordInput)


class JourneySearchForm(forms.Form):
    origin_station = forms.CharField(label='Origin Station', max_length=100, required=False)
    destination_station = forms.CharField(label='Destination Station', max_length=100, required=False)
    date = forms.DateField(label='Date', required=False, widget=forms.TextInput(attrs={'type': 'date'}))

class TrainJourneyForm(forms.ModelForm):
    class Meta:
        model = TrainJourney
        fields = ['journey_number', 'origin_station', 'destination_station', 'date', 'departure_time_local', 'arrival_time_local', 'remaining_tickets', 'journey_status']

        widgets = {
            'date': forms.DateInput(attrs={'type': 'date'}),
            'departure_time_local': forms.TimeInput(attrs={'type': 'time'}),
            'arrival_time_local': forms.TimeInput(attrs={'type': 'time'}),
        }
