package com.example.mapadogoogle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mapadogoogle.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Mudar a exibição do Mapa, utilizo o método setMapType.
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Add a marker in Sydney and move the camera
        LatLng meuLocal = new LatLng(-3.902397, -38.517523);

        //Adicionando um circulo dentro do mapa, onde no centro terá uma marcador
        //Utilizo o Objeto CircleOptions

        /*
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(meuLocal);
        circleOptions.radius(10); //definido em metros
        //circleOptions.fillColor(Color.BLUE); //Cor pré-definida da ide
        circleOptions.fillColor(Color.argb(128, 255, 153,0 )); // 0 a 255 para o ALPHA, preciso definir as cores RGB
        circleOptions.strokeColor(Color.GREEN); // Posso definir uma cor para a borda ao redor do círculo
        circleOptions.strokeWidth(10); // Atribui um tamanho da borda
        mMap.addCircle(circleOptions); //Ele adiciona o circulo no mapa

         */

        //-3.902275632031424, -38.51759127783987
        //-3.902023566952254, -38.51751841941137
        //-3.9021760155450838, -38.5169990781108
        //-3.902429604691061, -38.51706726995146
        /*Adicionando Polígonos dentro do mapa, usando o Objeto PolygonOptions, com o método add vou adicionando linhas ou seja vou
        desenhando a forma. Esse Objetos fornce os meus métodos do Objeto CircleOptions para definir cor de borda, largura, cor de fundo etc.

         */

        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(new LatLng(-3.902275632031424, -38.51759127783987));
        polygonOptions.add(new LatLng(-3.902023566952254, -38.51751841941137));
        polygonOptions.add(new LatLng(-3.9021760155450838, -38.5169990781108));
        polygonOptions.add(new LatLng(-3.902429604691061, -38.51706726995146));
        polygonOptions.strokeColor(Color.GREEN);
        polygonOptions.strokeWidth(10);
        polygonOptions.fillColor(Color.argb(128, 255, 153, 0));
        mMap.addPolygon(polygonOptions);




        //Adicionando evento de click no mapa, o evento de onClick
        // o método retorna uma lat e long de onde foi clicado dentro do mapa.
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {

                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;

                Toast.makeText(MapsActivity.this,
                        "onClick Lat: " + latitude + " long: " + longitude, Toast.LENGTH_LONG).show();

                //Será adicionado um marcador a cada click na tela

                mMap.addMarker(
                        new MarkerOptions()
                        .position(latLng)
                        .title("local")
                        .snippet("Descrição") //Colocamos uma descrição também
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.storefront))
                );


            }
        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {

                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;

                Toast.makeText(MapsActivity.this,
                        "onLong Lat: " + latitude + " long: " + longitude, Toast.LENGTH_LONG).show();

                //Será adicionado um marcador a cada click Long na tela

                mMap.addMarker(
                        new MarkerOptions()
                                .position(latLng)
                                .title("local")
                                .snippet("Descrição") //Colocamos uma descrição também
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.carro2))
                );

            }
        });

        //-3.902397, -38.517523

        // o método addMarker, adiciona um marcador dentro do mapa.


        /*  Posso alterar a cor do marcador, utilizo o método .icon que recebe um BitmapDescriptorFactory
                        .icon(
                                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)
                            )


         */

        /* Podemos também utilizar uma imagem no lugar do marcador, usando o mesmo método .icon, o método específico
        é fromResource para acessar as imagens da pasta drawable.

            .icon(
            BitmapDescriptorFactory.fromResource(R.drawable.carro)
                )


         */

        mMap.addMarker(
                 new MarkerOptions()
                        .position(meuLocal)
                        .title("Meu Local")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.carro))
                         /*.icon(
                                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)
                        )

                          */
        );

        // o Método newLatLng exibe um zoom padrão, que requer apenas um lat e long.
        // o Método new LatLngZoom, esse método conseguimos definir um zoom ao abrir o aplicativo.
        mMap.moveCamera(// 2.0 até 21.0
                CameraUpdateFactory.newLatLngZoom(meuLocal, 19)
        );


       // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}