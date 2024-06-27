-- Create table for airports
CREATE TABLE IF NOT EXISTS airport (
    airport_code VARCHAR(10) PRIMARY KEY,
    airport_name VARCHAR(255),
    city VARCHAR(100),
    country VARCHAR(100),
    latitude DECIMAL(10, 6),
    longitude DECIMAL(10, 6),
    time_zone VARCHAR(50)
);

-- Create table for airlines
CREATE TABLE IF NOT EXISTS airline (
    iata_code VARCHAR(10) PRIMARY KEY,
    airline_name VARCHAR(255),
    airport_base VARCHAR(100),
    username VARCHAR(50),
    password VARCHAR(255)
);

-- Create table for flights
CREATE TABLE IF NOT EXISTS flight (
    id SERIAL PRIMARY KEY,
    flight_number VARCHAR(50),
    airline_iata_code VARCHAR(10) REFERENCES airline(iata_code),
    origin_airport_code VARCHAR(10) REFERENCES airport(airport_code),
    destination_airport_code VARCHAR(10) REFERENCES airport(airport_code),
    date DATE,
    departure_time_local TIME,
    landing_time_local TIME,
    remaining_tickets INT,
    flight_status VARCHAR(50)
);

-- Create table for customers
CREATE TABLE IF NOT EXISTS customer (
    customer_id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    address TEXT,
    phone_number VARCHAR(20),
    credit_card_number VARCHAR(20),
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create table for tickets
CREATE TABLE IF NOT EXISTS ticket (
    ticket_id VARCHAR(50) PRIMARY KEY,
    customer_id BIGINT REFERENCES customer(customer_id),
    flight_id BIGINT REFERENCES flight(id),
    class_type VARCHAR(50),
    seat_number VARCHAR(10),
    booking_status VARCHAR(50),
    price DECIMAL(10, 2)
);

-- Create table for administrators
CREATE TABLE IF NOT EXISTS administrator (
    admin_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);