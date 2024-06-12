package com.example.flightticketmanagement.data;

import com.example.flightticketmanagement.models.Airport;
import com.example.flightticketmanagement.repositories.AirportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

import java.util.Optional;

@Slf4j
@Component
@Order(1)
public class AirportDataInitializer {
    private final AirportRepository airportRepository;

    @Autowired
    public AirportDataInitializer(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }
    @Bean
    public ApplicationRunner preloadAirportData() {
        return args -> {
            Optional<Airport> existingAirportBOS = airportRepository.findByAirportCode("BOS");
            if (!existingAirportBOS.isPresent()) {
                // Step 1: Create the Airport objects
                Airport bosAirport = new Airport("BOS", "Logan International Airport", "Boston", "USA", 42.3656, -71.0096, "UTC-5");
                // Step 2: Save the Airport objects
                airportRepository.save(bosAirport);
            }
            Optional<Airport> existingAirportJFK = airportRepository.findByAirportCode("JFK");
            if (!existingAirportJFK.isPresent()) {
                Airport jfkAirport = new Airport("JFK", "John F. Kennedy International Airport", "New York", "USA", 40.6413, -73.7781, "UTC-5");
                airportRepository.save(jfkAirport);
            }
            Optional<Airport> existingAirportLHR = airportRepository.findByAirportCode("LHR");
            if (!existingAirportLHR.isPresent()) {
                Airport lhrAirport = new Airport("LHR", "Heathrow Airport", "London", "United Kingdom", 51.4700, -0.4543, "UTC+0");
                airportRepository.save(lhrAirport);
            }
            Optional<Airport> existingAirportTLV = airportRepository.findByAirportCode("TLV");
            if (!existingAirportTLV.isPresent()) {
                Airport tlvAirport = new Airport("TLV", "Ben Gurion Airport", "Tel Aviv", "Israel", 32.0055, 34.8854, "UTC+2");
                airportRepository.save(tlvAirport);
            }
            Optional<Airport> existingAirportBOM = airportRepository.findByAirportCode("BOM");
            if (!existingAirportBOM.isPresent()) {
                Airport bomAirport = new Airport("BOM", "Chhatrapati Shivaji Maharaj International Airport", "Mumbai", "India", 19.0896, 72.8656, "UTC+5:30");
                airportRepository.save(bomAirport);
            }
            Optional<Airport> existingAirportZRH = airportRepository.findByAirportCode("ZRH");
            if (!existingAirportZRH.isPresent()) {
                Airport zrhAirport = new Airport("ZRH", "Zurich Airport", "Zurich", "Switzerland", 47.4582, 8.5481, "UTC+1");
                airportRepository.save(zrhAirport);
            }
            Optional<Airport> existingAirportMUC = airportRepository.findByAirportCode("MUC");
            if (!existingAirportMUC.isPresent()) {
                Airport mucAirport = new Airport("MUC", "Munich Airport", "Munich", "Germany", 48.3538, 11.7861, "UTC+1");
                airportRepository.save(mucAirport);
            }
            Optional<Airport> existingAirportAMS = airportRepository.findByAirportCode("AMS");
            if (!existingAirportAMS.isPresent()) {
                Airport amsAirport = new Airport("AMS", "Amsterdam Schiphol Airport", "Amsterdam", "Netherlands", 52.3105, 4.7683, "UTC+1");
                airportRepository.save(amsAirport);
            }
            Optional<Airport> existingAirportMAD = airportRepository.findByAirportCode("MAD");
            if (!existingAirportMAD.isPresent()) {
                Airport madAirport = new Airport("MAD", "Adolfo Suárez Madrid–Barajas Airport", "Madrid", "Spain", 40.4983, -3.5676, "UTC+1");
                airportRepository.save(madAirport);
            }
            Optional<Airport> existingAirportFCO = airportRepository.findByAirportCode("FCO");
            if (!existingAirportFCO.isPresent()) {
                Airport fcoAirport = new Airport("FCO", "Leonardo da Vinci–Fiumicino Airport", "Rome", "Italy", 41.8003, 12.2389, "UTC+1");
                airportRepository.save(fcoAirport);
            }
            Optional<Airport> existingAirportBRU = airportRepository.findByAirportCode("BRU");
            if (!existingAirportBRU.isPresent()) {
                Airport bruAirport = new Airport("BRU", "Brussels Airport", "Brussels", "Belgium", 50.9014, 4.4844, "UTC+1");
                airportRepository.save(bruAirport);
            }
            Optional<Airport> existingAirportCDG = airportRepository.findByAirportCode("CDG");
            if (!existingAirportCDG.isPresent()) {
                Airport cdgAirport = new Airport("CDG", "Charles de Gaulle Airport", "Paris", "France", 49.0097, 2.5479, "UTC+1");
                airportRepository.save(cdgAirport);
            }
            Optional<Airport> existingAirportVIE = airportRepository.findByAirportCode("VIE");
            if (!existingAirportVIE.isPresent()) {
                Airport vieAirport = new Airport("VIE", "Vienna International Airport", "Vienna", "Austria", 48.1103, 16.5697, "UTC+1");
                airportRepository.save(vieAirport);
            }
            Optional<Airport> existingAirportATH = airportRepository.findByAirportCode("ATH");
            if (!existingAirportATH.isPresent()) {
                Airport athAirport = new Airport("ATH", "Athens International Airport", "Athens", "Greece", 37.9356, 23.9484, "UTC+2");
                airportRepository.save(athAirport);
            }
            log.info("Airport data preloaded.");
        };
    }
}