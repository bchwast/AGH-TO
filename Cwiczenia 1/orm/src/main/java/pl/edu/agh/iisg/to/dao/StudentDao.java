package pl.edu.agh.iisg.to.dao;

import java.util.*;

import org.hibernate.Session;
import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Grade;
import pl.edu.agh.iisg.to.model.Student;

import javax.persistence.PersistenceException;

public class StudentDao extends GenericDao<Student> {

    public Optional<Student> create(final String firstName, final String lastName, final int indexNumber) {
    	//TODO - implement
        try {
            save(new Student(firstName, lastName, indexNumber));
            return findByIndexNumber(indexNumber);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Student> findByIndexNumber(final int indexNumber) {
    	//TODO - implement
        try {
            Student student = currentSession().createQuery("SELECT s FROM Student s " +
                    "WHERE s.indexNumber = :indexNumber", Student.class)
                    .setParameter("indexNumber", indexNumber).getSingleResult();
            return Optional.of(student);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Map<Course, Float> createReport(final Student student) {
        //TODO additional task
        try {
            Map<Course, Float> report = new HashMap<>();
            student.courseSet().forEach(course -> {
                List<Float> grades = course.gradeSet().stream().filter(grade -> grade.student().equals(student))
                        .map(Grade::grade).toList();
                report.put(course, (float) grades.stream().mapToDouble(grade -> grade).average().orElse(0.0));
            });
            System.out.println(report);
            return report;
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        return Collections.emptyMap();
    }

}
