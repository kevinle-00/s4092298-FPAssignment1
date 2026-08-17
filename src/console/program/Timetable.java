package console.program;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a student's enrolled courses and timetable
public class Timetable {

    private final List<Course> enrolledCourses = new ArrayList<>();

    public void enrol(Course course) {
        enrolledCourses.add(course);
    }

    public List<Course> getEnrolledCourses() {
        return Collections.unmodifiableList(enrolledCourses);
    }
}
