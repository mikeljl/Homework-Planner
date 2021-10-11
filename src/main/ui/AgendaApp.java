package ui;

// Homework agenda application

import model.Homework;
import model.HomeworkAgenda;

import java.util.Scanner;

public class AgendaApp {

    private Scanner input;
    private Homework homework;
    private HomeworkAgenda homeworkAgenda;

    // EFFECTS: runs the agenda application
    public AgendaApp() {
        runAgenda();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runAgenda() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nWork hard and good bye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("add homework")) {
            doAddHomework();
        } else if (command.equals("delete homework")) {
            doDeleteHomework();
        } else if (command.equals("show all homework")) {
            doShowAllHomework();
        } else if (command.equals("total number of homework")) {
            doShowTotalNumberOfHomework();
        } else {
            System.out.println("Selection not valid!");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        homeworkAgenda = new HomeworkAgenda();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tadd homework");
        System.out.println("\tdelete homework");
        System.out.println("\tshow all homework");
        System.out.println("\ttotal number of homework");
        System.out.println("\tquit");
    }

    // MODIFIES: this
    // EFFECTS: conducts a deposit transaction
    private void doAddHomework() {
        Scanner input1 = null;
        Scanner input2 = null;
        input1 = new Scanner(System.in);
        input1.useDelimiter("\n");
        input2 = new Scanner(System.in);
        input2.useDelimiter("\n");
        String subject = null;
        String description = null;

        System.out.print("Please enter the subject of the homework: \t");
        subject = input1.next();

        System.out.print("Please enter the description of the homework: \t");
        description = input2.next();

        Homework newHomework = new Homework(subject, description);

        homeworkAgenda.addHomework(newHomework);
    }

    // MODIFIES: this
    // EFFECTS: conducts a withdraw transaction
    private void doDeleteHomework() {
        Scanner input1 = null;
        Scanner input2 = null;
        input1 = new Scanner(System.in);
        input1.useDelimiter("\n");
        input2 = new Scanner(System.in);
        input2.useDelimiter("\n");
        String userInputOfSubject = null;
        String userInputOfDescription = null;

        System.out.print("Enter the subject of homework you want to delete: \n");
        userInputOfSubject = input1.next();

        for (Homework next : homeworkAgenda.getAgenda()) {
            if (next.getSubject() == userInputOfSubject) {
                System.out.println(userInputOfSubject + " " + next.getDescription() + " " + "or\n");
            }
        }

        System.out.print("Enter the description of homework you want to delete: \n");
        userInputOfDescription = input2.next();

        for (Homework next : homeworkAgenda.getAgenda()) {
            if ((next.getSubject() == userInputOfSubject) && (next.getDescription() == userInputOfDescription)) {
                Homework needToBeDeleted = new Homework(userInputOfSubject, userInputOfDescription);
                homeworkAgenda.deleteHomework(needToBeDeleted);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts a transfer transaction
    private void doShowAllHomework() {
        for (Homework next : homeworkAgenda.getAgenda()) {
            System.out.println(next.getSubject() + " " + next.getDescription() + "\t");
        }
    }

    private void doShowTotalNumberOfHomework() {
        System.out.println(homeworkAgenda.length());

    }

}



