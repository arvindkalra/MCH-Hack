package com.example.ritukapatwal.hospitalapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FusedLocationProviderClient mFusedLocationClient;
    protected Location mLastLocation;

    private TextView mLatitudeText;
    private TextView mLongitudeText;
    private Button btnSOS;

    private static String[] locationPermissions=new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLatitudeText=findViewById(R.id.latitude_text);
        mLongitudeText=findViewById(R.id.longitude_text);
        btnSOS=findViewById(R.id.btnSOS);

        btnSOS.setOnClickListener(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }



    private boolean checkForPermissions(String[] locationPermissions){
        if(ActivityCompat.checkSelfPermission(this,locationPermissions[0])!= PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this,locationPermissions[1])!=PackageManager.PERMISSION_GRANTED){
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {

        if(!checkForPermissions(locationPermissions)){
            // if permission not granted then request for permissions
            ActivityCompat.requestPermissions(this,locationPermissions,1);
        }
        if(ActivityCompat.checkSelfPermission(this,locationPermissions[0])== PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this,locationPermissions[1])==PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                mLatitudeText.setText(location.getLatitude()+"");
                                mLongitudeText.setText(location.getLongitude()+"");

                                // Logic to handle location object
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_SHORT).show();;
                            }
                        }
                    });
        }
    }
}
