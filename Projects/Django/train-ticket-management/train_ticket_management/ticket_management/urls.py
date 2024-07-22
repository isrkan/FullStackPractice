from django.urls import path
from . import views

urlpatterns = [
       path('', views.HomeView.as_view(), name='home'),
       path('journeys/', views.TrainJourneyListView.as_view(), name='journeys'),
       path('contact/', views.ContactView.as_view(), name='contact'),
       path('register/', views.RegisterView.as_view(), name='register'),
       path('login/', views.CustomLoginView.as_view(), name='login'),
       path('logout/', views.CustomLogoutView.as_view(), name='logout'),
       path('account/', views.AccountView.as_view(), name='account'),
       path('account/edit/', views.CustomerUpdateView.as_view(), name='edit_customer'),
       path('account/tickets/edit/<str:ticket_id>/', views.TicketUpdateView.as_view(), name='edit_ticket'),
       path('account/tickets/cancel/<str:ticket_id>/', views.CancelTicketView.as_view(), name='cancel_ticket'),
       path('train-operator-login/', views.TrainOperatorLoginView.as_view(), name='train_operator_login'),
       path('train-operator-logout/', views.TrainOperatorLogoutView.as_view(), name='train_operator_logout'),
       path('operator-journeys/', views.OperatorJourneysView.as_view(), name='operator_journeys'),
       path('add-journey/', views.AddJourneyView.as_view(), name='add_journey'),
       path('edit-journey/<int:journey_id>/', views.EditJourneyView.as_view(), name='edit_journey'),
       path('operator-journeys/cancel/<int:journey_id>/', views.CancelJourneyView.as_view(), name='cancel_journey'),
       path('admin-login/', views.AdminLoginView.as_view(), name='admin_login'),
       path('admin-logout/', views.AdminLogoutView.as_view(), name='admin_logout'),
       path('admin-page/', views.AdminPageView.as_view(), name='admin_page'),
       path('admin-page/edit-operator/<str:operator_code>/', views.AdminEditTrainOperatorView.as_view(), name='admin_edit_operator'),
       path('admin-page/edit-station/<str:station_code>/', views.AdminEditTrainStationView.as_view(), name='admin_edit_station'),
       path('admin-page/edit-journey/<int:journey_id>/', views.AdminEditTrainJourneyView.as_view(), name='admin_edit_journey'),
       path('admin-page/add-journey/', views.AdminAddJourneyView.as_view(), name='admin_add_journey'),
       path('admin-page/delete-journey/<int:journey_id>/', views.AdminDeleteJourneyView.as_view(), name='admin_delete_journey'),
       path('admin-page/edit-customer/<int:customer_id>/', views.AdminEditCustomerView.as_view(), name='admin_edit_customer'),
       path('admin-page/edit-ticket/<str:ticket_id>/', views.AdminEditTicketView.as_view(), name='admin_edit_ticket'),
       path('admin-page/add-ticket/', views.AdminAddTicketView.as_view(), name='admin_add_ticket'),
       path('admin-page/delete-ticket/<str:ticket_id>/', views.AdminDeleteTicketView.as_view(), name='admin_delete_ticket'),
]