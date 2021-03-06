package ui;

// Homework agenda application

import model.Homework;
import model.HomeworkAgenda;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AgendaApp {

    private Scanner input;
    private static final String JSON_STORE = "./data/homeworkagenda.json";
    private HomeworkAgenda homeworkAgenda;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the agenda application
    public AgendaApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
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

            if (command.equals("q")) {
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
        } else if (command.equals("delete given subject")) {
            deleteHomeworkWithGivenSubject();
        } else if (command.equals("s")) {
            saveHomeworkAgenda();
        } else if (command.equals("l")) {
            loadHomeworkAgenda();
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
        System.out.println("\tdelete given subject -> Delete All Homework From Given Subject");
        System.out.println("\ts -> Save Homework Agenda To File");
        System.out.println("\tl -> Load Work Room From File");
        System.out.println("\tq -> Quit");
    }

    // REQUIRES: HomeworkAgenda must already have a homework with the subject entered in
    // MODIFIES: this
    // EFFECTS: conducts delete homework with subject
    private void deleteHomeworkWithGivenSubject() {

        System.out.print("Please enter the subject of homework that you want to delete : \n");
        String subjectToDelete = input.next();
        subjectToDelete = subjectToDelete.toLowerCase();

        homeworkAgenda.deleteHomeworkWithSubject(subjectToDelete);

        System.out.print("All " + subjectToDelete + "'s" + " homework has been successfully deleted");
    }

    // MODIFIES: this
    // EFFECTS: conducts add homework
    private void doAddHomework() {

        System.out.print("Please enter the subject of the homework: \t");
        String subject = input.next();
        subject = subject.toLowerCase();

        System.out.print("Please enter the description of the homework: \t");
        String description = input.next();
        description = description.toLowerCase();

        Homework newHomework = new Homework(subject, description);

        homeworkAgenda.addHomework(newHomework);

        System.out.println("Subject: " + subject + " Description: " + description + " has been successfully added");
    }

    // MODIFIES: this
    // EFFECTS: conducts delete homework
    private void doDeleteHomework() {

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

        System.out.println("Subject: " + delete.getSubject() + " Description: " + delete.getDescription()
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

    //REQUIRES: HomeworkAgenda must already have a homework with subject entered in
    // EFFECTS: show all homework in agenda that has a given subject
    private void showHomeworkWithGivenSubject() {
        System.out.println("What subject's homework do you want to see? \t");
        String userInputOfSubject = input.next();
        userInputOfSubject = userInputOfSubject.toLowerCase();

        if (homeworkAgenda.length() == 0) {
            System.out.println("Agenda is EMPTY!!!");
        } else {
            for (Homework next : homeworkAgenda.getAgenda()) {
                if (next.getSubject().equals(userInputOfSubject)) {
                    System.out.println("Subject " + userInputOfSubject + " " + "Description " + next.getDescription());
                }
            }
        }
    }

    // EFFECTS: saves the homeworkAgenda to file
    private void saveHomeworkAgenda() {
        try {
            jsonWriter.open();
            jsonWriter.write(homeworkAgenda);
            jsonWriter.close();
            System.out.println("Saved " + "your homework agenda" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads homeworkAgenda from file
    private void loadHomeworkAgenda() {
        try {
            homeworkAgenda = jsonReader.read();
            System.out.println("Loaded " + "your homework agenda" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}



