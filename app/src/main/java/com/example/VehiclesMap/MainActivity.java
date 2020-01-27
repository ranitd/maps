package com.example.VehiclesMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
  ArrayList<LatLng> locationList=new ArrayList<>();
  HashMap<LatLng,String> idhash = new HashMap<>();

  Bundle bundle;
    String json;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getJson();
    bundle = new Bundle();
    bundle.putParcelableArrayList("locationList", locationList);

    locationList = bundle.getParcelableArrayList("locationList");

  }

  public void getJson() {
     json = null;
    try {
      InputStream is = getAssets().open("vehicles-location.json");

      int size = is.available();

      byte[] buffer = new byte[size];

      is.read(buffer);

      is.close();

      json = new String(buffer, "UTF-8");
      JSONArray jsonArray = new JSONArray(json);
      for(int i=0;i<jsonArray.length();i++) {
        JSONObject obj =jsonArray.getJSONObject(i);
        String id=obj.getString("id");
        JSONObject location = obj.getJSONObject("location");
        double x = location.getDouble("lat");
        double y = location.getDouble("lng");
        LatLng m = new LatLng(x, y);
        locationList.add(m);
        idhash.put(m,id);
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();

    }
  }


  public void MapOfVehicles(View view) {
    Intent intent = new Intent(MainActivity.this, MapOfVehicles.class);
    intent.putExtras(bundle);
    startActivity(intent);
  }

  public void DrawPolygon(View view) {
    Intent intent = new Intent(MainActivity.this, DrawPolygon.class);
    intent.putExtra("hashMap", idhash);
    startActivity(intent);
  }

}
