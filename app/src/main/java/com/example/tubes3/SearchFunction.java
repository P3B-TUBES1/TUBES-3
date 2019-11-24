package com.example.tubes3;

import android.util.Log;

import com.example.tubes3.model.MangaModel;

import java.util.ArrayList;

public class SearchFunction {
    private ArrayList<MangaModel> listManga;
    private ArrayList<MangaModel> newList;
    public SearchFunction(ArrayList<MangaModel> listManga){
        this.listManga = listManga;
        newList = new ArrayList<MangaModel>();
    }
    public ArrayList<MangaModel> search(String keyword){
        keyword = keyword.toLowerCase();
        for(int i=0;i<listManga.size();i++){
            String currentTittle = listManga.get(i).getTitle().toLowerCase();
            if(currentTittle.indexOf(keyword)!=-1){
                Log.d("newlist",listManga.get(i).getTitle());
               newList.add(listManga.get(i));
            }
        }
        return newList;
    }
}
