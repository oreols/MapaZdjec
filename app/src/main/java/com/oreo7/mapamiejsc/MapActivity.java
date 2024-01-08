package com.oreo7.mapamiejsc;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.config.IConfigurationProvider;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import org.osmdroid.views.overlay.simplefastpoint.LabelledGeoPoint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class MapActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private double latitude;
    private double longitude;
    private GeoPoint kordy;
    static MapView mapView;
    static MyLocationNewOverlay myLocationOverlay;
    private LocationManager locationManager;
    private IMapController mapController;
    private Button dodajButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map);

        Configuration.getInstance().load(this, getSharedPreferences(getPackageName() + "_preferences", MODE_PRIVATE));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GlownaFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_item1);
        }

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
            } else {
                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
            } else {
                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
            }
        }


        mapView = findViewById(R.id.mapview);
        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE); // Ustaw źródło mapy
        mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        mapView.setMultiTouchControls(true);


        myLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(this), mapView);
        myLocationOverlay.enableMyLocation();
        myLocationOverlay.enableFollowLocation();
        mapView.getOverlays().add(myLocationOverlay);



        AparatActivity aparatActivity = new AparatActivity();
        if(aparatActivity.czySkonczone) {
            //myLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(this), mapView);
            //myLocationOverlay.enableMyLocation();
            //myLocationOverlay.getMyLocation()
            //latitude = this.myLocationOverlay.getMyLocationProvider().getLastKnownLocation().getLatitude();
            //longitude = this.myLocationOverlay.getMyLocationProvider().getLastKnownLocation().getLongitude();
            //latitude = myLocationOverlay.getMyLocation().getLatitude();
            //longitude = myLocationOverlay.getMyLocation().getLongitude();
            //kordy = myLocationOverlay.getMyLocation();
            //String kordystring = kordy.toString();
            //Log.d(MapActivity.ACTIVITY_SERVICE, kordystring);
            myLocationOverlay.runOnFirstFix(() -> {
                latitude = myLocationOverlay.getMyLocation().getLatitude();
                longitude = myLocationOverlay.getMyLocation().getLongitude();
                aparatActivity.dodajMarker(mapView, latitude, longitude);
            });

        }

        mapController = mapView.getController();
        mapController.setZoom(15.0);





        ImageButton showLocationButton = findViewById(R.id.mojalokalizacja);
        View inflatedView = getLayoutInflater().inflate(R.layout.glowna, null);
        dodajButton = inflatedView.findViewById(R.id.dodaj);

        showLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myLocationOverlay.enableMyLocation();
                myLocationOverlay.enableFollowLocation();
                mapView.getOverlays().add(myLocationOverlay);
            }
        });
        dodajButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MapActivity.this, AparatActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        int itemId = item.getItemId();
        if (itemId == R.id.nav_item1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GlownaFragment()).commit();
        } else if (itemId == R.id.nav_item2) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ZapisaneFragment()).commit();
        } else if (itemId == R.id.nav_item3) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new KategorieFragment()).commit();
        } else if (itemId == R.id.nav_item4) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GaleriaZdjecFragment()).commit();
        }else if (itemId == R.id.powrotbutton){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new KategorieFragment()).commit();

    }




        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

}