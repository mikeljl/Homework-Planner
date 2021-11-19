// got part of code from https://stackoverflow.com/questions/16134549/how-to-make-a-splash-screen-for-gui

package ui;

import javax.swing.*;
import java.awt.*;

public class Splash extends JWindow {

    public Splash() {
        JWindow j = new JWindow();

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        Icon img = new ImageIcon(this.getClass().getResource("meeting-agenda.jpeg"));
        JLabel label = new JLabel(img);
        j.getContentPane().add(label);
        j.setBounds(((int) d.getWidth() - 1280) / 2, ((int) d.getHeight() - 904) / 2, 1280, 904);
        j.setVisible(true);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        j.setVisible(false);

    }

    public static void main(String[] args) {
        Splash s = new Splash();
    }
}
