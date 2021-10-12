package ui;

// Homework agenda application

import model.Homework;
import model.HomeworkAgenda;

import java.util.Scanner;

public class AgendaApp {

    private Scanner input;
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
        if (command.equals("add")) {
            doAddHomework();
        } else if (command.equals("delete")) {
            doDeleteHomework();
        } else if (command.equals("show all")) {
            doShowAllHomework();
        } else if (command.equals("total number")) {
            doShowTotalNumberOfHomework();
        } else if (command.equals("given subject")) {
            showHomeworkWithGivenSubject();
        } else {
            System.out.println("Selection Not Valid!");
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
        System.out.println("\tadd -> Add Homework");
        System.out.println("\tdelete -> Delete Homework");
        System.out.println("\tshow all -> Show All Homework");
        System.out.println("\ttotal number -> Total Number Of Homework");
        System.out.println("\tgiven subject -> See Homework With Given Subject");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: conducts add homework
    private void doAddHomework() {
        String subject = null;
        String description = null;

        System.out.print("Please enter the subject of the homework: \t");
        subject = input.next();
        subject = subject.toLowerCase();

        System.out.print("Please enter the description of the homework: \t");
        description = input.next();
        description = description.toLowerCase();

        Homework newHomework = new Homework(subject, description);

        homeworkAgenda.addHomework(newHomework);

        System.out.println("Subject: " + subject + " Description: " + description + " has been successfully added");
    }

    // MODIFIES: this
    // EFFECTS: conducts delete homework
    private void doDeleteHomework() {
        //String userInputOfSubject = null;
        //String userInputOfDescription = null;

        //System.out.print("Enter the subject of homework you want to delete: \n");
        //userInputOfSubject = input.next();
        //userInputOfSubject = userInputOfSubject.toLowerCase();


       // for (Homework next : homeworkAgenda.getAgenda()) {
       //     if (next.getSubject().equals(userInputOfSubject)) {
      //          System.out.println("Subject: " + next.getSubject() + "  Description: " + next.getDescription()
       //                  + " or\n");
       //       }
      //    }

        //for (Homework next : homeworkAgenda.getAgenda()) {
        //     if ((next.getDescription().equals(userInputOfDescription))) {
        //        homeworkAgenda.deleteHomework(next);
        //   }
        // }

        // System.out.println("Subject: " + userInputOfSubject + "  Description: " + userInputOfDescription
        //         + " has been successfully deleted");

        for (int i = 0; i < homeworkAgenda.length(); i++) {
            System.out.println((i + 1) + ". " + "Subject: " + homeworkAgenda.getAgenda().get(i).getSubject()
                    + "  Description: " + homeworkAgenda.getAgenda().get(i).getDescription()
                        + " \n");
        }

        System.out.println("Please enter the number of homework that you want to delete: \t");

        int i = Integer.parseInt(input.next());

        if (i <= 0 || i > homeworkAgenda.length()) {
            System.out.println("INVALID NUMBER!!!");
            System.out.println("Please enter again: ");
            int i1 = Integer.parseInt(input.next());
            i = i1;
        }

        Homework delete = homeworkAgenda.getAgenda().get(i - 1);
        homeworkAgenda.deleteHomework(delete);

        System.out.println("Subject: " + delete.getSubject() + "Description: " + delete.getDescription()
                + " has been deleted!");
    }

    // EFFECTS: show all homework in agenda with subject and description
    private void doShowAllHomework() {
        if (homeworkAgenda.length() == 0) {
            System.out.println("Agenda is EMPTY!!!");
        } else {
            for (Homework next : homeworkAgenda.getAgenda()) {
                System.out.println("Subject: " + next.getSubject() + "  Description: " + next.getDescription() + "\t");
            }
        }
    }

    // EFFECTS: show total number of homework in agenda
    private void doShowTotalNumberOfHomework() {
        System.out.println(homeworkAgenda.length());

    }

    // EFFECTS: show all homework in agends that have a given subject
    private void showHomeworkWithGivenSubject() {
        System.out.println("What subject's homework do you want to see? \t");
        String userInputOfSubject = input.next();

        if (homeworkAgenda.length() == 0) {
            System.out.println("Agenda is EMPTY!!!");
        } else {
            for (Homework next : homeworkAgenda.getAgenda()) {
                if (next.getSubject().equals(userInputOfSubject)) {
                    System.out.println(userInputOfSubject + " " + next.getDescription());
                }
            }
        }
    }

}



