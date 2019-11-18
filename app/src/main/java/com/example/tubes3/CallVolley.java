package com.example.tubes3;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;


public class CallVolley {
    protected final String BASE_URL = "https://www.mangaeden.com/api/";
    protected Gson gson;
    protected Context context;
    protected RequestQueue queue;

    public CallVolley(Context context) {
        this.context = context;
        this.gson  = new Gson();
        this.queue = Volley.newRequestQueue(context);
    }

    public void getMangaList(){
        String url = BASE_URL+"/api/list/0/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        this.queue.add(jsonObjectRequest);
    }

    public void getMangaInfo(String mangaID){
        String url = BASE_URL+"/manga/" + mangaID + "/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        this.queue.add(jsonObjectRequest);
    }

    public void getChapter(String chapterID){
        String url = BASE_URL+"/chapter/" + chapterID + "/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        this.queue.add(jsonObjectRequest);
    }

}
