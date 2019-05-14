package com.test.marketpulse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.test.marketpulse.R;
import com.test.marketpulse.adapter.CriteriaAdapter;
import com.test.marketpulse.model.Criteria;

import java.util.ArrayList;
import java.util.List;

public class CriteriaActivity extends AppCompatActivity {

    RecyclerView criteria_recyclerview;
    TextView criteria_activity_title, criteria_activity_subTitle;
    List<Criteria> criteriaList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criteria_activity);

        criteriaList = new ArrayList<>();
        criteria_activity_title = findViewById(R.id.criteria_activity_title);
        criteria_activity_subTitle = findViewById(R.id.criteria_activity_subTitle);
        criteria_recyclerview = findViewById(R.id.criteria_recyclerview);

        setupUi();
    }

    private void setupUi() {
        criteria_activity_title.setText(getIntent().getStringExtra("title"));
        criteria_activity_subTitle.setText(getIntent().getStringExtra("sub_title"));

        criteriaList = new ArrayList<>();
        criteriaList = (List<Criteria>) getIntent().getSerializableExtra("criteria");
        criteria_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        CriteriaAdapter criteriaAdapter = new CriteriaAdapter(this,criteriaList);
        criteria_recyclerview.setAdapter(criteriaAdapter);
    }
}
