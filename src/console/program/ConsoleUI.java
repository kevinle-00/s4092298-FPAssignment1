package console.program;

import java.util.List;
import java.util.Scanner;

// Provides the console interface for the MyTimetable application
public class ConsoleUI {

    private final CourseCatalog catalog;
    private final Timetable timetable;
    private final Scanner scanner;

    public ConsoleUI(CourseCatalog catalog, Timetable timetable, Scanner scanner) {
        this.catalog = catalog;
        this.timetable = timetable;
        this.scanner = scanner;
    }

    public void run() {
        boolean exit = false;
        System.out.println("Welcome to MyTimetable!");

        while (!exit) {
            printMenu();
            int selection = readNumber();

            switch (selection) {
                case 1:
                    searchAndEnrol();
                    break;
                case 2:
                    showEnrolledCourses();
                    break;
                case 3:
                    System.out.println("Withdrawal is not available yet.");
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Please select a valid menu option.");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println("----------------------------------------");
        System.out.println("> Select from main menu");
        System.out.println("----------------------------------------");
        System.out.println("1) Search by keyword to enrol");
        System.out.println("2) Show my enrolled courses");
        System.out.println("3) Withdraw from a course");
        System.out.println("4) Exit");
        System.out.print("Please select: ");
    }

    private int readNumber() {
        return Integer.parseInt(scanner.nextLine().trim());
    }

    private void searchAndEnrol() {
        System.out.print("Please provide a keyword: ");
        String keyword = scanner.nextLine().trim();
        List<Course> matches = catalog.search(keyword);

        if (matches.isEmpty()) {
            System.out.println("No matching courses found.");
            return;
        }

        System.out.println("> Select from matching list");
        for (int index = 0; index < matches.size(); index++) {
            System.out.println((index + 1) + ") " + matches.get(index).getDisplayDetails());
        }
        System.out.println((matches.size() + 1) + ") Go to main menu");
        System.out.print("Please select: ");

        int selection = readNumber();
        if (selection >= 1 && selection <= matches.size()) {
            Course selectedCourse = matches.get(selection - 1);
            timetable.enrol(selectedCourse);
            System.out.println("You have enrolled in the course " + selectedCourse.getName() + "!");
        }
    }

    private void showEnrolledCourses() {
        List<Course> enrolledCourses = timetable.getEnrolledCourses();

        if (enrolledCourses.isEmpty()) {
            System.out.println("You don't have any courses enrolled.");
            return;
        }

        System.out.println("You have enrolled into the following course(s):");
        for (int index = 0; index < enrolledCourses.size(); index++) {
            System.out.println((index + 1) + ") " + enrolledCourses.get(index).getDisplayDetails());
        }
    }
}
