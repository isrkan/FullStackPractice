from rest_framework import serializers
from .models import TrainStation, TrainOperator, TrainJourney, CustomUser, Ticket, Administrator

### This file defines serializers, which are responsible for converting complex data types, like Django models, into native Python data types that can be easily rendered into JSON (or XML) formats for API responses.
### They also handle deserialization, which is the process of converting parsed data back into complex types, after validating the incoming data.

class TrainStationSerializer(serializers.ModelSerializer):
    class Meta:
        model = TrainStation
        fields = '__all__'  # Or list specific fields

class TrainOperatorSerializer(serializers.ModelSerializer):
    class Meta:
        model = TrainOperator
        fields = '__all__'

class TrainJourneySerializer(serializers.ModelSerializer):
    class Meta:
        model = TrainJourney
        fields = '__all__'

class CustomUserSerializer(serializers.ModelSerializer):
    class Meta:
        model = CustomUser
        fields = '__all__'

class TicketSerializer(serializers.ModelSerializer):
    class Meta:
        model = Ticket
        fields = '__all__'

class AdministratorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Administrator
        fields = '__all__'