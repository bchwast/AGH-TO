package pl.edu.agh.school;

import java.util.Collections;
import java.util.List;

import com.google.inject.Inject;
import pl.edu.agh.logger.Logger;
import pl.edu.agh.school.persistence.IPersistenceManager;

public class SchoolDAO {

    private Logger logger;

    private final List<Teacher> teachers;

    private final List<SchoolClass> classes;

    private final IPersistenceManager manager;

    @Inject
    public SchoolDAO(IPersistenceManager manager) {
        this.manager = manager;
        teachers = manager.loadTeachers();
        classes = manager.loadClasses();
    }

    public void addTeacher(Teacher teacher) {
        if (!teachers.contains(teacher)) {
            teachers.add(teacher);
            manager.saveTeachers(teachers);
            logger.log("Added " + teacher.toString());
        }
    }

    public void addClass(SchoolClass newClass) {
        if (!classes.contains(newClass)) {
            classes.add(newClass);
            manager.saveClasses(classes);
            logger.log("Added " + newClass.toString());
        }
    }

    public List<SchoolClass> getClasses() {
        return Collections.unmodifiableList(classes);
    }

    public List<Teacher> getTeachers() {
        return Collections.unmodifiableList(teachers);
    }

    @Inject
    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
