package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTime {

    public CurrentTime() {
        JFrame frame = new JFrame("Current Time");
        frame.setVisible(true);
        JLabel label = new JLabel();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String timeStr = sdf.format(new Date());
        label.setText(timeStr);
        frame.setContentPane(label);
        frame.setSize(300,50);
        label.setOpaque(true);
        label.setBackground(new Color(150, 173, 255));
        label.setForeground(new Color(255, 255, 255));
        HomePageFrame.centerInScreen(frame);
    }
}
