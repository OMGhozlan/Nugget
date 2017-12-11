package threadcount.nugget;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        makeList();
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
}
