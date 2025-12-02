package com.example.comp2000new;

import static java.lang.Character.getType;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;



public class Api {
    public interface CustomerCallback {
        void onSuccess(Customer customer);

        void onError(String error);
    }

    private static final String BASE_URL = "http://10.0.2.2/comp2000/coursework";;
    private static RequestQueue requestQueue;
    private static final Gson gson = new Gson();

    // Initialize RequestQueue if needed


    private static void initQueue(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
    }

    public static void init(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
    }


    public static void get_customer(Context context, String username, Response.Listener<Customer> listener,
                                    Response.ErrorListener errorListener) {

        String url = BASE_URL + "/read_user/10925263/" + username;
        Log.d("API_URL", url);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    JSONObject userJson = null;
                    try {
                        userJson = response.getJSONObject("user");
                    } catch (JSONException e) {
                        errorListener.onErrorResponse(new VolleyError(e));
                        return;
                    }
                    Customer customer = gson.fromJson(userJson.toString(), Customer.class);
                    listener.onResponse(customer);
                },
                error -> errorListener.onErrorResponse(error)
        );

        requestQueue.add(request);
    }

}


