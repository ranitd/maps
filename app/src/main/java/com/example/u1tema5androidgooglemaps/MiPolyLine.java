package com.example.u1tema5androidgooglemaps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MiPolyLine extends AppCompatActivity implements OnMapReadyCallback {
  GoogleMap gMap;
  SeekBar seekwidth, seekazul, seekverde, seekrojo;
  Button btndibujar, btnlimpiar;
  Polyline polyline = null;
  List<LatLng> latLngList = new ArrayList<>();
  List<Marker> markerList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mi_poly_line);
    seekwidth = findViewById(R.id.seek_width);
    seekazul = findViewById(R.id.seek_azul);
    seekverde = findViewById(R.id.seek_verde);
    seekrojo = findViewById(R.id.seek_rojo);
    btndibujar = findViewById(R.id.btndibujar);
    btnlimpiar = findViewById(R.id.btnlimpiar);
    SupportMapFragment mapFragment = (SupportMapFragment)
            getSupportFragmentManager().findFragmentById(R.id.mapa);
    mapFragment.getMapAsync(this);

    btndibujar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (polyline != null) polyline.remove();
        PolylineOptions polylineOptions = new PolylineOptions()
                .addAll(latLngList).clickable(false); //clickable(true) - no se puede marcar la linea
        polyline = gMap.addPolyline(polylineOptions);
      }
    });

    btnlimpiar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (polyline != null) polyline.remove();
        for (Marker marker : markerList) marker.remove();
        latLngList.clear();
        markerList.clear();
        seekwidth.setProgress(3);
        seekazul.setProgress(0);
        seekrojo.setProgress(0);
        seekverde.setProgress(0);
      }
    });
  }

  //INICIALIZA CON EL ONCREATE - DEFINE EL ELEMENTO DE PULSACION
  @Override
  public void onMapReady(GoogleMap googleMap) {
    gMap = googleMap;
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
}
