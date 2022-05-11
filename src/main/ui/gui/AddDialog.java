package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDialog extends JDialog {

    protected JPanel root;
    protected JPanel buttonPanel;
    protected JPanel inputPanel;
    protected JTextField subjectInput;
    protected JTextField descriptionInput;
    protected JLabel subjectLabel;
    protected JLabel descriptionLabel;
    protected JButton addButton;
    protected JButton cancelButton;
    protected Boolean accepted;

    public AddDialog(Window owner) {
        super(owner);

        root = new JPanel(new BorderLayout());
        this.setContentPane(root);
        buttonPanel = new JPanel();
        inputPanel = new JPanel();

        subjectLabel = new JLabel("Enter Subject:");
        descriptionLabel = new JLabel("Enter Description:");

        subjectInput = new JTextField(10);
        descriptionInput = new JTextField(10);

        inputPanel.add(subjectLabel);
        inputPanel.add(subjectInput);
        inputPanel.add(descriptionLabel);
        inputPanel.add(descriptionInput);

        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");
        addButton.addActionListener(new AddListener());
        cancelButton.addActionListener(new CancelListener());
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        root.add(inputPanel, BorderLayout.CENTER);
        root.add(buttonPanel, BorderLayout.PAGE_END);

        accepted = false;
    }

    public String getSubjectValue() {
        String subject = subjectInput.getText();
        return subject;
    }

    public String getDescriptionValue() {
        String description = descriptionInput.getText();
        return description;
    }

    public Boolean setUp() {
        this.setTitle("Add Homework");
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

    private class AddListener implements ActionListener {
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
}
