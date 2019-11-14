
package newtables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course extends AbstractPersistable<Long>{
    
    private String name;
    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
    
    public Course(String name){
        this.name = name;
        students = new ArrayList<>();
    }
    
}
