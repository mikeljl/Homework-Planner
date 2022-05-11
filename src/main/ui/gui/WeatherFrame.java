package ui.gui;

import ui.Weather;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherFrame extends JFrame {

    private Weather weather;
    private JPanel root;
    private JLabel title;
    private JLabel picture;
    private JTextArea information;

    public WeatherFrame(String title, String location) {
        super(title);
        weather = new Weather(location);
        this.setSize(300, 300);
        this.setVisible(true);
        HomePageFrame.centerInScreen(this);

        root = new JPanel();
        this.setContentPane(root);
        root.setBackground(new Color(150, 173, 255));
        root.setLayout(new BorderLayout());
        root.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        this.title = new JLabel(location.toUpperCase() + " Weather");
        this.title.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        this.title.setForeground(Color.WHITE);

        root.add(this.title, BorderLayout.PAGE_START);
        picture = new JLabel();
        picture.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        picture.setPreferredSize(new Dimension(100, 100));

        setIcon();
        root.add(picture, BorderLayout.CENTER);
        setText();
        root.add(information, BorderLayout.LINE_START);
    }

    private void setText() {
        String description = "\nDescription: \n" + weather.getDescription();
        String temp = "Temperature: \n" + weather.getTemperature();
        String feel = "Feels Like: \n" + weather.getFeelsLike();
        String min = "Lowest Temperature: \n" + weather.getTempMin();
        String max = "Highest Temperature: \n" + weather.getTempMax();
        String hum = "Humidity: \n" + weather.getHumidity() + "%";

        information = new JTextArea();
        information.setEditable(false);
        information.setBackground(new Color(150, 173, 255));

        information.setText(description + "\n" + temp + "\n" + feel + "\n" + min + "\n" + max + "\n" + hum);
    }

    private void setIcon() {
        URL url = null;
        try {
            url = new URL("http://openweathermap.org/img/wn/" + weather.getIconCode() + "@2x.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Image image = null;
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(image);
        picture.setIcon(icon);
    }
}
