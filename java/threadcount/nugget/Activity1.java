package threadcount.nugget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Patterns;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class Activity1 extends AppCompatActivity {
    public static final String name = "testing@sunshine.com";
    public static final String pwd = "tf71wqr";
    public static final String login_data = "login_data" ;
    Button login_btn;
    Button deleteme_btn;
    EditText usr_field, pwd_field;
    /*SharedPreferences sharedpref;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        //addListenerOnButton();
        final Context context = this;
        login_btn = (Button) findViewById(R.id.login_btn);
        deleteme_btn = (Button) findViewById(R.id.deleteMe);
        usr_field = (EditText) findViewById(R.id.usr_field);
        pwd_field = (EditText) findViewById(R.id.pwd_field);
        /*sharedpref = getSharedPreferences(login_data, Context.MODE_PRIVATE);*/
        long curr_time = new Date().getTime();
        if(LoginSharedPreference.getData(Activity1.this).length() == 0  &&
                curr_time < LoginSharedPreference.next_log){
            Intent intent = new Intent(context, Activity2.class);
            startActivity(intent);
        }
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usr_field.getText().toString();
                String password = pwd_field.getText().toString();
                /*SharedPreferences.Editor editor = sharedpref.edit();
                editor.putString(name, username);
                editor.putString(pwd, password);
                editor.commit();
                editor.apply();*/
                long curr_time = new Date().getTime();
                if(Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                    if(username.equalsIgnoreCase("testing@sunshine.com") && password.equals("tf71wqr")){
                    Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();
                    /*Intent intent = new Intent(context, Activity2.class);*/
                    LoginSharedPreference.setData(Activity1.this, username, password, curr_time);
                    /*Intent intent = new Intent(context, WeatherActivity.class);*/
                    Intent intent = new Intent(context, Activity2.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid creditionals", Toast.LENGTH_SHORT).show();
                }
                }else{
                    Toast.makeText(getApplicationContext(), "Not a valid e-mail format", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deleteme_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(context, BookActivity.class);*/
                Intent intent = new Intent(context, WeatherActivity.class);
                startActivity(intent);
            }
        });
    }

    /*public void addListenerOnButton() {
        final Context context = this;
        login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Activity2.class);
                startActivity(intent);
            }
        });

    }*/

    private int hideKeyboard(){
        View view = getCurrentFocus();
        if(view != null){
            ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            return 0;
        }
        return 1;
    }

    private int validateUsername(){
        return 1;
    }

    private int validatePassword(){
        return 1;
    }
}
