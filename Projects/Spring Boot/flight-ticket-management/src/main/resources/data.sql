-- data.sql initializes the data when the app starts

-- Insert initial airport data if not exists
INSERT INTO airport (airport_code, airport_name, city, country, latitude, longitude, time_zone)
SELECT 'BOS', 'Logan International Airport', 'Boston', 'USA', 42.3656, -71.0096, 'UTC-5'
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE airport_code = 'BOS');

INSERT INTO airport (airport_code, airport_name, city, country, latitude, longitude, time_zone)
SELECT 'JFK', 'John F. Kennedy International Airport', 'New York', 'USA', 40.6413, -73.7781, 'UTC-5'
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE airport_code = 'JFK');

INSERT INTO airport (airport_code, airport_name, city, country, latitude, longitude, time_zone)
SELECT 'LHR', 'Heathrow Airport', 'London', 'United Kingdom', 51.4700, -0.4543, 'UTC+0'
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE airport_code = 'LHR');

INSERT INTO airport (airport_code, airport_name, city, country, latitude, longitude, time_zone)
SELECT 'TLV', 'Ben Gurion Airport', 'Tel Aviv', 'Israel', 32.0055, 34.8854, 'UTC+2'
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE airport_code = 'TLV');

INSERT INTO airport (airport_code, airport_name, city, country, latitude, longitude, time_zone)
SELECT 'BOM', 'Chhatrapati Shivaji Maharaj International Airport', 'Mumbai', 'India', 19.0896, 72.8656, 'UTC+5:30'
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE airport_code = 'BOM');

INSERT INTO airport (airport_code, airport_name, city, country, latitude, longitude, time_zone)
SELECT 'ZRH', 'Zurich Airport', 'Zurich', 'Switzerland', 47.4582, 8.5481, 'UTC+1'
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE airport_code = 'ZRH');

INSERT INTO airport (airport_code, airport_name, city, country, latitude, longitude, time_zone)
SELECT 'MUC', 'Munich Airport', 'Munich', 'Germany', 48.3538, 11.7861, 'UTC+1'
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE airport_code = 'MUC');

INSERT INTO airport (airport_code, airport_name, city, country, latitude, longitude, time_zone)
SELECT 'AMS', 'Amsterdam Schiphol Airport', 'Amsterdam', 'Netherlands', 52.3105, 4.7683, 'UTC+1'
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE airport_code = 'AMS');

INSERT INTO airport (airport_code, airport_name, city, country, latitude, longitude, time_zone)
SELECT 'MAD', 'Adolfo Suárez Madrid–Barajas Airport', 'Madrid', 'Spain', 40.4983, -3.5676, 'UTC+1'
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE airport_code = 'MAD');

INSERT INTO airport (airport_code, airport_name, city, country, latitude, longitude, time_zone)
SELECT 'FCO', 'Leonardo da Vinci–Fiumicino Airport', 'Rome', 'Italy', 41.8003, 12.2389, 'UTC+1'
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE airport_code = 'FCO');

INSERT INTO airport (airport_code, airport_name, city, country, latitude, longitude, time_zone)
SELECT 'BRU', 'Brussels Airport', 'Brussels', 'Belgium', 50.9014, 4.4844, 'UTC+1'
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE airport_code = 'BRU');

INSERT INTO airport (airport_code, airport_name, city, country, latitude, longitude, time_zone)
SELECT 'CDG', 'Charles de Gaulle Airport', 'Paris', 'France', 49.0097, 2.5479, 'UTC+1'
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE airport_code = 'CDG');

INSERT INTO airport (airport_code, airport_name, city, country, latitude, longitude, time_zone)
SELECT 'VIE', 'Vienna International Airport', 'Vienna', 'Austria', 48.1103, 16.5697, 'UTC+1'
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE airport_code = 'VIE');

INSERT INTO airport (airport_code, airport_name, city, country, latitude, longitude, time_zone)
SELECT 'ATH', 'Athens International Airport', 'Athens', 'Greece', 37.9356, 23.9484, 'UTC+2'
WHERE NOT EXISTS (SELECT 1 FROM airport WHERE airport_code = 'ATH');




