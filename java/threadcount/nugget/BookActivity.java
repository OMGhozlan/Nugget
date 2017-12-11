package threadcount.nugget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    TextView name, id;
    ListView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        final ArrayList<String> data = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.listviewitem, R.id.textViewdelete, data);
        name = (TextView) findViewById(R.id.name);
        id = (TextView) findViewById(R.id.id);
        v = (ListView) findViewById(R.id.items);
        RequestQueue rq = Volley.newRequestQueue(this);
        JsonArrayRequest jar = new JsonArrayRequest("https://goo.gl/52Kwga", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray resp) {
                Log.d("responseJson", String.valueOf(resp.length()));
                for(int i = 0; i < resp.length(); i++){
                    try{
                        JSONObject jo = resp.getJSONObject(i);
                        String id_s = jo.getString("id");
                        String name_s = jo.getString("name");
                        //name.setText(name_s);
                        //id.setText(id_s);
                        data.add("ID: " + id_s + " Name: " + name_s);
                        //Toast.makeText(getApplicationContext(), "ID: " + id + " Name: " + name, Toast.LENGTH_LONG);

                    }catch (JSONException e){

                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                Toast.makeText(getApplicationContext(), "Error in shabaka", Toast.LENGTH_LONG);
            }
        });
        v.setAdapter(adapter);
        rq.add(jar);

        StringRequest sr = new StringRequest("https://goo.gl/52Kwga", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                ArrayList<Book> books = gson.fromJson(response, new TypeToken<ArrayList<Book>>()
                {
                }.getType());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error in shabaka", Toast.LENGTH_LONG).show();
            }
        });
        rq.add(sr);
    }

}
