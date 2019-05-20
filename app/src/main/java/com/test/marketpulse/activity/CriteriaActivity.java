package com.test.marketpulse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test.marketpulse.R;
import com.test.marketpulse.adapter.CriteriaAdapter;
import com.test.marketpulse.model.Criteria;
import com.test.marketpulse.model.Variable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        try {
            setupUi();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setupUi() throws JSONException {
        criteria_activity_title.setText(getIntent().getStringExtra("title"));
        criteria_activity_subTitle.setText(getIntent().getStringExtra("sub_title"));

        updateString();

        criteria_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        CriteriaAdapter criteriaAdapter = new CriteriaAdapter(this, criteriaList);
        criteria_recyclerview.setAdapter(criteriaAdapter);
    }

    private ArrayList<String> getDollars(String string) {
        ArrayList<String> dollars = new ArrayList<>();
        String[] strings = string.split(" ");
        for (String s : strings) {
            if (s.contains("$")) {
                dollars.add(s);
            }
        }
        return dollars;
    }

    private void updateString() throws JSONException {
        criteriaList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(getIntent().getStringExtra("criteria_json"));
        for (int i = 0; i < jsonArray.length(); i++) {
            List<Variable> variableList = new ArrayList<>();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String type = jsonObject.getString("type");
            String text = jsonObject.getString("text");
            if (type.equals("variable")) {
                JSONObject jsonObject_variable = jsonObject.getJSONObject("variable");
                for (int j = 0; j < getDollars(text).size(); j++) {
                    System.out.println("okayokay" + getDollars(text).get(j));
                    JSONObject jsonObject_dollar = jsonObject_variable.getJSONObject(getDollars(text).get(j));
                    if (isTypeValue(jsonObject_dollar)) {
                        Gson gson = new Gson();
                        System.out.println("okayokay" + gson.toJson(jsonObject_dollar));
                        variableList.add(getVariable(jsonObject_dollar));
                    }
                }
            }
            Criteria criteria = new Criteria(type, text, new SpannableString(text), variableList);
            criteriaList.add(criteria);
        }

    }

    private Variable getVariable(JSONObject jsonObject) throws JSONException {
        List<Double> valuesList = new ArrayList<>();
        String type = jsonObject.getString("type");
        String study_type = "";
        String parameter_name = "";
        long min_value = 0;
        long max_value = 0;
        long default_value = 0;
        if (type.equals("value")) {
            JSONArray jsonArray = jsonObject.getJSONArray("values");
            for (int i = 0; i < jsonArray.length(); i++) {
                valuesList.add(jsonArray.getDouble(i));
            }
        } else {
            study_type = jsonObject.getString("study_type");
            parameter_name = jsonObject.getString("parameter_name");
            min_value = jsonObject.getLong("min_value");
            max_value = jsonObject.getLong("max_value");
            default_value = jsonObject.getLong("default_value");
        }
        return new Variable(type, valuesList, study_type, parameter_name, min_value, max_value, default_value);
    }

    private boolean isTypeValue(JSONObject jsonObject) throws JSONException {
        return jsonObject.getString("type").equals("value");
    }
}
