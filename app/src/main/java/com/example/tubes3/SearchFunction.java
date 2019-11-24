package com.example.tubes3;

import android.util.Log;

import com.example.tubes3.model.MangaModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
//    public int searchRec(String keyword,int left,int right){
//
//
//        if (right >= left) {
//            int mid = 1 + (right - left) / 2;
//            Log.d("alllist",listManga.get(mid).getTitle());
//            if (listManga.get(mid).getTitle().indexOf(keyword)!=-1){
//                Log.d("founded","founded");
//                newList.add(listManga.get(mid));
//                return mid;
//            }
//            if (listManga.get(mid).getTitle().compareTo(keyword)>0){
//                return searchRec(keyword, left, mid - 1);
//            }else {
//                return searchRec(keyword, mid + 1, right);
//            }
//        }
//
//        return -1;
//    }
}
