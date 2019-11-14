
package newtables;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends AbstractPersistable<Long>{
    
    
    private String first_name;
    private String second_name;
    @ManyToMany
    private List<Course> courses;
    
    public Student(String fn, String sn){
        first_name = fn;
        second_name = sn;
        courses = new ArrayList<>();
    }
    
}
