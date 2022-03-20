package ui.gui;

import javax.swing.*;
import java.awt.*;

public class MyListCellRenderer implements ListCellRenderer {

    JLabel label = new JLabel();

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        String text = (String) value;
        label.setText(text);

        label.setHorizontalAlignment(SwingConstants.CENTER);;
        label.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        label.setPreferredSize(new Dimension(100, 40));
        label.setOpaque(true);
        if (isSelected) {
            label.setBackground(new Color(150, 173, 255));
            label.setForeground((list.getSelectionForeground()));
        } else {
            label.setBackground(list.getBackground());
            label.setForeground(list.getForeground());
        }
        return label;
    }
}
