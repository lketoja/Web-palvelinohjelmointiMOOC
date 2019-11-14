package namesandaddresses;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    
    @EntityGraph(attributePaths = {"address"})
    List<Person> findAll();

}
