package ui;

import javax.swing.*;
import java.awt.*;

public class Splash extends JWindow {

    public Splash() {
        JWindow j = new JWindow();

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        Icon img = new ImageIcon(this.getClass().getResource("download.jpg"));
        JLabel label = new JLabel(img);
        label.setSize(200, 300);
        j.getContentPane().add(label);
        j.setBounds(((int) d.getWidth() - 722) / 2, ((int) d.getHeight() - 401) / 2, 722, 401);
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