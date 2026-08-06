package console.program;

import java.util.List;

/**
 * The Main class is the entry point of the sample console program.
 */
public class Main {

	public static void main(String args[]){
	    CourseLoader loader = new CourseLoader();
		List<Course> courses = loader.load("courses.csv");

		CourseCatalog catalog = new CourseCatalog(courses);
		Timetable timetable = new Timetable();

		ConsoleUI ui = new ConsoleUI(catalog, timetable);
		ui.run();
	}

}