-- Insert initial airline data if not exists
INSERT INTO airline (iata_code, airline_name, airport_base, username, password)
SELECT 'AA', 'American Airlines', 'DFW', 'userAmerican', 'passAmerican123'
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE iata_code = 'AA');

INSERT INTO airline (iata_code, airline_name, airport_base, username, password)
SELECT 'BA', 'British Airways', 'LHR', 'userBritish', 'passBritish123'
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE iata_code = 'BA');

INSERT INTO airline (iata_code, airline_name, airport_base, username, password)
SELECT 'LY', 'El Al', 'TLV', 'userElAl', 'passElAl123'
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE iata_code = 'LY');

INSERT INTO airline (iata_code, airline_name, airport_base, username, password)
SELECT 'AI', 'Air India', 'BOM', 'userAirIndia', 'passAirIndia123'
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE iata_code = 'AI');

INSERT INTO airline (iata_code, airline_name, airport_base, username, password)
SELECT 'LX', 'Swiss Air Lines', 'ZRH', 'userSwiss', 'passSwiss123'
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE iata_code = 'LX');

INSERT INTO airline (iata_code, airline_name, airport_base, username, password)
SELECT 'LH', 'Lufthansa', 'FRA', 'userLufthansa', 'passLufthansa123'
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE iata_code = 'LH');

INSERT INTO airline (iata_code, airline_name, airport_base, username, password)
SELECT 'KL', 'KLM', 'AMS', 'userKLM', 'passKLM123'
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE iata_code = 'KL');

INSERT INTO airline (iata_code, airline_name, airport_base, username, password)
SELECT 'IB', 'Iberia', 'MAD', 'userIberia', 'passIberia123'
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE iata_code = 'IB');

INSERT INTO airline (iata_code, airline_name, airport_base, username, password)
SELECT 'AZ', 'Alitalia', 'FCO', 'userAlitalia', 'passAlitalia123'
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE iata_code = 'AZ');

INSERT INTO airline (iata_code, airline_name, airport_base, username, password)
SELECT 'OS', 'Austrian Airlines', 'VIE', 'userAustrian', 'passAustrian123'
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE iata_code = 'OS');

INSERT INTO airline (iata_code, airline_name, airport_base, username, password)
SELECT 'SN', 'Brussels Airlines', 'BRU', 'userBrussels', 'passBrussels123'
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE iata_code = 'SN');

INSERT INTO airline (iata_code, airline_name, airport_base, username, password)
SELECT 'AF', 'Air France', 'CDG', 'userAirFrance', 'passAirFrance123'
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE iata_code = 'AF');

INSERT INTO airline (iata_code, airline_name, airport_base, username, password)
SELECT 'A3', 'Aegean Airlines', 'ATH', 'userAegean', 'passAegean123'
WHERE NOT EXISTS (SELECT 1 FROM airline WHERE iata_code = 'A3');




-- Insert initial customer data if not exists
INSERT INTO customer (customer_id, first_name, last_name, address, phone_number, credit_card_number, username, password)
SELECT 1, 'Tom', 'Hanks', '123 Hollywood Blvd, Los Angeles, CA 90038', '+13105551234', '4111111111111111', 'tomhanks123', '1928374657483921'
WHERE NOT EXISTS (SELECT 1 FROM customer WHERE username = 'tomhanks123');

INSERT INTO customer (customer_id, first_name, last_name, address, phone_number, credit_card_number, username, password)
SELECT 2, 'Leonardo', 'DiCaprio', '567 Vine St, Los Angeles, CA 90038', '+13105555678', '4012888888881881', 'ldicaprio567', '4839204756019283'
WHERE NOT EXISTS (SELECT 1 FROM customer WHERE username = 'ldicaprio567');




-- Insert initial administrator data if not exists
INSERT INTO administrator (admin_id, username, password, first_name, last_name)
SELECT 1, 'admin', 'admin', 'admin', 'admin'
WHERE NOT EXISTS (SELECT 1 FROM administrator WHERE username = 'admin');




