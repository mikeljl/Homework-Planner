package ui.gui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.util.Iterator;

public class HomePage {
    private JFrame frame;



    public HomePage() {

        frame = new HomePageFrame("Homework Agenda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,500);
        frame.setVisible(true);
        HomePageFrame.centerInScreen(frame);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                Iterator<Event> iterator = EventLog.getInstance().iterator();

                while (iterator.hasNext()) {
                    System.out.println(iterator.next() + " ");
                }

                System.exit(0);
            }
        });

    }


    public static void main(String[] args) {
        new SplashScreen();
        new HomePage();


    }
}
