from django.shortcuts import render
from .models import Customer, TrainStation, TrainOperator, TrainJourney, Ticket, Administrator
def home(request):
    return render(request, 'home_app/home.html')

def train_journey(request):
    journeyList = TrainJourney.objects.order_by('date')
    journey_dict = {'journeys': journeyList}
    return render(request, 'home_app/journeys.html', context=journey_dict)

def contact(request):
    return render(request, 'home_app/contact.html')