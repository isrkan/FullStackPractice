from rest_framework import viewsets
from .models import TrainStation, TrainOperator, TrainJourney, CustomUser, Ticket, Administrator
from .serializers import (
    TrainStationSerializer,
    TrainOperatorSerializer,
    TrainJourneySerializer,
    CustomUserSerializer,
    TicketSerializer,
    AdministratorSerializer,
)

# Define viewsets for each model that define the API logic
class TrainStationViewSet(viewsets.ModelViewSet):
    queryset = TrainStation.objects.all()
    serializer_class = TrainStationSerializer

class TrainOperatorViewSet(viewsets.ModelViewSet):
    queryset = TrainOperator.objects.all()
    serializer_class = TrainOperatorSerializer

class TrainJourneyViewSet(viewsets.ModelViewSet):
    queryset = TrainJourney.objects.all()
    serializer_class = TrainJourneySerializer

class CustomUserViewSet(viewsets.ModelViewSet):
    queryset = CustomUser.objects.all()
    serializer_class = CustomUserSerializer

class TicketViewSet(viewsets.ModelViewSet):
    queryset = Ticket.objects.all()
    serializer_class = TicketSerializer

class AdministratorViewSet(viewsets.ModelViewSet):
    queryset = Administrator.objects.all()
    serializer_class = AdministratorSerializer