package pl.agh.edu.to.school.grade;

import pl.agh.edu.to.school.course.Course;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Grade {

    @Id
    @GeneratedValue
    private int id;
    private double gradeValue;
    @ManyToOne
    private Course course;

    public Grade() {}

    public Grade(double gradeValue, Course course) {
        this.gradeValue = gradeValue;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public double getGradeValue() {
        return gradeValue;
    }

    public Course getCourse() {
        return course;
    }
}
