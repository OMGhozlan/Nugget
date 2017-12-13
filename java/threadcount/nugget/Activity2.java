package threadcount.nugget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        /*makeList();*/
        makeWeatherList();
    }

    public void logout(View view){
        SharedPreferences sharedpref = getSharedPreferences(Activity1.login_data, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref.edit();
        editor.clear();
        editor.commit();
    }

    private void makeList(){
        Toast.makeText(getApplicationContext(), "Listing items..", Toast.LENGTH_SHORT).show();
        ArrayList<Item> items = new ArrayList<Item>();
        ItemAdapter adapter = new ItemAdapter(this, Item.listItems());
        ListView listView = (ListView) findViewById(R.id.list_items);
        listView.setAdapter(adapter);
    }

    private void makeWeatherList(){
        Toast.makeText(getApplicationContext(), "Listing days..", Toast.LENGTH_SHORT).show();
        ArrayList<WeatherItem> items = new ArrayList<>();
        WeatherItemAdapter adapter = new WeatherItemAdapter(this, WeatherItem.listData());
        ListView listView = (ListView) findViewById(R.id.list_items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Activity2.this, WeatherActivity.class);
                startActivity(intent);
            }
        });
    }
}
