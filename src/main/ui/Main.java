

package ui;

import java.io.FileNotFoundException;


public class Main {
    public static void main(String[] args) {
        try {
            new AgendaApp();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

