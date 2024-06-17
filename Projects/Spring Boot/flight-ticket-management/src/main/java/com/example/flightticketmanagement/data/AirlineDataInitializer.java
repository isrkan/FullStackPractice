package com.example.flightticketmanagement.data;

import com.example.flightticketmanagement.models.Airline;
import com.example.flightticketmanagement.repositories.AirlineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

import java.util.Optional;

// Class for preloading airline data into the database when the application starts
@Slf4j
@Component
@Order(2)
public class AirlineDataInitializer {
    private final AirlineRepository airlineRepository;

    @Autowired
    public AirlineDataInitializer(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Bean
    @DependsOn({"preloadAirportData"}) // Ensures this bean is initialized after preloadAirportData
    public ApplicationRunner preloadAirlineData() {
        return args -> {
            // Check if the AA airline exists; if not, create and save it
            Optional<Airline> existingAirlineAA = airlineRepository.findByIataCode("AA");
            if (!existingAirlineAA.isPresent()) {
                // Step 1: Create the airline object
                Airline aaAirline = new Airline("AA", "American Airlines", "DFW", "userAmerican", "passAmerican123");
                // Step 2: Save the airline object
                airlineRepository.save(aaAirline);
            }
            // Check if the BA airline exists; if not, create and save it
            Optional<Airline> existingAirlineBA = airlineRepository.findByIataCode("BA");
            if (!existingAirlineBA.isPresent()) {
                Airline baAirline = new Airline("BA", "British Airways", "LHR", "userBritish", "passBritish123");
                airlineRepository.save(baAirline);
            }
            Optional<Airline> existingAirlineLY = airlineRepository.findByIataCode("LY");
            if (!existingAirlineLY.isPresent()) {
                Airline lyAirline = new Airline("LY", "El Al", "TLV", "userElAl", "passElAl123");
                airlineRepository.save(lyAirline);
            }
            Optional<Airline> existingAirlineAI = airlineRepository.findByIataCode("AI");
            if (!existingAirlineAI.isPresent()) {
                Airline aiAirline = new Airline("AI", "Air India", "BOM", "userAirIndia", "passAirIndia123");
                airlineRepository.save(aiAirline);
            }
            Optional<Airline> existingAirlineLX = airlineRepository.findByIataCode("LX");
            if (!existingAirlineLX.isPresent()) {
                Airline lxAirline = new Airline("LX", "Swiss Air Lines", "ZRH", "userSwiss", "passSwiss123");
                airlineRepository.save(lxAirline);
            }
            Optional<Airline> existingAirlineLH = airlineRepository.findByIataCode("LH");
            if (!existingAirlineLH.isPresent()) {
                Airline lhAirline = new Airline("LH", "Lufthansa", "FRA", "userLufthansa", "passLufthansa123");
                airlineRepository.save(lhAirline);
            }
            Optional<Airline> existingAirlineKL = airlineRepository.findByIataCode("KL");
            if (!existingAirlineKL.isPresent()) {
                Airline klAirline = new Airline("KL", "KLM", "AMS", "userKLM", "passKLM123");
                airlineRepository.save(klAirline);
            }
            Optional<Airline> existingAirlineIB = airlineRepository.findByIataCode("IB");
            if (!existingAirlineIB.isPresent()) {
                Airline ibAirline = new Airline("IB", "Iberia", "MAD", "userIberia", "passIberia123");
                airlineRepository.save(ibAirline);
            }
            Optional<Airline> existingAirlineAZ = airlineRepository.findByIataCode("AZ");
            if (!existingAirlineAZ.isPresent()) {
                Airline azAirline = new Airline("AZ", "Alitalia", "FCO", "userAlitalia", "passAlitalia123");
                airlineRepository.save(azAirline);
            }
            Optional<Airline> existingAirlineOS = airlineRepository.findByIataCode("OS");
            if (!existingAirlineOS.isPresent()) {
                Airline osAirline = new Airline("OS", "Austrian Airlines", "VIE", "userAustrian", "passAustrian123");
                airlineRepository.save(osAirline);
            }
            Optional<Airline> existingAirlineSN = airlineRepository.findByIataCode("SN");
            if (!existingAirlineSN.isPresent()) {
                Airline snAirline = new Airline("SN", "Brussels Airlines", "BRU", "userBrussels", "passBrussels123");
                airlineRepository.save(snAirline);
            }
            Optional<Airline> existingAirlineAF = airlineRepository.findByIataCode("AF");
            if (!existingAirlineAF.isPresent()) {
                Airline afAirline = new Airline("AF", "Air France", "CDG", "userAirFrance", "passAirFrance123");
                airlineRepository.save(afAirline);
            }
            Optional<Airline> existingAirlineA3 = airlineRepository.findByIataCode("A3");
            if (!existingAirlineA3.isPresent()) {
                Airline a3Airline = new Airline("A3", "Aegean Airlines", "ATH", "userAegean", "passAegean123");
                airlineRepository.save(a3Airline);
            }
            log.info("Airline data preloaded.");
        };
    }
}