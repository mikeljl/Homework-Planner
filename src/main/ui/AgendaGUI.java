package ui;


import model.Homework;
import model.HomeworkAgenda;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* ListDemo.java requires no other files. */
public class AgendaGUI extends JPanel
        implements ListSelectionListener {
    private final JList list;
    protected final DefaultListModel listModel;

    private static final String addHomework = "Add Homework";
    private static final String deleteHomework = "Delete Homework";
    protected final JButton deleteButton;
    protected final JTextField homework;
    protected final JTextField subject;
    protected HomeworkAgenda agenda;

    @SuppressWarnings("checkstyle:MethodLength")
    public AgendaGUI() {
        super(new BorderLayout());
        agenda = new HomeworkAgenda();


        listModel = new DefaultListModel();
        listModel.addElement("Time to work hard!");

        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        JButton hireButton = new JButton(addHomework);
        AddListener addListener = new AddListener(hireButton);
        hireButton.setActionCommand(addHomework);
        hireButton.addActionListener(addListener);
        hireButton.setEnabled(false);


        deleteButton = new JButton(deleteHomework);
        deleteButton.setActionCommand(deleteHomework);
        deleteButton.addActionListener(new DeleteListener());

        homework = new JTextField();
        homework.setPreferredSize(new Dimension(250, 40));
        homework.addActionListener(addListener);
        homework.getDocument().addDocumentListener(addListener);
        String name = listModel.getElementAt(
                list.getSelectedIndex()).toString();
        JLabel label1 = new JLabel("Enter description HERE");

        subject = new JTextField();
        subject.setPreferredSize(new Dimension(100, 20));
        JLabel label2 = new JLabel("Enter subject HERE");


        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout(5, 1));
        buttonPane.add(deleteButton);
        buttonPane.add(Box.createHorizontalStrut(10));
        buttonPane.add(Box.createHorizontalStrut(10));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(subject);
        buttonPane.add(label2);
        buttonPane.add(homework);
        buttonPane.add(label1);
        buttonPane.add(hireButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = list.getSelectedIndex();
            listModel.remove(index);

            int size = listModel.getSize();

            if (size == 0) {
                deleteButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    //This listener is shared by the text field and the hire button.
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private final JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {

            Homework hw = new Homework(subject.getText(), homework.getText());
            agenda.addHomework(hw);

            listModel.addElement("Subject: " + hw.getSubject() + "    Description: "
                    + hw.getDescription());

            //Reset the text field.
            homework.requestFocusInWindow();
            homework.setText("");
            subject.requestFocusInWindow();
            subject.setText("");

        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            //No selection, disable fire button.
            //Selection, enable the fire button.
            deleteButton.setEnabled(list.getSelectedIndex() != -1);
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Homework Agenda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new AgendaGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    /*public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }*/
    class Splash extends JWindow {
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
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            j.setVisible(false);

        }
    }


    public static void main(String[] args) {
        ui.splash s = new ui.splash();
        createAndShowGUI();
    }
}
