package pl.edu.agh.logger;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileMessageSerializer implements IMessageSerializer {
    private Writer output;

    @Inject
    public FileMessageSerializer(@Named("LogFilename") String filename) throws IOException {
        output = new BufferedWriter(new FileWriter(filename, true));
    }

    @Override
    public void serializeMessage(String message) {
        try {
            output.write(message + "\n");
            output.flush();
        } catch (IOException e) {
            System.err.println("FileMessageSerializer error: " + e.getMessage());
        }
    }
}
