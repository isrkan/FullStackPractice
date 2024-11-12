# Getting Started with Flask

This guide will walk you through setting up a simple Flask project and organizing the project structure. We'll provide instructions for setting up and running the project in VS Code.

## Step 1: Setting up the project

### Creating a new project in VS Code
1. Open VS Code and create a new folder for the project.
2. Open the terminal in VS Code by selecting **Terminal** and then **New Terminal**.
3. Navigate to the project directory in the terminal (if not already there).

### Setting up a virtual environment
A virtual environment is a self-contained directory that contains a Python installation for a particular version of Python, plus a number of additional packages. Any packages we install are confined to this environment and do not affect the system-wide Python installation.
1. In the terminal, run the following command to create a virtual environment named `flaskenv`:
```bash
python -m venv flaskenv
```
2. Activate the virtual environment:
```bash
.\flaskenv\Scripts\Activate
```

### Installing Flask
In the terminal, run the following commands to install `Flask`:
```bash
pip install Flask
```

## Step 2: Creating a simple Flask project
1. Create a new file named `app.py` and add the following code to set up a basic Flask application:
    ```python
    from flask import Flask

    app = Flask(__name__)

    @app.route('/')
    def home():
        return "Welcome to the Flask App!"

    if __name__ == '__main__':
        app.run(debug=True, port=5000)
    ```
    * `Flask(__name__)` creates an instance of the Flask class. This instance is the WSGI application.
    * `@app.route('/')` is a decorator that binds a function to a URL. Here, it binds the `home` function to the root URL `/`.
    * `app.run(debug=True, port=5000)` runs the application.

2. Start the Flask app using the Flask CLI. This command automatically detects the application in the current directory and runs it.:
    ```bash
    flask run
    ```
    Alternatively, we can run the Flask application using the Python interpreter directly by executin:
    ```bash
    python app.py
    ```
    This method provides more flexibility, especially when managing virtual environments or customizing startup configurations.
3. Open the web browser and navigate to `http://127.0.0.1:5000/` to see the running application.

## Step 3: Adding templates and static files
Flask uses Jinja2 templating to render HTML and includes support for serving static files.

Create a directory named `templates` and another named `static` in the root of your project. In the `static` directory, create a new directory named `styles`.

### Creating templates
In the `templates` directory, create a new file named `index.html` and add the following code:
```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>
        <link rel="stylesheet" href="{{ url_for('static', filename='styles/index.css') }}">
    </head>
    <body>
        <h1>Welcome to the Flask App!</h1>
    </body>
</html>
```
* `{{ url_for('static', filename='styles/index.css') }}` is a Jinja2 template expression that Flask uses to generate URLs for static files.

### Creating static files
In the `styles` directory, create a new file named `index.css` and add some basic styling:
```css
body {
    font-family: Arial, sans-serif;
}
h1 {
    color: #333;
}
```

### Updating views
1. Update `app.py` to use the template:
   ```python
   from flask import Flask, render_template

   app = Flask(__name__)

   @app.route('/')
   def home():
       return render_template('index.html')

   if __name__ == '__main__':
       app.run(debug=True, port=5000)
   ```

2. Start the Flask app by running:
    ```bash
    flask run
    ```
3. Open the web browser and navigate to `http://127.0.0.1:5000/` to see the running application.

## Step 4: Using data in views
1. Create a directory named `data` in the root of the project and place the data there.
2. Add to `app.py` to a view that displays expenses:
   ```python
   import json

   @app.route('/expenses')
   def expenses():
       with open('data/expenses.json') as f:
           expenses = json.load(f)
       return render_template('expenses.html', expenses=expenses)
   ```
   * `render_template('expenses.html', expenses=expenses)` passes the data to the template.
3. In the `templates` directory, create a new file named `expenses.html` and add the following code:
   ```html
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Expenses</title>
            <link rel="stylesheet" href="{{ url_for('static', filename='styles/index.css') }}">
        </head>
        <body>
            <h1>Expenses</h1>
            <ul>
                {% for expense in expenses %}
                <li>{{ expense.amount }} - {{ expense.category }} - {{ expense.description }}</li>
                {% endfor %}
            </ul>
        </body>
    </html>
   ```
   * `{% for expense in expenses %}` is a Jinja2 for-loop that iterates over the expenses list passed to the template.
4. Update the `index.html` file to include a link to the expenses page
5. Start the Flask app by running:
    ```bash
    flask run
    ```
6. Open the web browser and navigate to `http://127.0.0.1:5000/` to see the running application.

## Step 5: Using GET and POST methods for creating, editing, and deleting data examples

### Creating forms
1. Create templates for adding and editing expenses in `templates/add_expense.html` and `templates/edit_expense.html`. (Use the templates from code example)
2. Update `app.py` to include routes for creating, editing and deleting expenses. (Use the code from the `app.py` example)
3. Update `templates/expenses.html` to include links for adding, editing and deleting expenses. (Use the templates from code example)
4. Start the Flask app by running:
   ```bash
   flask run
   ```
5. Open the web browser and navigate to `http://127.0.0.1:5000/` to see the running application.