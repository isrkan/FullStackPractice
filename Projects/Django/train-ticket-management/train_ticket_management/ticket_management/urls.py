from django.urls import path
from . import views

urlpatterns = [
       path('', views.home, name='home'),
       path('journeys/', views.train_journey, name='journeys'),
       path('contact/', views.contact, name='contact'),
       path('register/', views.register, name='register'),
       path('login/', views.custom_login, name='login'),
       path('logout/', views.custom_logout, name='logout'),
       path('account/', views.account_view, name='account'),
       path('train-operator-login/', views.train_operator_login, name='train_operator_login'),
       path('train-operator-logout/', views.train_operator_logout, name='train_operator_logout'),
       path('operator-journeys/', views.operator_journeys, name='operator_journeys'),
       path('add-journey/', views.add_journey, name='add_journey'),
       path('edit-journey/<int:journey_id>/', views.edit_journey, name='edit_journey'),
       path('operator-journeys/cancel/<int:journey_id>/', views.cancel_journey, name='cancel_journey'),
       path('admin-login/', views.admin_login, name='admin_login'),
       path('admin-logout/', views.admin_logout, name='admin_logout'),
]