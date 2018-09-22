package com.example.androidtools.karma.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidtools.karma.R;

import com.example.androidtools.karma.Service.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class YasinFragment1 extends Fragment
{

    MapView mMapView;
    private GoogleMap googleMap;
    GPSTracker gpsTracker;
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_yasin1, container, false);

        //****************************

        gpsTracker = new GPSTracker(getActivity().getApplicationContext());
        setLocationAddress();

        //****************************



        mMapView = rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try
        {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback()
        {
            @Override
            public void onMapReady(GoogleMap mMap)
            {
                googleMap = mMap;


                // Add a marker in Sydney and move the camera
                Intent tmp = getActivity().getIntent();
                double lat = tmp.getDoubleExtra("lat", 39.925);
                double lon = tmp.getDoubleExtra("lon", 32.866);
                LatLng phoneLocation = new LatLng(lat, lon);
                googleMap.addMarker(new MarkerOptions().position(phoneLocation).title("Selam Buradayım.."));

                CameraPosition cameraPosition = new CameraPosition.Builder().target(phoneLocation).zoom(5).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                /*
                gps = new GPSTracker(getContext());

                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    Toast.makeText(getContext(), "Your Location is - \nLat: "
                            + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

                    LatLng myLocation = new LatLng(latitude, longitude);
                    googleMap.addMarker(new MarkerOptions().position(myLocation).title("Konumum.."));

                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();


                }*/
            }
        });

        return rootView;
    }

    private void setLocationAddress() {

        Toast.makeText(getActivity().getApplicationContext(), "LOCATION: "+gpsTracker.getLocation(), Toast.LENGTH_SHORT).show();

        if (gpsTracker.getLocation() != null) {
            if (gpsTracker.getLatitude() != 0 && gpsTracker.getLongitude() != 0) {
                Toast.makeText(getActivity().getApplicationContext(), "Lat: "+gpsTracker.getLatitude()+ "Lon: "+gpsTracker.getLongitude(), Toast.LENGTH_SHORT).show();
            } else {
                buildAlertMessageNoGps();
            }
        } else {
            buildAlertMessageNoGps();
        }
    }

    private void buildAlertMessageNoGps() {
        if (!(getActivity().isFinishing())) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
            builder1.setMessage(getResources().getString(R.string.ok_location));
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    getResources().getString(R.string.ok),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            setLocationAddress();
                        }
                    });

            builder1.setNegativeButton(
                    android.R.string.cancel,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            getActivity().finish();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

}