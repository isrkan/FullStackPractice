### Creating User Authorization Models in Django

Adding user authorization models to the Django project involves setting up a system for user authentication, including user registration, login, logout, and managing user permissions. Below is a guide to help you integrate these features into the existing Django project.
Django provides a built-in user authentication system that includes models, views, and forms for user management. To start using it, follow these steps:

### 1. Create user-related templates
Create templates for user registration, login and logout.

1. **Register template (`register.html`)**:
```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register</title>
    </head>
    <body>
        <h2>Register</h2>
        <form method="post">
            {% csrf_token %}
            {{ form.as_p }}
            <button type="submit">Register</button>
        </form>
    </body>
</html>
```

2. **Login template (`login.html`)**:
```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
    </head>
    <body>
        <h2>Login</h2>
        <form method="post">
            {% csrf_token %}
            {{ form.as_p }}
            <button type="submit">Login</button>
        </form>
    </body>
</html>
```

3. **Logout template (`logged_out.html`)**:
```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Logged Out</title>
    </head>
    <body>
        <h2>Logged Out</h2>
        <p>You have been logged out. <a href="{% url 'login' %}">Log in again?</a></p>
    </body>
</html>
```

### 2. Creating views, URLs and forms for user registration and login

#### a. Create user registration and login views
Open `expenses/views.py` and add the following code:
```python
from .forms import CustomUserCreationForm
from django.shortcuts import render, redirect
from django.contrib.auth import login, authenticate
from django.contrib.auth.forms import AuthenticationForm

def register(request):
    if request.method == 'POST':
        form = CustomUserCreationForm(request.POST)
        if form.is_valid():
            user = form.save()
            login(request, user)
            return redirect('home')  # Change 'home' to your desired redirect URL
    else:
        form = CustomUserCreationForm()
    return render(request, 'expenses/register.html', {'form': form})

def custom_login(request):
    if request.method == 'POST':
        form = AuthenticationForm(request, data=request.POST)
        if form.is_valid():
            username = form.cleaned_data.get('username')
            password = form.cleaned_data.get('password')
            user = authenticate(username=username, password=password)
            if user is not None:
                login(request, user)
                return redirect('home')  # Redirect to the home page
    else:
        form = AuthenticationForm()
    return render(request, 'expenses/login.html', {'form': form})
```

#### b. Update URLs for registration and authentication
Update the `expenses/urls.py` file:
```python
from django.urls import path
from django.contrib.auth import views as auth_views
from . import views

urlpatterns = [
    path('register/', views.register, name='register'),
    path('login/', views.custom_login, name='login'),
    path('logout/', auth_views.LogoutView.as_view(template_name='expenses/logged_out.html'), name='logout'),
]
```

#### c. Create a custom user creation form
1. Create a file named `forms.py` in the expenses app directory.
2. Define the custom user creation form in `forms.py` as follows:
```python
from django import forms
from django.contrib.auth.forms import UserCreationForm
from .models import CustomUser

class CustomUserCreationForm(UserCreationForm):
    class Meta(UserCreationForm.Meta):
        model = CustomUser
        fields = ('username', 'email', 'address', 'phone_number')  # Add any additional fields you want

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.fields['address'].required = False
        self.fields['phone_number'].required = False
```

### 3. Customizing the user model
If we need a custom user model, we can extend the default user model provided by Django.

#### a. Create a custom user model
In `expenses/models.py`, define a custom user model:
```python
from django.contrib.auth.models import AbstractUser

class CustomUser(AbstractUser):
    address = models.CharField(max_length=255, blank=True, null=True)
    phone_number = models.CharField(max_length=15, blank=True, null=True)
```

#### b. Register the CustomUser model in `admin.py`:
```python
from django.contrib.auth.admin import UserAdmin
from expenses.models import Expense, CustomUser

admin.site.register(Expense)
admin.site.register(CustomUser, UserAdmin)
```

#### c. Update `settings.py`
In `expenses_project/settings.py`, set the custom user model:
```python
AUTH_USER_MODEL = 'expenses.CustomUser'
```

### 4. Creating initial data for `CustomUser`
1. In the `data.json` file, add the initial data for `CustomUser`. Here is an example of how we can structure the data.json file:
    ```json
    [
        {
            "model": "expenses.customuser",
            "pk": 1,
            "fields": {
                "username": "david",
                "password": "password123",
                "email": "david@example.com",
                "address": "123 Main St",
                "phone_number": "1234567890",
                "is_staff": true,
                "is_superuser": true
            }
        },
        {
            "model": "expenses.customuser",
            "pk": 2,
            "fields": {
                "username": "ben",
                "password": "password456",
                "email": "ben@example.com",
                "address": "456 Elm St",
                "phone_number": "0987654321",
                "is_staff": false,
                "is_superuser": false
            }
        }
    ]
    ```
2. The issue is that the passwords in the fixture are stored in plain text, but Django expects them to be hashed So, when we load users from a fixture, we need to ensure the passwords are hashed. We can hash the passwords using the Django shell. Run the following command to open the Django shell:
```bash
python manage.py shell
```
Then, run the following code to generate hashed passwords:
```python
from django.contrib.auth.hashers import make_password

print(make_password('password123'))
print(make_password('password456'))
```
This will output hashed versions of `password123` and `password456`. Replace the plain text passwords with the hashed versions in the `data.json` file.

### 5. Testing the authentication system
Run the following commands to create and apply migrations for the custom user model:
```bash
python manage.py makemigrations
python manage.py migrate
```
Use the following command to load the initial data into the database:
```bash
python manage.py loaddata data.json
```

Start the development server:
```bash
python manage.py runserver
```
Open the web browser and navigate to:
- `http://127.0.0.1:8003/register/` for the registration page
- `http://127.0.0.1:8003/login/` for the login page
- `http://127.0.0.1:8003/logout/` for the logout page
