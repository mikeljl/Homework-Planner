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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import persistence.JsonReader;
import persistence.JsonWriter;

/* ListDemo.java requires no other files. */
public class AgendaGUI extends JPanel implements ListSelectionListener {
    private final JList list;
    protected final DefaultListModel listModel;

    private static final String addHomework = "Add Homework";
    private static final String deleteHomework = "Delete Homework";
    protected final JButton deleteButton;
    protected final JTextField homework;
    protected final JTextField subject;
    protected HomeworkAgenda homeworkAgenda;
    private JsonWriter jsonWriter = new JsonWriter("./data/homeworkagenda.json");
    private JsonReader jsonReader = new JsonReader("./data/homeworkagenda.json");
    private JPanel buttonPane;


    //EFFECT: constructs panels, buttons in gui

    @SuppressWarnings("methodlength")
    public AgendaGUI() {
        super(new BorderLayout());
        homeworkAgenda = new HomeworkAgenda();
        listModel = new DefaultListModel();
        //listModel.addElement("Time to work hard!");

        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        JButton addButton = new JButton(addHomework);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addHomework);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);


        deleteButton = new JButton(deleteHomework);
        deleteButton.setActionCommand(deleteHomework);
        deleteButton.addActionListener(new DeleteListener());

        JButton saveButton = new JButton("save");
        SaveListener saveListener = new SaveListener(saveButton);
        saveButton.setActionCommand("save");
        saveButton.addActionListener(saveListener);

        JButton loadButton = new JButton("load");
        LoadListener loadListener = new LoadListener(loadButton);
        loadButton.setActionCommand("load");
        loadButton.addActionListener(loadListener);

        homework = new JTextField();
        homework.setPreferredSize(new Dimension(250, 40));
        homework.addActionListener(addListener);
        homework.getDocument().addDocumentListener(addListener);
        JLabel label1 = new JLabel("Enter description HERE");

        subject = new JTextField();
        subject.setPreferredSize(new Dimension(100, 20));
        JLabel label2 = new JLabel("Enter subject HERE");


        //Create a panel that uses BoxLayout.
        buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout(5, 1));
        buttonPane.add(deleteButton);
        buttonPane.add(saveButton);
        buttonPane.add(Box.createHorizontalStrut(10));
        buttonPane.add(Box.createHorizontalStrut(10));
        buttonPane.add(subject);
        buttonPane.add(label2);
        buttonPane.add(homework);
        buttonPane.add(label1);
        buttonPane.add(addButton);
        buttonPane.add(loadButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }


    // EFFECTS: Creates frame
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

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("file");
        menuBar.add(fileMenu);

        JMenuItem saveItem = new JMenuItem("SAVE");
        JMenuItem loadItem = new JMenuItem("LOAD");

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    class SaveListener implements ActionListener {
        private final JButton button;

        // EFFECTS: contructs a new SaveListener
        public SaveListener(JButton button) {
            this.button = button;
        }

        //MODIFIES: this
        //EFFECTS: save current list of homework in homeworkAgenda
        public void actionPerformed(ActionEvent e) {

            try {
                jsonWriter.open();
                jsonWriter.write(homeworkAgenda);
                jsonWriter.close();
            } catch (FileNotFoundException var2) {
                System.out.println("wrong");
            }
        }
    }

    class LoadListener implements ActionListener {
        private final JButton button;

        //EFFCTS: contructs a new LoadListener
        public LoadListener(JButton button) {
            this.button = button;
        }

        //MODIFIES: this
        //EFFECTS: load the saved list of homework in homeworkAgenda to gui
        public void actionPerformed(ActionEvent e) {


            try {
                homeworkAgenda = jsonReader.read();

                for (Homework hw : homeworkAgenda.getAgenda()) {
                    listModel.addElement("Subject: " + hw.getSubject() + "    Description: "
                            + hw.getDescription());
                }

            } catch (IOException var2) {
                System.out.println("wrong");
            }
        }
    }





    class DeleteListener implements ActionListener {

        //MODIFIES: this
        //EFFECTS: delete selected homework in gui and in homeworkAgenda
        public void actionPerformed(ActionEvent e) {

            int index = list.getSelectedIndex();
            listModel.remove(index);
            homeworkAgenda.getAgenda().remove(index);

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

    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private final JButton button;


        //EFFECTS: constructs a new AddListener
        public AddListener(JButton button) {
            this.button = button;
        }


        //MODIFIES: this
        //EFFECTS: add the entered homework to gui and to homeworkAgenda
        public void actionPerformed(ActionEvent e) {

            Homework hw = new Homework(subject.getText(), homework.getText());
            homeworkAgenda.addHomework(hw);

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

        //EFFECTS: disable add button if the text box is empty
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //EFFECTS: enable button if the text box is not empty
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        //EFFECTS: enable button if enable status is true and it is not enabled yet
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        //EFFECTS: disable add button if the text box is empty
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    //EFFECTS: if no selection, disable delete button, if there is selection, enable the delete button.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            deleteButton.setEnabled(list.getSelectedIndex() != -1);
        }
    }

    //EFFECT: initialize gui
    public static void main(String[] args) {
        new Splash();
        createAndShowGUI();

    }
}
