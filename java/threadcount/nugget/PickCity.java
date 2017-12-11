package threadcount.nugget;

import android.app.Activity;
import android.content.SharedPreferences;

public class PickCity {

    SharedPreferences sharedpref;
    public PickCity(Activity activity){
        sharedpref = activity.getPreferences(Activity.MODE_PRIVATE);
    }
    String getCity(){
        return sharedpref.getString("city", "Cairo,EG");//Check
    }

    void setCity(String city){
        sharedpref.edit().putString("city", city).commit();
    }

}
