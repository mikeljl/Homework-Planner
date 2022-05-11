package ui.gui;

import com.sun.tools.javac.comp.Check;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocationDialog extends JDialog {
    protected JPanel root;
    protected JPanel buttonPanel;
    protected JPanel inputPanel;
    protected JTextField locationInput;
    protected JLabel locationLabel;
    protected JButton checkButton;
    protected JButton cancelButton;
    protected Boolean accepted;

    public LocationDialog(Window owner) {
        super(owner);

        root = new JPanel(new BorderLayout());
        this.setContentPane(root);
        buttonPanel = new JPanel();
        inputPanel = new JPanel();

        locationLabel = new JLabel("Enter your city name:");

        locationInput = new JTextField(10);

        inputPanel.add(locationLabel);
        inputPanel.add(locationInput);

        checkButton = new JButton("Check");
        cancelButton = new JButton("Cancel");
        checkButton.addActionListener(new CheckListener());
        cancelButton.addActionListener(new CancelListener());
        buttonPanel.add(checkButton);
        buttonPanel.add(cancelButton);

        root.add(inputPanel, BorderLayout.CENTER);
        root.add(buttonPanel, BorderLayout.PAGE_END);

        accepted = false;
    }

    public Boolean setUp() {
        this.setTitle("Check Weather");
        this.setModal(true);
        this.setSize(200,200);
        relocate();
        this.setVisible(true);

        return accepted;
    }

    protected void relocate() {
        Rectangle owner = getOwner().getBounds();
        int width = getWidth();
        int height = getHeight();
        int x = owner.x + (owner.width - width) / 2;
        int y = owner.y + (owner.height - height) / 2;
        setBounds(x,y,width,height);
    }

    private class CheckListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            accepted = true;
            setVisible(false);
        }
    }

    private class CancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            accepted = false;
            setVisible(false);
        }
    }

    public String getLocationValue() {
        String location = locationInput.getText();
        return location;
    }
}
