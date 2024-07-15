from django.contrib import admin
from .models import TrainStation, TrainOperator, TrainJourney, CustomUser, Ticket
from django.contrib.auth.admin import UserAdmin

# Register your models here.
admin.site.register(CustomUser, UserAdmin)
admin.site.register(TrainStation)
admin.site.register(TrainOperator)
admin.site.register(TrainJourney)
admin.site.register(Ticket)
