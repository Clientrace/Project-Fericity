package com.mindovermatter.fericity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.mindovermatter.fericity.Handlers.Data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver battInfo = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Data.batteryPercent = intent.getIntExtra("level",0);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.registerReceiver(this.battInfo, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        ImageView report = (ImageView) findViewById(R.id.report_button);
        getFeed();
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getFeed(){
        final ArrayList<String> messages = new ArrayList<>();
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/507645796113125/feed",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try {
                            JSONObject jobj = new JSONObject(response.getRawResponse());
                            JSONArray data = jobj.getJSONArray("data");
                            for(int i=0;i<data.length();i++){
                               Log.d(">>>>>>>>>>>>>>>",""+ data.getJSONObject(i).getString("messages"));
                            }
                        }catch (Exception e){}
                    }
                }
        ).executeAsync();
    }

    @Override
    public void onBackPressed() {

    }

}
