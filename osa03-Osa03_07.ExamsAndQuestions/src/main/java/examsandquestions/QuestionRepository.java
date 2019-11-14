
package examsandquestions;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Lotta
 */
public interface QuestionRepository extends JpaRepository<Question, Long>{
    
}
