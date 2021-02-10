package com.example.namedayapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

// so we will handle all the json requests within a seperate class
public class getTheJson {

    // so first we will set up the url
    public static final String QUERY_FOR_NAMEDAY_PART_ONE = "https://api.abalin.net/";
    public static final String QUERY_FOR_NAMEDAY_PART_TWO = "?country=";

    // these queries will then in turn be filled out with the country and the day
    String day;
    String country;

    // and we will have a variable for the string of names
    String getTheNames;

    // and finally we will also create a variable for the context which will be established on the call
    Context context;
    public getTheJson(Context context) {
        this.context = context;
    }


    // so we will be using lambdas to obtain the names and so we will obviously need to create the interface
    // the interfaces should be built to handle any errors too
    public interface GetVolleyResponse {
        void onError(String message);

        // we will get the names to be used late in mainactivity
        void onResponse(String names);
    }

    // to obtain the names we will be using jsonobject requests and jsonobjects
    // the only thing that will differ is the url so it is easiest to create a method and call them individually
    public void forEase(String url, final String country, final GetVolleyResponse getVolleyResponse) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject("data");
                            JSONObject jsonObject2 = jsonObject.getJSONObject("namedays");
                            getTheNames = jsonObject2.getString(country);
                            // now we set the onresponse to be able to change the deatils in mainactivity when this is a success
                            getVolleyResponse.onResponse(getTheNames);
                        } catch (JSONException e) {
                            Toast.makeText(context, "Problem", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "We have an error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        // and now we will add this to the request queue
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    // so we will create the method to get the names
    public void getNamesOfToday(final String country, String day, final GetVolleyResponse getVolleyResponse) {
        String url = QUERY_FOR_NAMEDAY_PART_ONE + day + QUERY_FOR_NAMEDAY_PART_TWO + country;
        forEase(url,country,getVolleyResponse);
    }

    public void getNamesOfTom(final String country, String day, final GetVolleyResponse getVolleyResponse) {
        String url = QUERY_FOR_NAMEDAY_PART_ONE + day + QUERY_FOR_NAMEDAY_PART_TWO + country;
        forEase(url,country,getVolleyResponse);
    }

    public void getNamesOfYest(final String country, String day, final GetVolleyResponse getVolleyResponse) {
        String url = QUERY_FOR_NAMEDAY_PART_ONE + day + QUERY_FOR_NAMEDAY_PART_TWO + country;
        forEase(url,country,getVolleyResponse);
    }


}