-- Insert initial flight data if not exists
INSERT INTO flight (flight_number, airline_iata_code, origin_airport_code, destination_airport_code, date, departure_time_local, landing_time_local, remaining_tickets, flight_status)
SELECT 'AA101', (SELECT iata_code FROM airline WHERE iata_code = 'AA'), (SELECT airport_code FROM airport WHERE airport_code = 'JFK'), (SELECT airport_code FROM airport WHERE airport_code = 'LHR'), '2024-07-15', '08:00', '20:00', 150, 'SCHEDULED'
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'AA101' AND date = '2024-07-15');

INSERT INTO flight (flight_number, airline_iata_code, origin_airport_code, destination_airport_code, date, departure_time_local, landing_time_local, remaining_tickets, flight_status)
SELECT 'AA201', (SELECT iata_code FROM airline WHERE iata_code = 'AA'), (SELECT airport_code FROM airport WHERE airport_code = 'BOS'), (SELECT airport_code FROM airport WHERE airport_code = 'LHR'), '2024-07-10', '10:00', '22:00', 120, 'SCHEDULED'
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'AA201' AND date = '2024-07-10');

INSERT INTO flight (flight_number, airline_iata_code, origin_airport_code, destination_airport_code, date, departure_time_local, landing_time_local, remaining_tickets, flight_status)
SELECT 'LY073', (SELECT iata_code FROM airline WHERE iata_code = 'LY'), (SELECT airport_code FROM airport WHERE airport_code = 'TLV'), (SELECT airport_code FROM airport WHERE airport_code = 'BOM'), '2024-07-01', '16:30', '23:45', 90, 'SCHEDULED'
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'LY073' AND date = '2024-07-01');

INSERT INTO flight (flight_number, airline_iata_code, origin_airport_code, destination_airport_code, date, departure_time_local, landing_time_local, remaining_tickets, flight_status)
SELECT 'AF1234', (SELECT iata_code FROM airline WHERE iata_code = 'AF'), (SELECT airport_code FROM airport WHERE airport_code = 'CDG'), (SELECT airport_code FROM airport WHERE airport_code = 'ATH'), '2024-07-04', '16:35', '20:50', 160, 'SCHEDULED'
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'AF1234' AND date = '2024-07-04');

INSERT INTO flight (flight_number, airline_iata_code, origin_airport_code, destination_airport_code, date, departure_time_local, landing_time_local, remaining_tickets, flight_status)
SELECT 'AI102', (SELECT iata_code FROM airline WHERE iata_code = 'AI'), (SELECT airport_code FROM airport WHERE airport_code = 'JFK'), (SELECT airport_code FROM airport WHERE airport_code = 'BOM'), '2024-07-02', '22:00', '22:30', 100, 'SCHEDULED'
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'AI102' AND date = '2024-07-02');

INSERT INTO flight (flight_number, airline_iata_code, origin_airport_code, destination_airport_code, date, departure_time_local, landing_time_local, remaining_tickets, flight_status)
SELECT 'SN401', (SELECT iata_code FROM airline WHERE iata_code = 'SN'), (SELECT airport_code FROM airport WHERE airport_code = 'BRU'), (SELECT airport_code FROM airport WHERE airport_code = 'CDG'), '2024-08-23', '14:30', '16:30', 190, 'SCHEDULED'
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'SN401' AND date = '2024-08-23');

INSERT INTO flight (flight_number, airline_iata_code, origin_airport_code, destination_airport_code, date, departure_time_local, landing_time_local, remaining_tickets, flight_status)
SELECT 'IB456', (SELECT iata_code FROM airline WHERE iata_code = 'IB'), (SELECT airport_code FROM airport WHERE airport_code = 'MAD'), (SELECT airport_code FROM airport WHERE airport_code = 'FCO'), '2024-07-29', '14:45', '16:30', 190, 'SCHEDULED'
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'IB456' AND date = '2024-07-29');

INSERT INTO flight (flight_number, airline_iata_code, origin_airport_code, destination_airport_code, date, departure_time_local, landing_time_local, remaining_tickets, flight_status)
SELECT 'AZ324', (SELECT iata_code FROM airline WHERE iata_code = 'AZ'), (SELECT airport_code FROM airport WHERE airport_code = 'FCO'), (SELECT airport_code FROM airport WHERE airport_code = 'ATH'), '2024-07-19', '09:30', '13:00', 160, 'SCHEDULED'
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'AZ324' AND date = '2024-07-19');

