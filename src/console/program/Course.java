package console.program;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// Represents a course available for enrolment
public class Course {

    private static final DateTimeFormatter DISPLAY_TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    private final String name;
    private final Integer capacity;
    private final String year;
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

    public Integer getCapacity() {
        return capacity;
    }

    public String getYear() {
        return year;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public String getLectureDay() {
        return lectureDay;
    }

    public LocalTime getLectureTime() {
        return lectureTime;
    }

    public double getLectureDuration() {
        return lectureDuration;
    }

    public int getCurrentEnrolments() {
        return currentEnrolments;
    }

    // Formats the course details for display in the console menus
    public String getDisplayDetails() {
        String shortDay = lectureDay.substring(0, Math.min(3, lectureDay.length()));
        String start = lectureTime.format(DISPLAY_TIME_FORMAT);
        String end = getEndTime().format(DISPLAY_TIME_FORMAT);
        return name + " " + deliveryMode + " " + shortDay + " " + start + "-" + end;
    }

    private LocalTime getEndTime() {
        return lectureTime.plusMinutes(Math.round(lectureDuration * 60));
    }
}
