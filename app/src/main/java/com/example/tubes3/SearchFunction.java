package com.example.tubes3;

import com.example.tubes3.model.MangaModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SearchFunction {
    private ArrayList<MangaModel> listManga;

    public SearchFunction(ArrayList<MangaModel> listManga){
        this.listManga = listManga;
    }
    public void sortAscending(){
        Collections.sort(listManga);
    }
    public ArrayList<MangaModel> search(String keyword){

        return this.listManga;
    }
}
