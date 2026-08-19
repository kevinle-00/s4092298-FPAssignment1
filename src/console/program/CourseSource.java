package console.program;

import java.util.List;

// Separates course loading from its CSV implementation
public interface CourseSource {
    List<Course> load(String fileName);
}
