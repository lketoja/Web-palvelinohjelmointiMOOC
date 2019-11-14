package newtables;


import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {
    
    @Autowired
    private CourseRepository courseRepo;
    private StudentRepository studentRepo;
    
    @GetMapping("/courses")
    public String courses(Model model){
        model.addAttribute("courses", courseRepo.findAll());
        System.out.println("tassa studentRepo:");
        System.out.println(studentRepo.findAll());
        return "courses";
    }
    
    @PostMapping("/courses")
    public String addCourse(@RequestParam String name){
        courseRepo.save(new Course(name));
        return "redirect:/courses";
    }
    
    @GetMapping("/courses/{courseId}")
    public String oneCourse(Model model, @PathVariable Long courseId){
        model.addAttribute("course", courseRepo.getOne(courseId));
        model.addAttribute("students", studentRepo.findAll());
        
        return "one_course";
    }
    
    @PostMapping("/courses/{courseId}/{studentId}")
    public String addStudentToCourse(Model model, @PathVariable Long courseId, @PathVariable Long studentId){
        Course course = courseRepo.getOne(courseId);
        Student student = studentRepo.getOne(studentId);
        return "redirect:/courses";
    }
    
}