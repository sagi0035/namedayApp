package com.example.namedayapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

// so note here that because we will be using the API whitin multiple buttons
// we will want the queue to be re-used over and over
// and sepertaing out the class allows you to do just that specifically
public class MySingleton {

    // so the first thing that we will do is set up the singleton, reuestque and context for when the singleton is called
    private static MySingleton instance;
    private RequestQueue requestQueue;
    //private ImageLoader imageLoader;
    private static Context ctx;

    // so as per this method we will only create a new request queue if there is no current one open]
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    // similarly we will only allow for the creation of a new MySingleton instance if the current value is null
    public static synchronized MySingleton getInstance(Context context) {
        // so here note that only if the instance is null do we we create a new instance of the singleton (where we create a requestqueue)
        if (instance == null) {
            instance = new MySingleton(context);
        }
        return instance;
    }

    // so we will create the MySingleton method to handle the stuff
    private MySingleton(Context context) {
        // so this establishes the context of where the singleton method was called
        ctx = context;
        // so there will be only one version of the request queue each time
        requestQueue = getRequestQueue();

    }

    // now we will actually add the request queue
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    /*
    public ImageLoader getImageLoader() {
        return imageLoader;
    }
    */


}
