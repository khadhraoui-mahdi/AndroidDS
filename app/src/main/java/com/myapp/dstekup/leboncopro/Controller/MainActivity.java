package com.myapp.dstekup.leboncopro.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.myapp.dstekup.leboncopro.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                Intent HomeActivity = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(HomeActivity);
            }
        }, 4000);
    }
}
