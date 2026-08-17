package console.program;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Loads courses from the CSV data source
public class CourseLoader {

    private static final DateTimeFormatter INPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("H:mm");

    public List<Course> load(String fileName) {
        List<Course> courses = new ArrayList<>();
        Path path = Paths.get(fileName);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            reader.readLine(); // Skip the header row

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    courses.add(parseCourse(line));
                }
            }
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load courses from " + fileName, exception);
        }

        return courses;
    }

    private Course parseCourse(String line) {
        String[] values = line.split(",", -1);
        Integer capacity = values[1].trim().equalsIgnoreCase("n/a")
                ? null
                : Integer.valueOf(values[1].trim());

        return new Course(
                values[0].trim(),
                capacity,
                values[2].trim(),
                values[3].trim(),
                values[4].trim(),
                LocalTime.parse(values[5].trim(), INPUT_TIME_FORMAT),
                Double.parseDouble(values[6].trim()),
                Integer.parseInt(values[7].trim()));
    }
}
