# Use the official Python image from the Docker Hub
FROM python:3.9.13-slim

# Set the working directory within the Docker container. All subsequent commands will be executed in this directory
WORKDIR /train-ticket-management

# Install system dependencies that are required for compiling and linking some Python packages
# Some Python packages have components written in C or C++ that need to be compiled during installation such as mysqlclient package
RUN apt-get update && \
    apt-get install -y --no-install-recommends gcc \
    libc-dev \
    libmariadb-dev \
    pkg-config

# Copy the requirements file from the local host to the container's working directory
COPY requirements.txt /train-ticket-management/

# Install the dependencies specified in the requirements file
RUN pip install --no-cache-dir -r requirements.txt

# Copy the entire project directory from the local host to the container's working directory
COPY . .

# Set the environment variable to include the main project directory in the PYTHONPATH
WORKDIR /train-ticket-management/train_ticket_management

# Define the default command to run when the container starts
CMD ["gunicorn", "--bind", "0.0.0.0:8000", "train_ticket_management.wsgi:application"]