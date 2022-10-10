package pl.edu.agh.iisg.to.dao;

import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Grade;
import pl.edu.agh.iisg.to.model.Student;

import javax.persistence.PersistenceException;

public class GradeDao extends GenericDao<Grade> {

    public boolean gradeStudent(final Student student, final Course course, final float grade) {
        //TODO implement
        try {
            Grade gradeO = new Grade(student, course, grade);
            if (student.gradeSet().contains(gradeO) || course.gradeSet().contains(gradeO)) return false;
            save(gradeO);
            student.gradeSet().add(gradeO);
            course.gradeSet().add(gradeO);
            update(gradeO);
            super.update(student);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return true;
    }


}
