package threadcount.nugget;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Date;

public class LoginSharedPreference {
    public static final String name = "testing@sunshine.com";
    public static final String pwd = "tf71wqr";
    public static final long first_log = new Date().getTime();
    public static final long next_log = first_log + 604800000l;
    public static final boolean log = true;
    public static final String PREF_NAME = "login_data" ;

    static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setData(Context context, String username, String password, long login) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString("name", username);
        editor.putString("pwd", password);
        editor.putLong("first_log", login);
        editor.commit();
    }

    public static String getData(Context context) {
        return getSharedPreferences(context).getString(PREF_NAME, "");
    }
    
    public static void clearData(Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear();
        editor.commit();
    }
}
