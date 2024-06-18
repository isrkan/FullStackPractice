from django.shortcuts import render

def home(request):
    return render(request, 'home_app/home.html')

def contact(request):
    return render(request, 'home_app/contact.html')