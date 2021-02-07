package com.example.namedayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // so the first thing that you should always do is add the volley dependency
    // and give interner 5permission form the volley library


    private Button button;
    private RequestQueue mRequest;

    // so we will set up the spinner
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // now we will set up the spinner
        spinner = (Spinner) findViewById(R.id.mySpinner);
        // so now we will set up the array adapter for the spinnetr as per the countries we set up in string.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.countries,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // and now we will finally set up the adapter
        spinner.setAdapter(adapter);
        // so now we will set up an onitemselected listener
        spinner.setOnItemSelectedListener(this);


        button = (Button) findViewById(R.id.getNameForToday);
        mRequest = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJSON();
            }
        });

    }

    public void getJSON() {
        String url = "https://api.abalin.net/yesterday?country=us&timezone=America%2FLos_Angeles";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject("data");
                            JSONObject jsonObject2 = jsonObject.getJSONObject("namedays");
                            String getIt = jsonObject2.getString("us");
                            Toast.makeText(MainActivity.this, getIt, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Problem", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "We have an error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        mRequest.add(jsonObjectRequest);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // now here we will change all the deatils as per whichever country the user selected

        // first we will get the string for whichever item has at the time been sellected
        String textFirItemSelected = adapterView.getItemAtPosition(i).toString();

        // so if no country is selected we will
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}