package threadcount.nugget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity1 extends AppCompatActivity {
    public static final String name = "Osama";
    public static final String pwd = "123lol";
    public static final String login_data = "login_data" ;
    Button login_btn;
    Button deleteme_btn;
    EditText usr_field, pwd_field;
    SharedPreferences sharedpref;
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
        sharedpref = getSharedPreferences(login_data, Context.MODE_PRIVATE);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usr_field.getText().toString();
                String password = pwd_field.getText().toString();
                SharedPreferences.Editor editor = sharedpref.edit();
                editor.putString(name, username);
                editor.putString(pwd, password);
                editor.commit();
                if(username.equalsIgnoreCase("Osama") && password.equals("123lol")) {
                    Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, Activity2.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Failed to login", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deleteme_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(context, BookActivity.class);
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
