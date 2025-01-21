package org.example.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "airports")
public class Airport {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false, unique = true)
    private String code;

    @Column (nullable = false)
    private String city;

    @Column (nullable = false)
    private String country;

    @OneToMany (mappedBy = "originAirport")
    private List<Flight> departureFlights;

    @OneToMany (mappedBy = "destinationAirport")
    private List<Flight> arrivalFlights;
}
