package pl.agh.edu.to.school.course;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.agh.edu.to.school.student.Student;

import java.util.List;

@RestController
@RequestMapping(path = "courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses() {
        return courseService.getCourses();
    }

    @GetMapping("/{id}")
    public List<Student> getStudentsByCourseId(@PathVariable String id) {
        return courseService.getCourseById(Integer.parseInt(id)).get(0).getStudents();
    }
}
