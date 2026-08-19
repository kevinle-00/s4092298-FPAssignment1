package console.program;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a student's enrolled courses and timetable
public class Timetable {

    private final List<Course> enrolledCourses = new ArrayList<>();

    // Represents the possible outcomes of an enrolment attempt
    public enum EnrolmentResult {
        ENROLLED,
        DUPLICATE,
        FULL,
        CONFLICT
    }

    public EnrolmentResult enrol(Course candidate) {
        if (isAlreadyEnrolled(candidate)) {
            return EnrolmentResult.DUPLICATE;
        }

        if (candidate.isFull()) {
            return EnrolmentResult.FULL;
        }

        if (findLectureConflict(candidate) != null) {
            return EnrolmentResult.CONFLICT;
        }

        enrolledCourses.add(candidate);
        return EnrolmentResult.ENROLLED;
    }

    public boolean withdraw(Course course) {
        for (int index = 0; index < enrolledCourses.size(); index++) {
            Course enrolledCourse = enrolledCourses.get(index);
            if (enrolledCourse.getName().equals(course.getName())) {
                enrolledCourses.remove(index);
                return true;
            }
        }

        return false;
    }

    private boolean isAlreadyEnrolled(Course candidate) {
        for (Course enrolledCourse : enrolledCourses) {
            if (enrolledCourse.getName().equals(candidate.getName())) {
                return true;
            }
        }

        return false;
    }

    public Course findLectureConflict(Course candidate) {
        for (Course enrolledCourse : enrolledCourses) {
            if (enrolledCourse.overlapsWith(candidate)) {
                return enrolledCourse;
            }
        }

        return null;
    }

    public List<Course> getEnrolledCourses() {
        return Collections.unmodifiableList(enrolledCourses);
    }
}
