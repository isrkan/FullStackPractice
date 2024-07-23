from django.urls import path, include
from . import views
from rest_framework.routers import DefaultRouter
from .views_api import *
from rest_framework import permissions
from drf_yasg.views import get_schema_view
from drf_yasg import openapi

# Application urls
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
       path('purchase/<int:journey_id>/', views.PurchaseTicketView.as_view(), name='purchase_ticket'),
       path('payment/<str:ticket_id>/', views.PaymentView.as_view(), name='payment'),
       path('confirmation/<str:ticket_id>/', views.ConfirmationView.as_view(), name='confirmation'),
       path('mycart/', views.MyCartView.as_view(), name='mycart'),
       path('mycart/remove/<str:ticket_id>/', views.RemoveTicketFromCartView.as_view(), name='remove_ticket_from_cart'),
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


# Create a router and register the viewsets. Router automatically generates URL patterns for the API endpoints based on the viewsets we define
# Each register() call connects a viewset to a URL path, creating standard CRUD operations for each model
router = DefaultRouter()
router.register(r'trainstations', TrainStationViewSet)
router.register(r'trainoperators', TrainOperatorViewSet)
router.register(r'trainjourneys', TrainJourneyViewSet)
router.register(r'users', CustomUserViewSet)
router.register(r'tickets', TicketViewSet)
router.register(r'administrators', AdministratorViewSet)

# Create a schema view for Swagger
schema_view = get_schema_view(
    openapi.Info(
        title="Train Management System API",
        default_version='v1',
        description="This API exposes endpoints to manage train journeys, customers, tickets, and more.",
        contact=openapi.Contact(email="contact@railwaymaster.com"),
    ),
    public=True,
    permission_classes=(permissions.AllowAny,),
)

# Add Swagger and ReDoc URL patterns
urlpatterns += [
    path('api/', include(router.urls)),
    path('swagger/', schema_view.with_ui('swagger', cache_timeout=0), name='schema-swagger-ui'),
    path('redoc/', schema_view.with_ui('redoc', cache_timeout=0), name='schema-redoc'),
]