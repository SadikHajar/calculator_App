package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class listeResults extends AppCompatActivity {
    ListView show ;
    ArrayList<String> list;
    List <String> sublist;
    ArrayList<String> finalList;
    ArrayAdapter<String> arrayadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_results);
        show=(ListView)findViewById(R.id.resultsList);
        list=getIntent().getStringArrayListExtra("myResults");
        if(list.size()<=10){
            arrayadapter= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,list);
        }
        else{
            sublist=list.subList(0,10);
            arrayadapter= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,sublist);
        }

        show.setAdapter(arrayadapter);
        arrayadapter.notifyDataSetChanged();


    }
}