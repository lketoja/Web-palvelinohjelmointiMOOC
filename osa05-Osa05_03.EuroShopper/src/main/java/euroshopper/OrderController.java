package euroshopper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ShoppingCart shoppingCart;

    @RequestMapping("/orders")
    public String list(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }

    @PostMapping("/orders")
    public String order(@RequestParam String name, @RequestParam String address) {
        
        List<OrderItem> orderItems = new ArrayList<>();
        Map<Item, Long> cartItems = shoppingCart.getItems();
        for(Item item : cartItems.keySet()){
            OrderItem orderItem = new OrderItem(item, cartItems.get(item));
            orderItems.add(orderItem);
        }
                
        Order order = new Order();
        order.setName(name);
        order.setAddress(address);
        order.setOrderItems(orderItems);

        orderRepository.save(order);
        
        shoppingCart.setItems(new HashMap<>());

        return "redirect:/orders";
    }
}
