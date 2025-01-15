package org.example.Entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table (name = " reservations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class reservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "flight_id")
    private Flight flight;

    @Column (nullable = false)
    private LocalDateTime reservationTime;

    @Column (nullable = false)
    private Integer numberOfSeats;

    @Column (name = " reservation_expiry")
    private LocalDateTime reservationExpiry;
}