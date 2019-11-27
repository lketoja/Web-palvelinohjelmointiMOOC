/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euroshopper;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lotta
 */

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart extends AbstractPersistable<Long>{
    
    private Map<Item, Long> items = new HashMap<>();;
    
    public void addToCart(Item item){
        if(items.containsKey(item)){
            Long count = items.get(item);
            items.put(item, count + 1);
        } else {
            items.put(item, 1L);
        }
    }
    
}
