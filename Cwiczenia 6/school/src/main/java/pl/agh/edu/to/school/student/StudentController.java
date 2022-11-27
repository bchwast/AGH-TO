package pl.agh.edu.to.school.student;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(@RequestParam Optional<String> id) {
        if (id.isPresent()) {
            return studentService.getStudentById(Integer.parseInt(id.get()));
        }
        return studentService.getStudents();
    }

    @PostMapping("/{studentId}/{courseId}")
    public void giveGrade(@PathVariable String studentId, @PathVariable String courseId, @RequestParam String grade) {
        studentService.giveGrade(Integer.parseInt(studentId), Integer.parseInt(courseId), Double.parseDouble(grade));
    }
}
