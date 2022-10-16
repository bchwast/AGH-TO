package pl.edu.agh.school;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import pl.edu.agh.logger.Logger;
import pl.edu.agh.school.guice.SchoolModule;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SchoolModuleTest {

    private Injector injector;

    @BeforeEach
    public void setUp() {
        this.injector = Guice.createInjector(new SchoolModule());
    }

    @Test
    public void testLoggerSingleton() {
        Logger logger = injector.getInstance(Logger.class);
        assertEquals(logger, injector.getInstance(Logger.class));
    }

}
