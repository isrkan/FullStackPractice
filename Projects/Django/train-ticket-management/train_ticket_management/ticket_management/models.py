from django.db import models
from django.contrib.auth.models import AbstractUser
from django.core.validators import RegexValidator, MinLengthValidator
from django.contrib.auth.hashers import make_password, check_password

class TrainStation(models.Model):
    station_code = models.CharField(max_length=10, primary_key=True)
    station_name = models.CharField(max_length=100)
    city = models.CharField(max_length=100)
    country = models.CharField(max_length=100)
    latitude = models.FloatField()
    longitude = models.FloatField()
    time_zone = models.CharField(max_length=50)

    def __str__(self):
        return f"{self.station_name} ({self.station_code})"


class TrainOperator(models.Model):
    operator_code = models.CharField(max_length=10, primary_key=True)
    operator_name = models.CharField(max_length=100)
    headquarters_location = models.CharField(max_length=100)
    username = models.CharField(max_length=50, unique=True)
    password = models.CharField(max_length=128)

    def __str__(self):
        return self.operator_name

    def set_password(self, raw_password):
        self.password = make_password(raw_password)
        self.save()

    def check_password(self, raw_password):
        return check_password(raw_password, self.password)


class TrainJourney(models.Model):
    journey_id = models.AutoField(primary_key=True)
    journey_number = models.CharField(max_length=20)
    train_operator = models.ForeignKey('TrainOperator', on_delete=models.CASCADE)
    origin_station = models.ForeignKey('TrainStation', related_name='journeys_from', on_delete=models.CASCADE)
    destination_station = models.ForeignKey('TrainStation', related_name='journeys_to', on_delete=models.CASCADE)
    date = models.DateField()
    departure_time_local = models.TimeField()
    arrival_time_local = models.TimeField()
    remaining_tickets = models.IntegerField()
    journey_status = models.CharField(max_length=20, choices=(
        ('SCHEDULED', 'Scheduled'),
        ('CANCELLED', 'Cancelled'),
        ('COMPLETED', 'Completed'),
    ))

    def __str__(self):
        return f"{self.journey_number} - {self.origin_station} to {self.destination_station}"


class CustomUser(AbstractUser):
    address = models.CharField(max_length=255, validators=[MinLengthValidator(1)])
    phone_number = models.CharField(
        max_length=15,
        validators=[RegexValidator(regex=r'^\+\d{11,14}$',message="Phone Number must start with '+' followed by 11 to 14 digits")]
    )
    credit_card_number = models.CharField(
        max_length=19,
        validators=[RegexValidator(regex=r'^\d{13,19}$',message="Credit Card Number must be between 13 and 19 digits")]
    )


class Ticket(models.Model):
    class BookingStatus(models.TextChoices):
        BOOKED = 'BOOKED', 'Booked'
        CANCELLED = 'CANCELLED', 'Cancelled'
        CONFIRMED = 'CONFIRMED', 'Confirmed'
        CHECKED_IN = 'CHECKED_IN', 'Checked In'
        COMPLETED = 'COMPLETED', 'Completed'
        PENDING = 'PENDING', 'Pending'

    class ClassType(models.TextChoices):
        ECONOMY = 'ECONOMY', 'Economy'
        FIRST = 'FIRST', 'First'
        BUSINESS = 'BUSINESS', 'Business'

    ticket_id = models.CharField(max_length=20, primary_key=True)
    custom_user = models.ForeignKey('CustomUser', on_delete=models.CASCADE)
    train_journey = models.ForeignKey('TrainJourney', on_delete=models.CASCADE)
    class_type = models.CharField(max_length=10, choices=ClassType.choices)
    seat_number = models.CharField(max_length=10)
    booking_status = models.CharField(max_length=20, choices=BookingStatus.choices)
    price = models.DecimalField(max_digits=10, decimal_places=2)

    def __str__(self):
        return f"{self.ticket_id} - {self.customer} - {self.train_journey}"


class Administrator(models.Model):
    admin_id = models.AutoField(primary_key=True)
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    username = models.CharField(max_length=50, unique=True)
    password = models.CharField(max_length=128)

    def __str__(self):
        return f"{self.first_name} {self.last_name}"

    def set_password(self, raw_password):
        self.password = make_password(raw_password)
        self.save()

    def check_password(self, raw_password):
        return check_password(raw_password, self.password)