package com.example.VehiclesMap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class IdList extends AppCompatActivity  {
  ArrayList<String> idlist;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_id_list);
    Intent intent = getIntent();
    idlist= intent.getStringArrayListExtra("idlist");
    if(!idlist.isEmpty()){
    ListView listView;
    ArrayAdapter arrayAdapter;
    listView = findViewById(R.id.list);
    arrayAdapter = new ArrayAdapter(this,R.layout.list_item, R.id.id, idlist);
    listView.setAdapter(arrayAdapter);
    }
    else{
      Log.e("empty list","there are no cars in the polygon");
    }
  }
}