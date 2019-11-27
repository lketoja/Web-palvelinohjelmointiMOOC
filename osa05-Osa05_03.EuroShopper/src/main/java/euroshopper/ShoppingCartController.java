/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euroshopper;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Lotta
 */
@Controller
public class ShoppingCartController {
    
    @Autowired
    private HttpSession session; 
    
    @Autowired
    private ShoppingCart shoppingCart; 
    
    @Autowired
    private ItemRepository itemRepository;
    
    private int sum = 0;
    
    @GetMapping("/cart")
    public String showCart(Model model){
        session.setAttribute("cart", shoppingCart);
        model.addAttribute("items", shoppingCart.getItems());
        model.addAttribute("sum", sum);
    
        return "cart";
    }
    
    @PostMapping("/cart/items/{id}")
    public String addItemToShoppingCart(@PathVariable Long id){
        Item item = itemRepository.getOne(id);
       
        if(session.getAttribute("cart") == null){
            session.setAttribute("cart", shoppingCart);
        }
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        cart.addToCart(item);
        sum += item.getPrice();
     
        
        session.setAttribute("cart", cart);
        
        
        return "redirect:/cart";
    }
    
}
