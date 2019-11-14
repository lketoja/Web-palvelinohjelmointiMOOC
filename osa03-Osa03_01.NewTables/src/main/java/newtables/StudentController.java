package newtables;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {
    
    @Autowired
    private StudentRepository studentRepo;
    
    @GetMapping("/students")
    public String students(Model model){
        model.addAttribute("students", studentRepo.findAll());
        System.out.println("testi");
        System.out.println(studentRepo.findAll());
        return "students";
    }
    
    @PostMapping("/students")
    public String addStudent(@RequestParam String first_name, @RequestParam String second_name){
        studentRepo.save(new Student(first_name, second_name));
        return "redirect:/students";
    }
    
}