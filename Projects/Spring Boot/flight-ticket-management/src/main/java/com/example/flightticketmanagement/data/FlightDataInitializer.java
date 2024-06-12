package com.example.flightticketmanagement.data;

import com.example.flightticketmanagement.models.Airline;
import com.example.flightticketmanagement.models.Airport;
import com.example.flightticketmanagement.models.Flight;
import com.example.flightticketmanagement.repositories.AirlineRepository;
import com.example.flightticketmanagement.repositories.AirportRepository;
import com.example.flightticketmanagement.repositories.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.core.annotation.Order;

@Slf4j
@Component
@Order(3)
public class FlightDataInitializer {

    private AirportRepository airportRepository;
    private AirlineRepository airlineRepository;
    private FlightRepository flightRepository;

    @Autowired
    public FlightDataInitializer(AirportRepository airportRepository,
                                 AirlineRepository airlineRepository,
                                 FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.airlineRepository = airlineRepository;
        this.flightRepository = flightRepository;
    }

    @Bean
    @DependsOn({"preloadAirlineData"})
    public ApplicationRunner preloadFlightData() {
        return args -> {
            // Retrieve the related airports
            Airport bosAirport = airportRepository.findByAirportCode("BOS").orElseThrow(() -> new RuntimeException("Airport not found: JFK"));
            Airport jfkAirport = airportRepository.findByAirportCode("JFK").orElseThrow(() -> new RuntimeException("Airport not found: JFK"));
            Airport lhrAirport = airportRepository.findByAirportCode("LHR").orElseThrow(() -> new RuntimeException("Airport not found: LHR"));
            Airport tlvAirport = airportRepository.findByAirportCode("TLV").orElseThrow(() -> new RuntimeException("Airport not found: TLV"));
            Airport bomAirport = airportRepository.findByAirportCode("BOM").orElseThrow(() -> new RuntimeException("Airport not found: BOM"));
            Airport athAirport = airportRepository.findByAirportCode("ATH").orElseThrow(() -> new RuntimeException("Airport not found: ATH"));
            Airport cdgAirport = airportRepository.findByAirportCode("CDG").orElseThrow(() -> new RuntimeException("Airport not found: CDG"));
            Airport bruAirport = airportRepository.findByAirportCode("BRU").orElseThrow(() -> new RuntimeException("Airport not found: BRU"));
            Airport madAirport = airportRepository.findByAirportCode("MAD").orElseThrow(() -> new RuntimeException("Airport not found: MAD"));
            Airport fcoAirport = airportRepository.findByAirportCode("FCO").orElseThrow(() -> new RuntimeException("Airport not found: FCO"));
            Airport amsAirport = airportRepository.findByAirportCode("AMS").orElseThrow(() -> new RuntimeException("Airport not found: AMS"));
            Airport zrhAirport = airportRepository.findByAirportCode("ZRH").orElseThrow(() -> new RuntimeException("Airport not found: ZRH"));
            Airport mucAirport = airportRepository.findByAirportCode("MUC").orElseThrow(() -> new RuntimeException("Airport not found: MUC"));
            Airport vieAirport = airportRepository.findByAirportCode("VIE").orElseThrow(() -> new RuntimeException("Airport not found: VIE"));
            // Retrieve the related airlines
            Airline aaAirline = airlineRepository.findByIataCode("AA").orElseThrow(() -> new RuntimeException("Airline not found: AA"));
            Airline lyAirline = airlineRepository.findByIataCode("LY").orElseThrow(() -> new RuntimeException("Airline not found: LY"));
            Airline afAirline = airlineRepository.findByIataCode("AF").orElseThrow(() -> new RuntimeException("Airline not found: AF"));
            Airline aiAirline = airlineRepository.findByIataCode("AI").orElseThrow(() -> new RuntimeException("Airline not found: AI"));
            Airline snAirline = airlineRepository.findByIataCode("SN").orElseThrow(() -> new RuntimeException("Airline not found: SN"));
            Airline ibAirline = airlineRepository.findByIataCode("IB").orElseThrow(() -> new RuntimeException("Airline not found: IB"));
            Airline azAirline = airlineRepository.findByIataCode("AZ").orElseThrow(() -> new RuntimeException("Airline not found: AZ"));
            Airline klAirline = airlineRepository.findByIataCode("KL").orElseThrow(() -> new RuntimeException("Airline not found: KL"));
            Airline lxAirline = airlineRepository.findByIataCode("LX").orElseThrow(() -> new RuntimeException("Airline not found: LX"));
            Airline lhAirline = airlineRepository.findByIataCode("LH").orElseThrow(() -> new RuntimeException("Airline not found: LH"));
            Airline osAirline = airlineRepository.findByIataCode("OS").orElseThrow(() -> new RuntimeException("Airline not found: OS"));
            Airline a3Airline = airlineRepository.findByIataCode("A3").orElseThrow(() -> new RuntimeException("Airline not found: A3"));

            // Check if flights with the given flight numbers and dates already exist
            Optional<Flight> existingFlight1Optional = flightRepository.findByFlightNumberAndDate("AA101", LocalDate.of(2024, 7, 15));
            if (!existingFlight1Optional.isPresent()) {
                // Create the first flight object
                Flight aa1011507202408 = new Flight(null, "AA101", aaAirline, jfkAirport, lhrAirport, LocalDate.of(2024, 7, 15), LocalTime.of(8, 0), LocalTime.of(20, 0), 150);
                // Save the first flight object
                flightRepository.save(aa1011507202408);
            }
            Optional<Flight> existingFlight2Optional = flightRepository.findByFlightNumberAndDate("AA201", LocalDate.of(2024, 7, 10));
            if (!existingFlight2Optional.isPresent()) {
                Flight aa2011007202410 = new Flight(null, "AA201", aaAirline, bosAirport, lhrAirport, LocalDate.of(2024, 7, 10), LocalTime.of(10, 0), LocalTime.of(22, 0), 120);
                flightRepository.save(aa2011007202410);
            }
            Optional<Flight> existingFlightLY073 = flightRepository.findByFlightNumberAndDate("LY073", LocalDate.of(2024, 7, 1));
            if (!existingFlightLY073.isPresent()) {
                Flight ly073 = new Flight(null, "LY073", lyAirline, tlvAirport, bomAirport, LocalDate.of(2024, 7, 1), LocalTime.of(16, 30), LocalTime.of(23, 45), 90);
                flightRepository.save(ly073);
            }
            Optional<Flight> existingFlightAF1234 = flightRepository.findByFlightNumberAndDate("AF1234", LocalDate.of(2024, 7, 4));
            if (!existingFlightAF1234.isPresent()) {
                Flight af1234 = new Flight(null, "AF1234", afAirline, cdgAirport, athAirport, LocalDate.of(2024, 7, 4), LocalTime.of(16, 35), LocalTime.of(20, 50), 160);
                flightRepository.save(af1234);
            }
            Optional<Flight> existingFlightAI102 = flightRepository.findByFlightNumberAndDate("AI102", LocalDate.of(2024, 7, 2));
            if (!existingFlightAI102.isPresent()) {
                Flight ai102 = new Flight(null, "AI102", aiAirline, jfkAirport, bomAirport, LocalDate.of(2024, 7, 2), LocalTime.of(22, 0), LocalTime.of(22, 30), 100);
                flightRepository.save(ai102);
            }
            Optional<Flight> existingFlightSN401 = flightRepository.findByFlightNumberAndDate("SN401", LocalDate.of(2024, 8, 23));
            if (!existingFlightSN401.isPresent()) {
                Flight sn401 = new Flight(null, "SN401", snAirline, bruAirport, cdgAirport, LocalDate.of(2024, 8, 23), LocalTime.of(14, 30), LocalTime.of(16, 30), 190);
                flightRepository.save(sn401);
            }
            Optional<Flight> existingFlightIB456 = flightRepository.findByFlightNumberAndDate("IB456", LocalDate.of(2024, 7, 29));
            if (!existingFlightIB456.isPresent()) {
                Flight ib456 = new Flight(null, "IB456", ibAirline, madAirport, fcoAirport, LocalDate.of(2024, 7, 29), LocalTime.of(14, 45), LocalTime.of(16, 30), 190);
                flightRepository.save(ib456);
            }
            Optional<Flight> existingFlightAZ324 = flightRepository.findByFlightNumberAndDate("AZ324", LocalDate.of(2024, 7, 19));
            if (!existingFlightAZ324.isPresent()) {
                Flight az324 = new Flight(null, "AZ324", azAirline, fcoAirport, athAirport, LocalDate.of(2024, 7, 19), LocalTime.of(9, 30), LocalTime.of(13, 0), 160);
                flightRepository.save(az324);
            }
            Optional<Flight> existingFlightKL201 = flightRepository.findByFlightNumberAndDate("KL201", LocalDate.of(2024, 7, 26));
            if (!existingFlightKL201.isPresent()) {
                Flight kl201 = new Flight(null, "KL201", klAirline, amsAirport, fcoAirport, LocalDate.of(2024, 7, 26), LocalTime.of(10, 0), LocalTime.of(12, 30), 160);
                flightRepository.save(kl201);
            }
            Optional<Flight> existingFlightOS101 = flightRepository.findByFlightNumberAndDate("OS101", LocalDate.of(2024, 7, 5));
            if (!existingFlightOS101.isPresent()) {
                Flight os10105072024 = new Flight(null, "OS101", osAirline, vieAirport, cdgAirport, LocalDate.of(2024, 7, 5), LocalTime.of(13, 0), LocalTime.of(16, 0), 170);
                flightRepository.save(os10105072024);
            }
            Optional<Flight> existingFlightLH100 = flightRepository.findByFlightNumberAndDate("LH100", LocalDate.of(2024, 7, 1));
            if (!existingFlightLH100.isPresent()) {
                Flight lh10001072024 = new Flight(null, "LH100", lhAirline, mucAirport, jfkAirport, LocalDate.of(2024, 7, 1), LocalTime.of(10, 0), LocalTime.of(12, 30), 120);
                flightRepository.save(lh10001072024);
            }
            Optional<Flight> existingFlightLX348 = flightRepository.findByFlightNumberAndDate("LX348", LocalDate.of(2024, 7, 5));
            if (!existingFlightLX348.isPresent()) {
                Flight lx34805072024 = new Flight(null, "LX348", lxAirline, zrhAirport, lhrAirport, LocalDate.of(2024, 7, 5), LocalTime.of(9, 15), LocalTime.of(9, 55), 60);
                flightRepository.save(lx34805072024);
            }
            Optional<Flight> existingFlightA3 = flightRepository.findByFlightNumberAndDate("A3985", LocalDate.of(2024, 7, 1));
            if (!existingFlightA3.isPresent()) {
                Flight a3985 = new Flight(null, "A3985", a3Airline, athAirport, lhrAirport, LocalDate.of(2024, 7, 1), LocalTime.of(12, 0), LocalTime.of(14, 15), 120);
                flightRepository.save(a3985);
            }
            log.info("Flight data preloaded.");
        };
    }
}