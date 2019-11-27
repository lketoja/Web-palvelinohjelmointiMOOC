package reservations;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    @Query("SELECT r from Reservation r WHERE "
            + "r.reservationFrom <= :to AND r.reservationTo >= :from")
    List<Reservation> findOverlappingReservations(@Param("from") LocalDate from, @Param("to") LocalDate to);
}
 


