/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gifbin;

import javax.persistence.Entity;
import javax.persistence.Lob;
import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Lotta
 */
@Data
@Entity
public class FileObject extends AbstractPersistable<Long>{
    
    @Lob
    private byte[] content;
    
}
