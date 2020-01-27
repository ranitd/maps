package com.example.VehiclesMap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DrawPolygon extends AppCompatActivity implements OnMapReadyCallback/*, SeekBar.OnSeekBarChangeListener */{
  GoogleMap gMap;
  Button btndraw, btndelete,btnlist;
  Polygon polygon = null;
  List<LatLng> latLngList = new ArrayList<>();
  List<Marker> markerList = new ArrayList<>();
  ArrayList<String> idlist;
  Boolean created;
  HashMap<LatLng, String> idhash;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_draw_polygon);
    btndraw = findViewById(R.id.btndraw);
    btndelete = findViewById(R.id.btndelete);
    btnlist=findViewById(R.id.btnlist);
    created=false;
    SupportMapFragment mapFragment = (SupportMapFragment)
            getSupportFragmentManager().findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);
    idlist =new ArrayList<>();
    Intent intent = getIntent();
    idhash = (HashMap<LatLng, String>) intent.getSerializableExtra("hashMap");

    btndraw.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (polygon != null) polygon.remove();
        if (!latLngList.isEmpty()) {
          PolygonOptions polygonOptions = new PolygonOptions()
                  .addAll(latLngList).clickable(true);
          polygon = gMap.addPolygon(polygonOptions);
          created=true;
        }
      }
    });

    btnlist.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (created) {
          for (Map.Entry<LatLng, String> entry : idhash.entrySet()) {
            LatLng key = entry.getKey();
            String value = entry.getValue();
            boolean inside = PolyUtil.containsLocation(key.latitude, key.longitude, latLngList, true);
            if (inside) {
              idlist.add(value);
            }
          }
          IdList(view);
        }
        else{
          Log.e("no polygon","there is no polygon");
        }
      }
    });



    btndelete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (polygon != null) polygon.remove();
        for (Marker marker : markerList) marker.remove();
        latLngList.clear();
        markerList.clear();
        if(!idlist.isEmpty()) idlist.clear();
         created=false;
      }
    });

  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    gMap = googleMap;
    LatLng center=new LatLng(  51.509865, -0.118092);
    gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center,7));
    gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
      @Override
      public void onMapClick(LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions().position(latLng);
        Marker marker = gMap.addMarker(markerOptions);
        latLngList.add(latLng);
        markerList.add(marker);
      }
    });


  }
  public void IdList(View view) {
    Intent intent = new Intent(DrawPolygon.this, IdList.class);
    intent.putExtra("idlist", idlist);
    startActivity(intent);
  }

}