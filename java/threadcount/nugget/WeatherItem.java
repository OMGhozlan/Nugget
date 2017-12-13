package threadcount.nugget;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class WeatherItem {
    public int id;
    public String date;
    public double max;
    public double min;
    public String cond;

    public WeatherItem(int id, String date, double max, double min, String cond) {
        this.id = id;
        this.date = date;
        this.max = max;
        this.min = min;
        this.cond = cond;
    }

    public static ArrayList<WeatherItem> listData(){
        ArrayList<WeatherItem> items = new ArrayList<>();
        /*items.add(new WeatherItem(0, "Tomorrow", 25.0d, 19.0d, "Windy"));
        items.add(new WeatherItem(1, "Jumbo", 25.0d, 19.0d, "Windy"));*/
        final JSONObject[] json = {null};
        new Thread() {
            public void run() {
                json[0] = WeatherData.getJSON(null, "Cairo,EG");
            }
        }.start();
        while(json[0] == null) {}
        /*JSONObject json = WeatherData.getJSON(null, "Cairo,EG");*/
        for(int i = 0; i < 7; i++){
            try {
                JSONObject details = json[0].getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0);
                JSONObject main = json[0].getJSONArray("list").getJSONObject(i);
                items.add(new WeatherItem(i, "Day " + Integer.toString(i),
                        main.getJSONObject("temp").getDouble("max"),
                        main.getJSONObject("temp").getDouble("min"),
                        details.getString("description").toUpperCase(Locale.US)));
            } catch (Exception e) {
                Log.e("Nugget>Weather", "One or more fields missing in JSON data");
            }
        }
        return items;
    }
}
