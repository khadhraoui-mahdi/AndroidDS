package com.myapp.dstekup.leboncopro.Controller;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.myapp.dstekup.leboncopro.R;

public class ContaintActivity extends AppCompatActivity {

    TextView message, sender, date;
    String sMessage, sSender, sDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_containt);

        sMessage = getIntent().getExtras().getString("message");
        sSender = getIntent().getExtras().getString("sender");
        sDate = getIntent().getExtras().getString("date");

        message = findViewById(R.id.cMessage);
        sender = findViewById(R.id.cSender);
        date = findViewById(R.id.cDate);

        message.setText(sMessage);
        sender.setText(sSender);
        date.setText(sDate);
    }
}
