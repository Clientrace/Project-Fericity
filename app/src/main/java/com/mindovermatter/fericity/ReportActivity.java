package com.mindovermatter.fericity;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

public class ReportActivity extends AppCompatActivity {

    /* POST CONTENT:
        Location
        Pic
        Info
        Contacts
     */


    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            UserProfile.loc = location.toString();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //sample post
        int MY_PERMISSION_ACCESS_COURSE_LOCATION = 1000;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
                    MY_PERMISSION_ACCESS_COURSE_LOCATION );
        }
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100,
                0, locationListener);

        

    }

    private void post(String msg){
       Bundle params = new Bundle();
        params.putString("message", msg);
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/507645796113125/feed",
                params,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {

                    }
                }
        ).executeAsync();
    }

}