package pl.agh.edu.to.school.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.agh.edu.to.school.course.Course;
import pl.agh.edu.to.school.course.CourseRepository;
import pl.agh.edu.to.school.grade.Grade;
import pl.agh.edu.to.school.grade.GradeRepository;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfigurator {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, CourseRepository courseRepository,
                                        GradeRepository gradeRepository) {
        return args -> {
            if (studentRepository.count() == 0) {
                Student kowalski = new Student("Jan", "Kowalski", LocalDate.now(), "123456");
                Student maziarz = new Student("Jakub", "Maziarz", LocalDate.now(), "234567");
                Student stachowicz = new Student("Konrad", "Stachowicz", LocalDate.now(), "718330");
                studentRepository.saveAll(List.of(kowalski, maziarz, stachowicz));

                Course matematyka = new Course("Matematyka");
                Course bazy = new Course("Bazy danych");

                matematyka.assignStudent(kowalski);
                matematyka.assignStudent(maziarz);
                bazy.assignStudent(maziarz);
                bazy.assignStudent(stachowicz);

                List<Grade> kowalskiGrades = List.of(new Grade(4.5, matematyka), new Grade(3.5, matematyka),
                        new Grade(2.0, bazy), new Grade(3.0, bazy));
                kowalskiGrades.forEach(kowalski::giveGrade);
                List<Grade> maziarzGrades = List.of(new Grade(3.5, matematyka), new Grade(3.0, matematyka),
                        new Grade(2.0, bazy), new Grade(3.5, bazy));
                maziarzGrades.forEach(maziarz::giveGrade);
                List<Grade> stachowiczGrades = List.of(new Grade(2.0, matematyka), new Grade(2.0, matematyka),
                        new Grade(2.0, bazy), new Grade(2.0, bazy));
                stachowiczGrades.forEach(stachowicz::giveGrade);

                courseRepository.saveAll(List.of(matematyka, bazy));
                gradeRepository.saveAll(kowalskiGrades);
                gradeRepository.saveAll(maziarzGrades);
                gradeRepository.saveAll(stachowiczGrades);
            }
        };
    }
}
