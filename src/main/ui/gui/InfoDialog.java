package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoDialog extends JDialog {

    private JPanel root;
    private JPanel buttonPanel;
    private JPanel messagePanel;
    private JLabel message;
    private JButton okButton;

    public InfoDialog(Window owner) {
        super(owner);

        root = new JPanel(new BorderLayout());
        this.setContentPane(root);
        buttonPanel = new JPanel();
        messagePanel = new JPanel();

        message = new JLabel();
        messagePanel.add(message);

        okButton = new JButton("Ok");
        okButton.addActionListener(new OkListener());
        buttonPanel.add(okButton);

        root.add(messagePanel, BorderLayout.CENTER);
        root.add(buttonPanel, BorderLayout.PAGE_END);

        setUp();
    }


    public void setUp() {
        this.setTitle("Notification");
        this.setModal(true);
        this.setSize(300, 100);
        relocate();
    }

    protected void relocate() {
        Rectangle owner = getOwner().getBounds();
        int width = getWidth();
        int height = getHeight();
        int x = owner.x + (owner.width - width) / 2;
        int y = owner.y + (owner.height - height) / 2;
        setBounds(x, y, width, height);
    }

    public void setMessage(String message) {
        this.message.setText(message);
        this.message.setForeground(Color.RED);
        this.setVisible(true);
    }

    private class OkListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }
}


