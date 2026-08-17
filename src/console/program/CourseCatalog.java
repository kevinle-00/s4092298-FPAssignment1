package console.program;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// Stores and searches the courses available for enrolment
public class CourseCatalog {

    private final List<Course> courses;

    public CourseCatalog(List<Course> courses) {
        this.courses = new ArrayList<>(courses);
    }

    // Finds courses whose names contain the keyword, ignoring letter case
    public List<Course> search(String keyword) {
        String searchTerm = keyword.toLowerCase(Locale.ROOT);
        List<Course> matches = new ArrayList<>();

        for (Course course : courses) {
            if (course.getName().toLowerCase(Locale.ROOT).contains(searchTerm)) {
                matches.add(course);
            }
        }

        return matches;
    }
}
