package ui.gui;

import model.Homework;
import model.HomeworkAgenda;
import persistence.JsonReader;
import persistence.JsonWriter;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HomePageFrame extends JFrame {

    private JPanel root;
    private JPanel buttonPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JButton showTimeButton;
    private JButton editButton;
    private JButton weatherButton;
    private JLabel titleLabel;
    private JMenuBar menuBar;
    private JMenu file;
    private JMenuItem save;
    private JMenuItem load;
    private JList<String> homeworkList;
    private JScrollPane scrollPane;
    private DefaultListModel<String> listModel;
    private HomeworkAgenda hwAgenda;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Border buttonBorder;

    public HomePageFrame(String title) {
        super(title);
        hwAgenda = new HomeworkAgenda();
        jsonWriter = new JsonWriter("./data/homeworkagenda.json");
        jsonReader = new JsonReader("./data/homeworkagenda.json");
        buttonPanel = new JPanel();
        buttonBorder = new LineBorder(Color.BLACK, 2);

        setUpAgendaList();
        setUpRootPanel();
        makeTitleLabel();
        makeButtons();
        createMenuBar();
        addActionListeners();
        addPictureToButton("image/add.png", addButton);
        addPictureToButton("image/delete.png", deleteButton);
        addPictureToButton("image/editing.png", editButton);
        addPictureToButton("image/clock.png", showTimeButton);
        addPictureToButton("image/cloudy.png", weatherButton);
        addButtonToPanel();
        addPanelsToFrame();
    }

    private void setUpAgendaList() {
        listModel = new DefaultListModel<>();
        homeworkList = new JList(listModel);
        scrollPane = new JScrollPane(homeworkList);
        homeworkList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListCellRenderer renderer = new MyListCellRenderer();
        homeworkList.setCellRenderer(renderer);
    }

    private void setUpRootPanel() {
        root = new JPanel();
        root.setLayout(new BorderLayout());
        this.setContentPane(root);
        root.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
    }

    private void makeTitleLabel() {
        titleLabel = new JLabel("Today");
        titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        titleLabel.setForeground(new Color(66, 105, 245));
    }

    private void addPanelsToFrame() {
        root.add(titleLabel, BorderLayout.PAGE_START);
        root.add(scrollPane, BorderLayout.CENTER);
        root.add(buttonPanel, BorderLayout.PAGE_END);
    }

    private void addButtonToPanel() {
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        buttonPanel.add(showTimeButton);
        buttonPanel.add(weatherButton);
    }

    private void addPictureToButton(String s, JButton editButton) {
        try {
            Image image = ImageIO.read(getClass().getResource(s));
            Image newimg = image.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            editButton.setIcon(new ImageIcon(newimg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editButton.setBorder(buttonBorder);
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();
        file = new JMenu("File");
        menuBar.add(file);
        this.setJMenuBar(menuBar);

        save = new JMenuItem("Save");
        load = new JMenuItem("Load");

        file.add(save);
        file.add(load);
    }

    private void addActionListeners() {
        addButton.addActionListener(new AddListener());
        deleteButton.addActionListener(new DeleteListener());
        editButton.addActionListener(new EditListener());
        showTimeButton.addActionListener(new ShowTimeListener());
        weatherButton.addActionListener(new WeatherListener());
        save.addActionListener(new SaveListener());
        load.addActionListener(new LoadListener());
    }

    // MODIFIES: this
    // EFFECTS: makes a button according to parameter specification
    public void makeButtons() {
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        editButton = new JButton("Edit");
        showTimeButton = new JButton("Time");
        weatherButton = new JButton("Weather");
    }

    private class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            runDialog();
        }
    }

    private class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = homeworkList.getSelectedIndex();
            if (index < 0) {
                notifyUser("Nothing is selected");
            } else {
                String selected = homeworkList.getSelectedValue();
                if (confirm(selected)) {
                    listModel.remove(index);
                    hwAgenda.deleteHomework(hwAgenda.getAgenda().get(index));
                }
            }
        }
    }

    private class ShowTimeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new CurrentTime();
        }
    }

    private class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (listModel.isEmpty()) {
                notifyUser("No Homework Added, can't save");
            } else {
                try {
                    jsonWriter.open();
                    jsonWriter.write(hwAgenda);
                    jsonWriter.close();
                } catch (FileNotFoundException ecp) {
                    ecp.printStackTrace();
                }
            }
        }
    }

    private class LoadListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                hwAgenda = jsonReader.read();
                for (Homework hw : hwAgenda.getAgenda()) {
                    listModel.addElement("Subject: " + hw.getSubject() + "    Description: "
                            + hw.getDescription());
                }
            } catch (IOException ecp) {
                ecp.printStackTrace();
            }

        }
    }

    private class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = homeworkList.getSelectedIndex();
            if (index < 0) {
                notifyUser("Select a homework to edit");
            } else {
                edit(index);
            }
        }
    }

    private class WeatherListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            runLocationDialog();
            
        }
    }

    private void runLocationDialog() {
        LocationDialog dlg = new LocationDialog(this);
        if (dlg.setUp()) {
            String location = dlg.getLocationValue();
            if (location.length() == 0) {
                notifyUser("City name can't be empty");
            } else {
                showWeather(location);
            }
        }
    }

    private void showWeather(String location) {
        WeatherFrame weather = new WeatherFrame("Weather", location);
    }


    public static void centerInScreen(Window win) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screenSize.width - win.getWidth()) / 2;
        int y = (screenSize.height - win.getHeight()) / 2;

        win.setLocation(x, y);
    }

    private void runDialog() {
        AddDialog dlg = new AddDialog(this);
        if (dlg.setUp()) {
            String subject = dlg.getSubjectValue();
            String description = dlg.getDescriptionValue();

            if (subject.length() == 0 || description.length() == 0) {
                notifyUser("Complete entering before adding");

            } else {
                addToList(subject, description);
            }
        }
    }

    private void addToList(String subject, String description) {
        Homework newHomework = new Homework(subject, description);
        hwAgenda.addHomework(newHomework);
        listModel.addElement("Subject: " + subject + "  Description: " + description);
    }

    private boolean confirm(String toDelete) {
        int selected = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete homework: " + toDelete,
                "Confirm",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (selected == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void edit(int index) {
        EditDialog dlg = new EditDialog(this);

        if (dlg.setUp()) {
            String subject = dlg.getSubjectValue();
            String description = dlg.getDescriptionValue();

            if (subject.length() == 0 || description.length() == 0) {
                notifyUser("Complete entering before editing");

            } else {
                editList(subject, description, index);
            }
        }
    }

    private void editList(String subject, String description, int index) {
        Homework editedHomework = new Homework(subject, description);
        listModel.set(index, "Subject: " + subject + "  Description: " + description);
        hwAgenda.deleteHomework(hwAgenda.getAgenda().get(index));
        hwAgenda.addHomework(editedHomework);
    }

    private void notifyUser(String message) {
        InfoDialog dlg = new InfoDialog(this);
        dlg.setMessage(message);
    }
}

