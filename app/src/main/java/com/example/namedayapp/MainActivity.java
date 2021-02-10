package com.example.namedayapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // so the first thing that you should always do is add the volley dependency
    // and give interner 5permission form the volley library


    private ConstraintLayout constraintLayout;
    private Button getNameTodayButton, getNameYestButton, getNameTomButton;
    private TextView textView;
    private RequestQueue mRequest;
    private Drawable drawable;
    private String countryCode;

    // so we will set up the spinner
    private Spinner spinner;

    // now we will create a boolean that will allow for the user to get the info
    // this will be dependent upon whether or not a country was chosen
    private boolean countryChosen;

    getTheJson getTheJson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // so we will first establish the context for the getTheJson class
        getTheJson = new getTheJson(MainActivity.this);

        // let us set up the constraint layout
        constraintLayout = (ConstraintLayout) findViewById(R.id.myConstraintLayout);

        // now we set up the textview
        textView = (TextView) findViewById(R.id.textView);

        // now we will set up the spinner
        spinner = (Spinner) findViewById(R.id.mySpinner);
        // so now we will set up the array adapter for the spinnetr as per the countries we set up in string.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.countries,R.layout.spinner);
        adapter.setDropDownViewResource(R.layout.spinner);
        // and now we will finally set up the adapter
        spinner.setAdapter(adapter);
        // so now we will set up an onitemselected listener
        spinner.setOnItemSelectedListener(this);

        // now we wiil set up all the individual buttons
        getNameTodayButton = (Button) findViewById(R.id.getNameForToday);
        getNameTomButton = (Button) findViewById(R.id.getNameForTom);
        getNameYestButton = (Button) findViewById(R.id.getNameForYest);

        mRequest = Volley.newRequestQueue(this);

        getNameTodayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // so we will only allow something to happenn with the item selectd if the boolean is true
                if (countryChosen) {
                    getTheJson.getNamesOfToday(countryCode, "today", new getTheJson.GetNamesToday() {
                        @Override
                        public void onError(String message) {
                            // so in the case of an error we will inform the user
                            Toast.makeText(MainActivity.this, "Error\nCouldn't provide names!!!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String country, String day) {
                            // so now the final step would be to set the textview to visibile and to the names that were obtained
                            textView.setText(getTheJson.getTheNames());
                            textView.setVisibility(View.VISIBLE);
                            Toast.makeText(MainActivity.this, "Hello World!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

        getNameYestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // so we will only allow something to happenn with the item selectd if the boolean is true
                if (countryChosen) {
                    getTheJson.getNamesOfYest(countryCode, "yesterday", new getTheJson.GetNamesYest() {
                        @Override
                        public void onError(String message) {
                            // so in the case of an error we will inform the user
                            Toast.makeText(MainActivity.this, "Error\nCouldn't provide names!!!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String country, String day) {
                            // so now the final step would be to set the textview to visibile and to the names that were obtained
                            textView.setText(getTheJson.getTheNames());
                            textView.setVisibility(View.VISIBLE);
                            Toast.makeText(MainActivity.this, "Hello World!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

        getNameTomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // so we will only allow something to happenn with the item selectd if the boolean is true
                if (countryChosen) {
                    getTheJson.getNamesOfTom(countryCode, "tomorrow", new getTheJson.GetNamesTom() {
                        @Override
                        public void onError(String message) {
                            // so in the case of an error we will inform the user
                            Toast.makeText(MainActivity.this, "Error\nCouldn't provide names!!!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String country, String day) {
                            // so now the final step would be to set the textview to visibile and to the names that were obtained
                            textView.setText(getTheJson.getTheNames());
                            textView.setVisibility(View.VISIBLE);
                            Toast.makeText(MainActivity.this, "Hello World!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // now here we will change all the deatils as per whichever country the user selected

        // first we will get the string for whichever item has at the time been sellected
        String textForItemSelected = adapterView.getItemAtPosition(i).toString();

        if (textForItemSelected.equals("Select a country")) {
            // so if no country is selected we will set it to false
            countryChosen = false;
            // first we need to create the drawable
            drawable = getResources().getDrawable(R.drawable.names);
            constraintLayout.setBackground(drawable);
        }
        // or else we will set it to true and turn the background into the flag of whatever country was selected
        else if (textForItemSelected.equals("Germany")) {
            countryChosen = true;
            // first we need to create the drawable
            drawable = getResources().getDrawable(R.drawable.germanyflag);
            constraintLayout.setBackground(drawable);
            // and we will set up the country code as per the country
            countryCode = "de";
        } else if (textForItemSelected.equals("Spain")) {
            countryChosen = true;
            drawable = getResources().getDrawable(R.drawable.spainflag);
            constraintLayout.setBackground(drawable);
            countryCode = "es";
        } else if (textForItemSelected.equals("USA")) {
            countryChosen = true;
            drawable = getResources().getDrawable(R.drawable.usaflag);
            constraintLayout.setBackground(drawable);
            countryCode = "us";
        } else if (textForItemSelected.equals("Greece")) {
            countryChosen = true;
            drawable = getResources().getDrawable(R.drawable.greeceflag);
            constraintLayout.setBackground(drawable);
            countryCode = "gr";
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}