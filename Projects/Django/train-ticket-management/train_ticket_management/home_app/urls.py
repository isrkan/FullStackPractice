from django.urls import path
from . import views

urlpatterns = [
    path('', views.home, name='home'),
    path('journeys/', views.train_journey, name='journeys'),
    path('contact/', views.contact, name='contact'),
]