from django.contrib import admin
from .models import Customer, TrainStation, TrainOperator, TrainJourney, Ticket, Administrator

# Register your models here.
admin.site.register(Customer)
admin.site.register(TrainStation)
admin.site.register(TrainOperator)
admin.site.register(TrainJourney)
admin.site.register(Ticket)
admin.site.register(Administrator)