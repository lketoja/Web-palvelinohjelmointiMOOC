package todoapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoApplicationController {
    
    @Autowired
    private TodoItemRepository itemRepo;
    
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("items", itemRepo.findAll());
        return "index";
    }
    
    @GetMapping("/{id}")
    public String oneItem(Model model, @PathVariable Long id){
        model.addAttribute("item", itemRepo.getOne(id));
        return "todo";
    }
    
    @PostMapping("/")
    public String newTodo(@RequestParam String name){
        itemRepo.save(new TodoItem(name,0));
        return "redirect:/";
    }
    

}
