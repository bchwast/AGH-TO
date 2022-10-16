package pl.edu.agh.school.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.google.inject.name.Named;
import pl.edu.agh.logger.ConsoleMessageSerializer;
import pl.edu.agh.logger.FileMessageSerializer;
import pl.edu.agh.logger.IMessageSerializer;
import pl.edu.agh.school.persistence.IPersistenceManager;
import pl.edu.agh.school.persistence.SerializablePersistenceManager;

public class SchoolModule extends AbstractModule {

    @Provides
    public IPersistenceManager providePersistenceManager(SerializablePersistenceManager manager) {
        return manager;
    }

    @Provides
    @Named("Teachers")
    public String provideTeachers() {
        return "guice-teachers.dat";
    }

    @Provides
    @Named("Classes")
    public String provideClasses() {
        return "guice-classes.dat";
    }

    @ProvidesIntoSet
    public IMessageSerializer provideFileMessageSerializer(FileMessageSerializer serializer) {
        return serializer;
    }

    @ProvidesIntoSet
    public IMessageSerializer provideConsoleMessageSerializer(ConsoleMessageSerializer serializer) {
        return serializer;
    }

    @Provides
    @Named("LogFilename")
    public String provideLogFilename() {
        return "persistence.log";
    }
}
