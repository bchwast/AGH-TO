package pl.agh.edu.to.school.student;

import org.springframework.stereotype.Service;
import pl.agh.edu.to.school.course.Course;
import pl.agh.edu.to.school.course.CourseRepository;
import pl.agh.edu.to.school.grade.Grade;
import pl.agh.edu.to.school.grade.GradeRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final GradeRepository gradeRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.gradeRepository = gradeRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public void giveGrade(int studentId, int courseId, double grade) {
        Student student = studentRepository.findById(studentId).get(0);
        Course course = courseRepository.findById(courseId).get(0);
        Grade newGrade = new Grade(grade, course);
        student.giveGrade(newGrade);

        gradeRepository.save(newGrade);
    }
}
