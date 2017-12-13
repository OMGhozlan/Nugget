package threadcount.nugget;

import java.net.URL;
import org.json.JSONObject;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class WeatherData {

    /*private static final String OPEN_WEATHER_MAP_API =
            "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";*/
  /*http://api.openweathermap.org/data/2.5/weather?q=Cairo,%20EG&appid=5895cf17531b815e36a728c286b1a0ae&units=metric&cnt=1*/
    private static final String OPEN_WEATHER_MAP_API =
            "http://api.openweathermap.org/data/2.5/forecast/daily?q=%s&units=metric&cnt=7";

    public static JSONObject getJSON(Context context, String city){
        try {
            URL url = new URL(String.format(OPEN_WEATHER_MAP_API, city));
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();
            if(context != null) {
                connection.addRequestProperty("x-api-key",
                        context.getString(R.string.open_weather_maps_api_key));
            }else{
                connection.addRequestProperty("x-api-key",
                        "c0c4a4b4047b97ebc5948ac9c48c0559");
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(4096);
            String tmp;
            while((tmp=reader.readLine())!= null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            if(data.getInt("cod") != 200){
                return null;
            }
            return data;
        }catch(Exception e){
            Log.e("Nugget>JSON", e.getMessage());
            return null;
        }
    }
}