package com.example.mapit;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mapit.databinding.ActivityMapsBinding;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    List<Location> savedLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        MyApplication myApplication = (MyApplication) getApplicationContext();
        savedLocations = myApplication.getMyLocations();


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

        // Adding a temp  marker in Sydney
        LatLng sydney = new LatLng(-34, 151);
        LatLng lastLocationPlaced = sydney;


        for(Location location: savedLocations
        ){
            Geocoder geocoder = new Geocoder(MapsActivity.this);
            try{
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
               // tv_address.setText(addresses.get(0).getAddressLine(0));
                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(addresses.get(0).getAddressLine(0));
                mMap.addMarker(markerOptions);
                lastLocationPlaced = latLng;

            }
            catch (Exception e){
                Toast.makeText(this, "Could not track location", Toast.LENGTH_SHORT).show();
            }


        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLocationPlaced, 12.0f));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {

                Integer clicks = (Integer) marker.getTag();
                if(clicks == null){
                    clicks = 0;
                }
                clicks++;
                marker.setTag(clicks);
                Toast.makeText(MapsActivity.this, "Marker " + marker.getTitle()+" was clicked "+ marker.getTag() +" times ", Toast.LENGTH_SHORT).show();



                return false;
            }
        });
    }
}