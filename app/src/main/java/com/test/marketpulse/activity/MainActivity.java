package com.test.marketpulse.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.test.marketpulse.R;
import com.test.marketpulse.adapter.ScanAdapter;
import com.test.marketpulse.model.Scan;
import com.test.marketpulse.view_model.ScanViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView main_recyclerview;
    ScanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_recyclerview = findViewById(R.id.main_recyclerview);
        main_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        ScanViewModel model = ViewModelProviders.of(this).get(ScanViewModel.class);

        model.getScans().observe(this, new Observer<List<Scan>>() {
            @Override
            public void onChanged(@Nullable List<Scan> scanList) {
                adapter = new ScanAdapter(MainActivity.this, scanList);
                main_recyclerview.setAdapter(adapter);
            }
        });
    }
}
