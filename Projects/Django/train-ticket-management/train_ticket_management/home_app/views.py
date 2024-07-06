from django.shortcuts import render, redirect
from django.contrib import messages
from .models import Customer, TrainStation, TrainOperator, TrainJourney, Ticket, Administrator
from .forms import CustomerRegistrationForm

def home(request):
    return render(request, 'home_app/home.html')

def train_journey(request):
    journeyList = TrainJourney.objects.order_by('date')
    journey_dict = {'journeys': journeyList}
    return render(request, 'home_app/journeys.html', context=journey_dict)

def contact(request):
    return render(request, 'home_app/contact.html')


def registration(request):
    if request.method == 'POST':
        form = CustomerRegistrationForm(request.POST)
        if form.is_valid():
            form.save()
            messages.success(request, 'Registration successful.')
            return redirect('home')  # Redirect to a success page or home page
        else:
            messages.error(request, 'Registration failed. Please correct the errors below.')
    else:
        form = CustomerRegistrationForm()

    return render(request, 'home_app/registration.html', {'form': form})