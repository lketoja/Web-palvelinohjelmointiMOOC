package airports;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
    
    Aircraft findByName(String name);
}
