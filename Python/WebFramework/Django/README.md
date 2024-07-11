# Getting Started with Django

This guide will walk you through setting up a simple Django project, connecting it to PostgreSQL, and organizing the project structure. We'll use `pip` as the package manager and provide instructions for setting up and running the project in PyCharm.

## Step 1: Setting up the project

### Creating a new project in PyCharm
1. Open PyCharm and select **File** and then **New Project**.
2. In the **New Project** dialog, configure the following:
   - **Location:** Choose the directory where to create the project.
   - **Python Interpreter:** Select **New environment using** and choose `Virtualenv`. PyCharm will automatically create a virtual environment for the project.
   - Uncheck the checkbox for creating main.py welcome script
   - Click **Create** to set up the new project.

### Installing Django and psycopg2 in PyCharm
1. Inside PyCharm, open the terminal by clicking on **Terminal** at the bottom of the window.

2. In the terminal, run the following commands to Install `Django` and `psycopg2`:
     ```bash
     pip install django psycopg2-binary
     ```

### Creating the Django project
1. In the PyCharm terminal, navigate to the project directory (if not already there).
2. Run the following command to create a new Django project:
   ```bash
    django-admin startproject expenses_project .
   ```
   - Note the dot `.` at the end of the command, which indicates the current directory.

### Creating a Django app
1. In the PyCharm terminal, run the following command to create a new Django app:
   ```bash
   python manage.py startapp expenses
   ```
2. Configure the app settings in `expenses_project/settings.py`:
   ```python
   INSTALLED_APPS = [
        "django.contrib.admin",
        "django.contrib.auth",
        "django.contrib.contenttypes",
        "django.contrib.sessions",
        "django.contrib.messages",
        "django.contrib.staticfiles",
        "expenses",
    ]
   ```

### Change the default port (Optional):
1. Open the `manage.py` file in the root directory of your Django project.
2. Modify the `main()` function to include the default port configuration to `8003`. Update the code to look like this:
   ```python
    def main():
        """Run administrative tasks."""
        os.environ.setdefault("DJANGO_SETTINGS_MODULE", "expenses_project.settings")
        try:
            from django.core.management import execute_from_command_line
        except ImportError as exc:
            raise ImportError(
                "Couldn't import Django. Are you sure it's installed and "
                "available on your PYTHONPATH environment variable? Did you "
                "forget to activate a virtual environment?"
            ) from exc
        args = sys.argv
        if 'runserver' in args:
            default_port = '8003'
            if len(args) == 2:
                args.append(default_port)
            elif len(args) == 3 and args[2].isdigit():
                args[2] = default_port
            else:
                args.insert(2, default_port)
        execute_from_command_line(args)
   ```

## Step 2: Configuring the Database
1. Create a new database for the project or make sure the desired database exists.
2. Configure the database settings in `expenses_project/settings.py`:
   ```python
   DATABASES = {
      "default": {
         "ENGINE": "django.db.backends.postgresql",
         "NAME": "expenses",
         "USER": "postgres",
         "PASSWORD": "postgres1",
         "HOST": "localhost",
         "PORT": "5432",
      }
   }
   ```

## Step 3: Configuring templates and static files

### Adjusting settings.py
1. Open `expenses_project/settings.py`.
2. Add the `templates` and `static` directories to the `TEMPLATES` and `STATICFILES_DIRS` settings:
   ```python
   import os

   BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
   TEMPLATE_DIR = os.path.join(BASE_DIR,"templates")
   STATIC_DIR = os.path.join(BASE_DIR,"static")

   TEMPLATES = [
        {
            "BACKEND": "django.template.backends.django.DjangoTemplates",
            "DIRS": [TEMPLATE_DIR,],
            "APP_DIRS": True,
            "OPTIONS": {
                "context_processors": [
                    "django.template.context_processors.debug",
                    "django.template.context_processors.request",
                    "django.contrib.auth.context_processors.auth",
                    "django.contrib.messages.context_processors.messages",
                ],
            },
        },
    ]

   STATIC_URL = "static/"
   STATICFILES_DIRS = [
        STATIC_DIR,
    ]
   ```

### Creating the templates directory
1. Create a `templates` directory inside the base directory.
2. Create an `expenses` directory inside the `templates` directory.

### Creating the static directory
1. Create a `static` directory inside the base directory.
2. Create a `styles` directory inside the `static` directory.
3. Create an `expenses` directory inside the `styles` directory for the `expenses` app.
4. Create an `images` directory inside the `static` directory.


## Step 4: Creating models and registering it
1. Define the `Expense` model in `expenses/models.py` (Add the code from the `models.py` example)
2. Register the `Expense` model in `expenses/admin.py` (Add the code from the `admin.py` example)

## Step 5: Creating views, templates and styles
1. Create views in `expenses/views.py` (Add the code from the `views.py` example)
2. Create URL patterns in `expenses/urls.py` (create the file if it doesn't exist and add the code from the `urls.py` example):
3. Update the main `urls.py` in `expenses_project/urls.py` (Use the code from the main `urls.py` example)
4. Create templates: Create HTML files in the `templates/expenses` directory (Use the templates from the main `templates/expenses` example)
5. Create styles: Create CSS files in the `static/styles/expenses` directory (Use the styles from the main `static/styles/expenses` example)

## Step 6: Running migrations

#### Option 1: Creating new initial data in the database
1. Create and apply database migrations:
    ```bash
    python manage.py makemigrations
    python manage.py migrate
    ```
2. Create the `fixtures` directory under the `expenses` app directory:
3. Create a JSON file for initial data, e.g., `data.json` in the `fixtures` directory
    ```json
    [
        {
            "model": "expenses.expense",
            "pk": 1,
            "fields": {
                "amount": "100.00",
                "category": "Food",
                "description": "Lunch",
                "approved": false,
                "added_by": "David"
            }
        },
        {
            "model": "expenses.expense",
            "pk": 2,
            "fields": {
                "amount": "500.00",
                "category": "Holidays",
                "description": "Flight",
                "approved": false,
                "added_by": "Ben"
            }
        }
    ]
    ```
4. Load the data into the database:
    ```bash
    python manage.py loaddata data.json
    ```
**Note**: Initializing the data with the data.json fixture works better with the default SQLite database compared to PostgreSQL. Here's why:

#### Option 2: Data already exists in the databse
In our use-cse, the table already exists.
1. Ensure that the Expense model matches the existing database schema. If needed, adjust the model fields in `expenses/models.py` to reflect the existing schema.
2. We need to ensure that Django does not try to create it again. We can do this by creating an initial empty migration:
```bash
   python manage.py makemigrations --empty expenses --name initial
   python manage.py migrate
```
This will create an initial migration file in the `expenses/migrations` directory that we can then edit to indicate that the table already exists.

## Step 7: Running the development server

1. Start the development server:
   ```bash
   python manage.py runserver
   ```
2. Open your web browser and navigate to `http://127.0.0.1:8003/` to see your running application.

## Step 8: Using Django admin

1. Create a superuser to access the Django admin interface:
   ```bash
   python manage.py createsuperuser
   ```
2. Follow the prompts to set up the superuser account.
3. Start the development server:
   ```bash
   python manage.py runserver
   ```
4. Navigate to `http://127.0.0.1:8003/admin` and log in with the superuser credentials to manage the data.
