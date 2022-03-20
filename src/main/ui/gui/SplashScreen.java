
package ui.gui;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {

    public SplashScreen() {
        JWindow j = new JWindow();

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        Icon img = new ImageIcon(this.getClass().getResource("image/meeting-agenda.jpeg"));
        JLabel label = new JLabel(img);
        j.getContentPane().add(label);
        j.setBounds(((int) d.getWidth() - 1280) / 2, ((int) d.getHeight() - 904) / 2, 1280, 904);
        j.setVisible(true);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        j.setVisible(false);

    }
}
