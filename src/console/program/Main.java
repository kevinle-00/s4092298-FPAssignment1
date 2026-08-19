package console.program;

import java.util.List;
import java.util.Scanner;

// Starts the MyTimetable application
public class Main {

    public static void main(String[] args) {
        CourseSource loader = new CourseLoader();
        List<Course> courses = loader.load("courses.csv");

        CourseCatalog catalog = new CourseCatalog(courses);
        Timetable timetable = new Timetable();

        ConsoleUI ui = new ConsoleUI(catalog, timetable, new Scanner(System.in));
        ui.run();
    }

}
