package org.example.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "flights")

public class flightEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinColumn (name = "origin_airport_id")
    private Airport originAirport;

    @ManyToMany
    @JoinColumn (name = "destination_airport_id")
    private Airport destinationAirport;

    @Column (nullable = false)
    private LocalDateTime departureTime;

    @Column (nullable = false)
    private Integer availableSeats;

    @Column (nullable = false)
    private Boolean available;

    @OneToMany (mappedBy = "flight")
    private List<Reservation> reservations;
}
