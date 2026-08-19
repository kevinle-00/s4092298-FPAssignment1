package console.program;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// Represents a course available for enrolment
public class Course {

    private static final DateTimeFormatter DISPLAY_TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    private final String name;
    private final Integer capacity;
    private final String year; // year is never used by any assignment feature, but is kept to fully represent course data
    private final String deliveryMode;
    private final String lectureDay;
    private final LocalTime lectureTime;
    private final double lectureDuration;
    private final int currentEnrolments;

    public Course(String name, Integer capacity, String year, String deliveryMode,
            String lectureDay, LocalTime lectureTime, double lectureDuration,
            int currentEnrolments) {
        this.name = name;
        this.capacity = capacity;
        this.year = year;
        this.deliveryMode = deliveryMode;
        this.lectureDay = lectureDay;
        this.lectureTime = lectureTime;
        this.lectureDuration = lectureDuration;
        this.currentEnrolments = currentEnrolments;
    }

    public String getName() {
        return name;
    }

    // Formats the course details for display in the console menus
    public String getDisplayDetails() {
        String shortDay = lectureDay.substring(0, Math.min(3, lectureDay.length()));
        String start = lectureTime.format(DISPLAY_TIME_FORMAT);
        String end = getEndTime().format(DISPLAY_TIME_FORMAT);
        return name + " " + deliveryMode + " " + shortDay + " " + start + "-" + end;
    }

    public boolean isFull() {
        // Online classes do not have a max capacity, this is represented by a null value
        return capacity != null && currentEnrolments >= capacity;
    }

    public boolean overlapsWith(Course other) {
        if (!lectureDay.equalsIgnoreCase(other.lectureDay)) {
            return false;
        }

        LocalTime thisEndTime = getEndTime();
        LocalTime otherEndTime = other.getEndTime();

        return lectureTime.isBefore(otherEndTime) && other.lectureTime.isBefore(thisEndTime);

    }

    private LocalTime getEndTime() {
        return lectureTime.plusMinutes(Math.round(lectureDuration * 60));
    }
}
