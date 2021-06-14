package com.myapp.dstekup.leboncopro.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;

import com.myapp.dstekup.leboncopro.Controller.Adapter.MyAdapter6;
import com.myapp.dstekup.leboncopro.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Model.Spending;

public class ContentActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter6 mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Spending> data = new ArrayList<>();
    DatabaseReference mDatabase;

    TextView reference, total;
    String key;
    int iReference, sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        reference = findViewById(R.id.ctReference);
        total = findViewById(R.id.total);

        key = getIntent().getExtras().getString("key", "");
        iReference = getIntent().getExtras().getInt("reference", 0);

        SharedPreferences sharedPreferences = getSharedPreferences("Client", Context.MODE_PRIVATE);
        String idCondo = sharedPreferences.getString("idCondo", "");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Bills").child(idCondo).child(key).child("spendings");

        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data = new ArrayList<>();
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    Spending spending = child.getValue(Spending.class);
                    data.add(spending);
                    sum += spending.getPrice();
                }
                total.setText(sum + ".000 TND");
                Collections.reverse(data);
                mAdapter.setFilter(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseError.getDetails();
            }
        });


        reference.setText("Bill Reference : " + iReference);

        mRecyclerView = findViewById(R.id.recycler6);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MyAdapter6(this, data);
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }
}
