package com.myapp.dstekup.leboncopro.Controller;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.dstekup.leboncopro.Controller.Adapter.MyAdapter;
import com.myapp.dstekup.leboncopro.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

import Model.Condo;

public class CondoActivity extends AppCompatActivity implements android.widget.SearchView.OnQueryTextListener {


    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    android.widget.SearchView searchView;
    DatabaseReference mDatabase;
    List<Condo> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condo);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Condos");

        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data = new ArrayList<>();
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    Condo condo = child.getValue(Condo.class);
                    data.add(condo);
                }
                mAdapter.setFilter(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MyAdapter(this, data);
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        newText = newText.toLowerCase();
        List<Condo> newData = new ArrayList<>();
        for (Condo condo : data) {
            String name = condo.getName().toLowerCase();
            if (name.contains(newText)) {
                newData.add(condo);
            }
        }
        mAdapter.setFilter(newData);
        return true;
    }
}
