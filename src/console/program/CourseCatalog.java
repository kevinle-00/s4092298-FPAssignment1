package console.program;
import console.program.Course;
import java.util.List;

/**
 * Stores and searches the courses available for enrolment.
 */
public class CourseCatalog {

    private List<Course> courses;

    public CourseCatalog(List<Course> courses) {
        this.courses = courses;
    }
}
