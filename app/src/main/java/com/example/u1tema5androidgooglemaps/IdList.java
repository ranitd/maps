package com.example.u1tema5androidgooglemaps;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polyline;

import java.util.ArrayList;

public class DistanciaDosPuntos  extends AppCompatActivity  {
  GoogleMap mapa; Marker punto1, punto2; TextView txtdistancia;
  Polyline polyline;
  ArrayList<String> idlist;
   ListView lv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_distancia_dos_puntos);
    //SupportMapFragment mapFragment = (SupportMapFragment)
          //  getSupportFragmentManager().findFragmentById(R.id.mapa);
  //  mapFragment.getMapAsync(this);
    idlist = getIntent().getParcelableExtra("idList");
    if(!idlist.isEmpty()){
    ListView listView;

    //String[] from = { "php_key","c_key","android_key","hacking_key" };

    ArrayAdapter arrayAdapter;

    listView = (ListView) findViewById(R.id.list);

    arrayAdapter = new ArrayAdapter(this,R.layout.list_item, R.id.id, idlist);

    listView.setAdapter(arrayAdapter);
    }
    else{
      Log.e("empty list","there are no cars in the polygon");
    }


  //  txtdistancia=findViewById(R.id.txtdistancia);
  }

  //@Override
  /*public void onMapReady(GoogleMap googleMap) {
   /* ListAdapter adapter = new SimpleAdapter(DistanciaDosPuntos.this, idlist,
            R.layout.list_item, new String[]{"id"},
           new int[]{R.id.id});
    lv.setAdapter(adapter);*/
 // }*/


  /*  mapa = googleMap;
    mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-18.010988, -70.248302), 13));
    punto1=mapa.addMarker(new MarkerOptions().position(new LatLng(-18.017518, -70.242380)).draggable(true));
    punto2=mapa.addMarker(new MarkerOptions().position(new LatLng(-18.006907, -70.252508)).draggable(true));
    polyline = mapa.addPolyline(new PolylineOptions()
            .clickable(true)
            .add(
                    punto1.getPosition(),
                    new LatLng(-18.006907, -70.252508)
            ));
    //establece la distancia entre 2 puntos
    txtdistancia.setText(Util.formatDistanceBetween(punto1.getPosition(),punto2.getPosition()));

    mapa.setOnMarkerDragListener(this);
  }*/

 /* @Override
  public void onMarkerDragStart(Marker marker) {
//Cuando se inicia el desplazamiento
    if(polyline!=null) polyline.remove();
    PolylineOptions polylineOptions = new PolylineOptions()
            .add( punto1.getPosition(),
                    punto2.getPosition()).clickable(true);
    polyline=mapa.addPolyline(polylineOptions);
    txtdistancia.setText(Util.formatDistanceBetween(punto1.getPosition(),punto2.getPosition()));
  }
  @Override
  public void onMarkerDrag(Marker marker) {
//Cuando estamos desplazando el icono
    if(polyline!=null) polyline.remove();
    PolylineOptions polylineOptions = new PolylineOptions()
            .add( punto1.getPosition(),
                    punto2.getPosition()).clickable(true);
    polyline=mapa.addPolyline(polylineOptions);
    txtdistancia.setText(Util.formatDistanceBetween(punto1.getPosition(),punto2.getPosition()));
  }
  @Override
  public void onMarkerDragEnd(Marker marker) {
//Cuando se termina el desplazamiento
  }*/
}