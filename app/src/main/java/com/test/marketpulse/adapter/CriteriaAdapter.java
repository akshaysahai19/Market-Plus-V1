package com.test.marketpulse.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.test.marketpulse.R;
import com.test.marketpulse.model.Criteria;

import java.util.List;

public class CriteriaAdapter extends RecyclerView.Adapter<CriteriaAdapter.MyViewHolder> {

    private Context context;
    private List<Criteria> criteriaList;

    public CriteriaAdapter(Context context, List<Criteria> criteriaList) {
        this.context = context;
        this.criteriaList = criteriaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.criteria_recyclerview_layout, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.criteria_name.setText(criteriaList.get(i).getText());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEdit(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return criteriaList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView criteria_name;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            criteria_name = itemView.findViewById(R.id.criteria_name);
        }
    }

    private void dialogEdit(final int i) {
        final EditText taskEditText = new EditText(context);
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.edit_criteria))
                .setView(taskEditText)
                .setPositiveButton(context.getString(R.string.update), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        criteriaList.get(i).setText(taskEditText.getText().toString());
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton(context.getString(R.string.cancel), null)
                .create();
        dialog.show();
    }
}
