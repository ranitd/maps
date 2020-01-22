package com.example.u1tema5androidgooglemaps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class DistanciaDosPuntos  extends FragmentActivity implements OnMapReadyCallback {
  GoogleMap mapa; Marker punto1, punto2; TextView txtdistancia;
  Polyline polyline;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_distancia_dos_puntos);
    SupportMapFragment mapFragment = (SupportMapFragment)
            getSupportFragmentManager().findFragmentById(R.id.mapa);
    mapFragment.getMapAsync(this);
    txtdistancia=findViewById(R.id.txtdistancia);
  }
  @Override
  public void onMapReady(GoogleMap googleMap) {
    mapa = googleMap;
    mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-18.010988, -70.248302), 13));
    punto1=mapa.addMarker(new MarkerOptions().position(new LatLng(-18.017518, -70.242380)).draggable(true));
    punto2=mapa.addMarker(new MarkerOptions().position(new LatLng(-18.006907, -70.252508)).draggable(true));
    polyline = mapa.addPolyline(new PolylineOptions()
            .clickable(true)
            .add(
                    punto1.getPosition(),
                    new LatLng(-18.006907, -70.252508)
            ));
    txtdistancia.setText(Util.formatDistanceBetween(punto1.getPosition(),punto2.getPosition()));
  }
}