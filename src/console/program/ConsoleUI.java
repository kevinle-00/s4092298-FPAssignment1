package console.program;

import java.util.List;
import java.util.Scanner;

import console.program.Timetable.EnrolmentResult;

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
                    withdrawFromCourse();
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
        System.out.println();
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

        System.out.println();
        System.out.println("> Select from matching list");
        printCourseList(matches);
        System.out.println((matches.size() + 1) + ") Go to main menu");
        System.out.print("Please select: ");

        int selection = readNumber();
        if (selection >= 1 && selection <= matches.size()) {
            Course selectedCourse = matches.get(selection - 1);
            EnrolmentResult result = timetable.enrol(selectedCourse);

            switch (result) {
                case ENROLLED:
                    System.out.println("You have enrolled in the course " + selectedCourse.getName() + "!");
                    break;
                case DUPLICATE:
                    System.out.println("You are already enrolled in this course.");
                    break;
                case FULL:
                    System.out.println("Sorry. This course has reached its maximum capacity.");
                    break;
                case CONFLICT:
                    Course conflictingCourse = timetable.findLectureConflict(selectedCourse);
                    System.out.println("You cannot enrol in " + selectedCourse.getName()
                            + " because it conflicts with " + conflictingCourse.getName() + ".");
                    break;
            }
        }
    }

    private void withdrawFromCourse() {
        List<Course> enrolledCourses = timetable.getEnrolledCourses();

        if (enrolledCourses.isEmpty()) {
            System.out.println("You don't have any courses enrolled.");
            return;
        }

        System.out.println();
        System.out.println("Please choose a course to withdraw from:");
        printCourseList(enrolledCourses);
        System.out.println((enrolledCourses.size() + 1) + ") Go to main menu");
        System.out.print("Please select: ");

        int selection = readNumber();
        if (selection == enrolledCourses.size() + 1) {
            return;
        }

        Course selectedCourse = enrolledCourses.get(selection - 1);

        if (timetable.withdraw(selectedCourse)) {
            System.out.println("You have withdrawn from " + selectedCourse.getName() + "!");
        } else {
            System.out.println("You are not enrolled in this course.");
        }
    }

    private void showEnrolledCourses() {
        List<Course> enrolledCourses = timetable.getEnrolledCourses();

        if (enrolledCourses.isEmpty()) {
            System.out.println("You don't have any courses enrolled.");
            return;
        }

        System.out.println();
        System.out.println("You have enrolled into the following course(s):");
        printCourseList(enrolledCourses);
    }

    private void printCourseList(List<Course> courses) {
        for (int index = 0; index < courses.size(); index++) {
            System.out.println((index + 1) + ") " + courses.get(index).getDisplayDetails());
        }
    }
}
