package org.example.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.Entities.userEntity;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Repository

public interface airLineRepository extends JpaRepository <userEntity.User, Long>{
    Optional <userEntity.User> findByUserName(String username);
    Optional <userEntity.User> findByEmail (String email);

    boolean existsByUserName (String username);
    boolean existsByEmail (String email);

    @Query ("SELECT u FROM u WHERE u role = :role")
    List <userEntity.User> findByRole (@Param("role")Role role);
}

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>{
    List<Flight> findByAvailableTrue();

    List<Flight> findByOriginAirportAndDestinationAirport(
            Airport origin,
            Airport destination
    );

    @Query ("SELECT f FROM  FLight f " +
            "WHERE f.originAirport.code = :origin " +
            "AND f.destinationAirport.code = :destination " +
            "AND f.departureTime >= :departureTime " +
            "AND f.availableSeats >= :seats " +
            "AND f.available = true")
    List<Flight> searchAvailableFlights(
            @Param("origin") String origin,
            @Param("destination") String destination,
            @Param("departureTime") String departureTime,
            @Param("seats") Integer seats
    );

    @Repository
    public interface AirportRepository extends JpaRepository<Airport, Long>{
        Optional<Airport> findByCode(String code);
        List<Airport> findByCountry (String country);
        List<Airport> findByCity (String city);

        boolean existsByCode (String code);

        @Query ("SELECT a FROM Airport a " +
                "WHERE a.code IN :codes")
        List<Airport> findByCodes(@Param("codes") List<String> code);
    }

    @Repository
    public interface ReservationRepository extends JpaRepository<Reservation, Long>{
        List<Reservation> findByUser(userEntity.User user);
        List<Reservation> findByFlight(Flight flight);
        List<Reservation> findByReservationStatus(String status);

        @Query ("SELECT r FROM Reservation r " +
        "WHERE r.reservationExpiry < :now " +
        "AND r.reservationExpiry = 'PENDING'")
        List<Reservation> findByExpiredReservations(@Param("now") LocalDateTime now);

        @Query ("SELECT r FROM Reservation r " +
        "JOIN FETCH r.FLight f " +
        "JOIN FETCH r.user u " +
        "WHERE u.id = :userId")
        List<Reservation> findByUserReservationsWithDetails(@Param("userId") Long userId);

        @Modifying
        @Query ("UPDATE Reservation r " +
        "SET r.Reservation = :status " +
        "WHERE r.id = :id")
        Void updateStatus(@Param("id") Long id, @Param("status") String status);

        @Repository
        public interface CustomFlightRepositoryImpl implements CustomFlightRepository{
            @PersistenceContext
            private EntityManager entityManager;

            @Override
            public List<Flight> findComplexFlightCriteria(FlightSearchCriteria criteria) {
                CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                CriteriaQuery<Flight> query = cb.createQuery(Flight.class);
                Root<Flight> flight = query.from(Flight.class);

                List<Predicate> predicates = new ArrayList<>();
                // Añadir criterios de búsqueda

                query.where(predicates.toArray(new Predicate[0]));
                return entityManager.createQuery(query).getResultList();
            }
        }
    }
}
