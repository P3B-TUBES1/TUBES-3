package com.example.tubes3;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes3.model.MangaChapterInfoModel;
import com.example.tubes3.model.MangaChapterModel;
import com.example.tubes3.model.MangaModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class CallVolley {
    protected final String BASE_URL = "https://www.mangaeden.com/api/";
    protected Gson gson;
    protected Context context;
    protected RequestQueue queue;
    protected Presenter presenter;
    public CallVolley(Context context,Presenter presenter) {
        this.context = context;
        this.gson  = new Gson();
        this.queue = Volley.newRequestQueue(context);
        this.presenter = presenter;
    }

    public void getMangaList(){
        String url = BASE_URL+"list/0/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray temp;
                try {
                    ArrayList<MangaModel> listManga = new ArrayList<MangaModel>();
                    temp = response.getJSONArray("manga");
                    for(int i=0;i<temp.length();i++){
                        JSONObject tempObj = temp.getJSONObject(i);
                        listManga.add(new MangaModel(tempObj.getString("i"),tempObj.getString("t"),tempObj.getString("im")));
                        //Log.d("mangalist",new MangaModel(tempObj.getString("i"),tempObj.getString("t"),tempObj.getString("im")).toString());
                    }
                    presenter.addManga(listManga);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        this.queue.add(jsonObjectRequest);
    }

    public void getMangaInfo(String mangaID){
        final String url = BASE_URL+"manga/" + mangaID + "/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String mangaTitle = response.getString("title");
                    String[] genre = gson.fromJson(response.getJSONArray("categories").toString(), String[].class);
                    String description = response.getString("description");
                    String artistName = response.getString("author");
                    String urlImageCover = response.getString("image");
                    String releaseDate = response.getString("released");
                    MangaChapterInfoModel mangaInfo = new MangaChapterInfoModel(mangaTitle, genre, description, artistName, urlImageCover, releaseDate);
                    List<MangaChapterModel> listChapter = new LinkedList<MangaChapterModel>();
                    JSONArray chapters = response.getJSONArray("chapters");
                    for (int i = 0; i < chapters.length(); i++) {
                        try {
                            JSONArray chapter = chapters.getJSONArray(i);
                            String chapterId = chapter.getString(3);
                            String chapterTitle = chapter.getString(2);
                            String chapterDate = chapter.getLong(1)+"";
                            String urlImage = urlImageCover;
                            listChapter.add(new MangaChapterModel(chapterId,chapterTitle, chapterDate, urlImage, chapters.length()-i));
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                    presenter.updateMangaChapterInfo(listChapter,mangaInfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        this.queue.add(jsonObjectRequest);
    }

    public void getChapter(String chapterID, final int indeks){
        String url = BASE_URL+"chapter/" + chapterID + "/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String[] imgUrl=null;
                JSONArray temp;
                try {
                    temp = response.getJSONArray("images");
                    imgUrl = new String[temp.length()];
                    for(int i=temp.length()-1;i>=0;i--){
                        imgUrl[i] = temp.getJSONArray(i).getString(1);
                    }
                    presenter.updateMangaContent(imgUrl,indeks);
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        this.queue.add(jsonObjectRequest);
    }

}
