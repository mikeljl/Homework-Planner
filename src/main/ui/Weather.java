package ui;


import java.io.IOException;
import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.net.URLConnection;
import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.*;


public class Weather {




    private Map<String, Object> restMap;
    private Map<String, Object> mainMap;
    private Map<String, Object> windMap;
    private String location;
    private String description;
    private double temperature;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private double humidity;
    private String iconCode;

    public Weather(String location) {
        this.location = location;
        weatherData(location);
    }


    public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String, Object>>() {
                }.getType()
        );
        return map;
    }

    public void weatherData(String location) {
        String apiKey = "c0b51f45a5591d2c733eec10c78b6cba";
        this.location = location;
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q="
                + location + "&appid=" + apiKey + "&units=metric";
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

            Map<String, Object> restMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(restMap.get("main").toString());
            ArrayList list = (ArrayList) restMap.get("weather");
            LinkedTreeMap<String, Object> a = (LinkedTreeMap<String, Object>) list.get(0);

            setUpField(mainMap, a);
            printInfo();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpField(Map<String, Object> mainMap, LinkedTreeMap<String, Object> a) {
        this.description = (String) a.get("description");
        this.temperature = (double) mainMap.get("temp");
        this.feelsLike = (double) mainMap.get("feels_like");
        this.tempMax = (double) mainMap.get("temp_max");
        this.tempMin = (double) mainMap.get("temp_min");
        this.humidity = (double) mainMap.get("humidity");
        this.iconCode = (String) a.get("icon");
    }

    private void printInfo() {
        System.out.println("Weather: ");
        System.out.println(" Current Temperature: " + this.temperature);
        System.out.println(" Feels Like: " + this.feelsLike);
        System.out.println(" Highest Temperature Today: " + this.tempMax);
        System.out.println(" Lowest Temperature Today: " + this.tempMin);
        System.out.println(" Humidity: " + this.humidity + "%");
    }


    public String getDescription() {
        return description;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getIconCode() {
        return iconCode;
    }
}
