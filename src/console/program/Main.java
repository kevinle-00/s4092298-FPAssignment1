package console.program;

import java.util.List;
import java.util.Scanner;

// Starts the MyTimetable application
public class Main {

    public static void main(String[] args) {
        List<Course> courses = new CourseLoader().load("courses.csv");

        CourseCatalog catalog = new CourseCatalog(courses);
        Timetable timetable = new Timetable();

        ConsoleUI ui = new ConsoleUI(catalog, timetable, new Scanner(System.in));
        ui.run();
    }

}
