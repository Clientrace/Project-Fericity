package com.mindovermatter.fericity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

public class ReportActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //sample post
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        System.out.print("onCreate test");
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