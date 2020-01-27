package com.example.u1tema5androidgooglemaps;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.List;


public class MiPolygon  extends AppCompatActivity implements OnMapReadyCallback, SeekBar.OnSeekBarChangeListener {
  GoogleMap gMap;
  CheckBox checkpintar;
  SeekBar seekazul, seekverde, seekrojo;
  Button btndibujar, btnlimpiar,btnlist;
  Polygon polygon = null;
  List<LatLng> latLngList = new ArrayList<>();
  List<Marker> markerList = new ArrayList<>();
  int rojo = 0, verde = 0, azul = 0;
  ArrayList<LatLng> locationList;
  ArrayList<LatLng> inPolygon;
  private MarkerOptions options = new MarkerOptions();
  ArrayList<latLng> latLngIdlist;
  ArrayList<String> idlist;
  Boolean created;
  private ListView lv;
  Bundle bundle;
  //private Object view;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mi_polygon);
    checkpintar = findViewById(R.id.checkpolygon);
    seekazul = findViewById(R.id.seek_azul);
    seekverde = findViewById(R.id.seek_verde);
    seekrojo = findViewById(R.id.seek_rojo);
    btndibujar = findViewById(R.id.btndibujar);
    btnlimpiar = findViewById(R.id.btnlimpiar);
    btnlist=findViewById(R.id.btnlist);
    //lv = (ListView) findViewById(R.id.list);
    created=false;
    SupportMapFragment mapFragment = (SupportMapFragment)
            getSupportFragmentManager().findFragmentById(R.id.mapa);
    mapFragment.getMapAsync(this);

    latLngIdlist = getIntent().getParcelableExtra("latLngIdlist");

    checkpintar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (!latLngList.isEmpty()) {
          if (b) {
            if (polygon == null) return;
            polygon.setFillColor(Color.rgb(rojo, verde, azul));
          } else {
            polygon.setFillColor(Color.TRANSPARENT);
          }
        }
      }
    });

    btndibujar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (polygon != null) polygon.remove();
        if (!latLngList.isEmpty()) {
          PolygonOptions polygonOptions = new PolygonOptions()
                  .addAll(latLngList).clickable(true);
          polygon = gMap.addPolygon(polygonOptions);
          polygon.setStrokeColor(Color.rgb(rojo, verde, azul));
          if (checkpintar.isChecked())
            polygon.setFillColor(Color.rgb(rojo, verde, azul));
          created=true;
        }
      }
    });

    btnlist.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (created) {
          for (latLng point : latLngIdlist) {
            boolean inside = PolyUtil.containsLocation(point.getLatLng().latitude, point.getLatLng().longitude, latLngList, true);
            if (inside) {
              idlist.add(point.getLatLngId());
            }
          }
          DistanciaDosPuntos(view);
        }
      }
    });



    btnlimpiar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (polygon != null) polygon.remove();
        for (Marker marker : markerList) marker.remove();
        latLngList.clear();
        markerList.clear();
        idlist.clear();
        created=false;
        seekazul.setProgress(0);
        seekrojo.setProgress(0);
        seekverde.setProgress(0);
      }
    });
    seekazul.setOnSeekBarChangeListener(this);
    seekrojo.setOnSeekBarChangeListener(this);
    seekverde.setOnSeekBarChangeListener(this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    gMap = googleMap;
    LatLng center=new LatLng(  51.509865, -0.118092);
    gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center,4));
    gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
      @Override
      public void onMapClick(LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions().position(latLng);
        Marker marker = gMap.addMarker(markerOptions);
        latLngList.add(latLng);
        markerList.add(marker);
      }
    });

    //SI MANTIENES SOSTENIDO CREA UN CIRCULO
    gMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
      @Override
      public void onMapLongClick(LatLng latLng) {
        CircleOptions circleOptions = new CircleOptions()
                .center(latLng)
                .radius(300000);//En metros
        Circle circle=gMap.addCircle(circleOptions);
        circle.setStrokeWidth(8);
      }
    });


  }
  public void DistanciaDosPuntos(View view) {
    Intent intent = new Intent(MiPolygon.this, DistanciaDosPuntos.class);
    intent.putExtras(bundle);
    startActivity(intent);
  }
  @Override
  public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
    switch (seekBar.getId()) {
      case R.id.seek_rojo:
        rojo = i;
        break;
      case R.id.seek_verde:
        verde = i;
        break;
      case R.id.seek_azul:
        azul = i;
        break;
    }
    if (polygon != null) {
      polygon.setStrokeColor(Color.rgb(rojo, verde, azul));
      if (checkpintar.isChecked())
        polygon.setFillColor(Color.rgb(rojo, verde, azul));
    }
  }

  @Override
  public void onStartTrackingTouch(SeekBar seekBar) {
  }

  @Override
  public void onStopTrackingTouch(SeekBar seekBar) {
  }
}