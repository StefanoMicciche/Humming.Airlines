package org.example.DTOs;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.example.Enums.FlightStatus;

import java.time.LocalDateTime;

@Data
@Builder

public class FlightDTO {

    private Long id;
    private String flightNumber;
    private String originAirportCode;
    private String destinationAirportCode;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer availableSeats;
    private Double basePrice;
    private boolean available;
    private FlightStatus status;

    @Data
    @Builder

    public static class CreateDTO {

        @NotBlank(message = "Flight number is required")
        private String flightNumber;

        @NotBlank(message = "Origin airport is required")
        private String originAirportCode;

        @NotBlank(message = "Destination airport is required")
        private String destinationAirportCode;

        @NotNull(message = "Departure time is required")
        @Future(message = "Departure time must be in the future")
        private LocalDateTime departureTime;

        @NotNull(message = "Arrival time is required")
        @Future(message = "Arrival time must be in the future")
        private LocalDateTime arrivalTime;

        @NotNull(message = "Total seats is required")
        @Min(1)
        private Integer totalSeats;

        @NotNull(message = "base price is required")
        @Positive
        private Double basePrice;

    }

    @Data
    @Builder

    public static class SearchDTO {
        @NotBlank(message = "Origin airport code is required")
        private String originAirportCode;

        @NotBlank(message = "Destination airport code is required")
        private String destinationAirportCode;

        @NotNull(message = "Departure date is required")
        @Future
        private LocalDateTime departureDate;

        @Min(1)
        private Integer numberOfPassengers;

        private Double maxPrice;
    }

    @Data
    @Builder
    public static class Reservation {
        private Long id;
        private Long userId;
        private Long flightId;
        private LocalDateTime reservationTime;
        private Integer numberOfSeats;
        private String reservationStatus;
        private LocalDateTime reservationExpiry;
        private FlightDTO flight;
        private Double totalPrice;

    }

    @Data
    @Builder

    public static class ReservationCreate {
        @NotNull(message = "Flight ID is required")
        private Long flightId;

        @NotNull(message = "Number of seats is required")
        private Integer numberOfSeats;
    }

    @Data
    @Builder

    public static class ReservationUpdate {
        private String reservationStatus;
        private Integer numberOfSeats;
    }
}