INSERT INTO flight (flight_number, airline_iata_code, origin_airport_code, destination_airport_code, date, departure_time_local, landing_time_local, remaining_tickets, flight_status)
SELECT 'KL201', (SELECT iata_code FROM airline WHERE iata_code = 'KL'), (SELECT airport_code FROM airport WHERE airport_code = 'AMS'), (SELECT airport_code FROM airport WHERE airport_code = 'FCO'), '2024-07-26', '10:00', '12:30', 160, 'SCHEDULED'
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'KL201' AND date = '2024-07-26');

INSERT INTO flight (flight_number, airline_iata_code, origin_airport_code, destination_airport_code, date, departure_time_local, landing_time_local, remaining_tickets, flight_status)
SELECT 'OS101', (SELECT iata_code FROM airline WHERE iata_code = 'OS'), (SELECT airport_code FROM airport WHERE airport_code = 'VIE'), (SELECT airport_code FROM airport WHERE airport_code = 'CDG'), '2024-07-05', '13:00', '16:00', 170, 'SCHEDULED'
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'OS101' AND date = '2024-07-05');

INSERT INTO flight (flight_number, airline_iata_code, origin_airport_code, destination_airport_code, date, departure_time_local, landing_time_local, remaining_tickets, flight_status)
SELECT 'LH100', (SELECT iata_code FROM airline WHERE iata_code = 'LH'), (SELECT airport_code FROM airport WHERE airport_code = 'MUC'), (SELECT airport_code FROM airport WHERE airport_code = 'JFK'), '2024-07-01', '10:00', '12:30', 120, 'SCHEDULED'
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'LH100' AND date = '2024-07-01');

INSERT INTO flight (flight_number, airline_iata_code, origin_airport_code, destination_airport_code, date, departure_time_local, landing_time_local, remaining_tickets, flight_status)
SELECT 'LX348', (SELECT iata_code FROM airline WHERE iata_code = 'LX'), (SELECT airport_code FROM airport WHERE airport_code = 'ZRH'), (SELECT airport_code FROM airport WHERE airport_code = 'LHR'), '2024-07-05', '09:15', '09:55', 60, 'SCHEDULED'
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'LX348' AND date = '2024-07-05');

INSERT INTO flight (flight_number, airline_iata_code, origin_airport_code, destination_airport_code, date, departure_time_local, landing_time_local, remaining_tickets, flight_status)
SELECT 'A3985', (SELECT iata_code FROM airline WHERE iata_code = 'A3'), (SELECT airport_code FROM airport WHERE airport_code = 'ATH'), (SELECT airport_code FROM airport WHERE airport_code = 'LHR'), '2024-07-01', '12:00', '14:15', 120, 'SCHEDULED'
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_number = 'A3985' AND date = '2024-07-01');




-- Insert initial ticket data if not exists
INSERT INTO ticket (ticket_id, customer_id, flight_id, class_type, seat_number, booking_status, price)
SELECT 'TCKT1A2B3C4D5', (SELECT customer_id FROM customer WHERE username = 'tomhanks123'), (SELECT id FROM flight WHERE flight_number = 'AA101' AND date = '2024-07-15'), 'ECONOMY', '12A', 'CONFIRMED', 450.00
WHERE NOT EXISTS (SELECT 1 FROM ticket WHERE ticket_id = 'TCKT1A2B3C4D5');

INSERT INTO ticket (ticket_id, customer_id, flight_id, class_type, seat_number, booking_status, price)
SELECT 'TCKT6E7F8G9H0', (SELECT customer_id FROM customer WHERE username = 'tomhanks123'), (SELECT id FROM flight WHERE flight_number = 'LY073' AND date = '2024-07-01'), 'BUSINESS', '5B', 'BOOKED', 860.00
WHERE NOT EXISTS (SELECT 1 FROM ticket WHERE ticket_id = 'TCKT6E7F8G9H0');