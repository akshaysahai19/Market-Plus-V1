package com.test.marketpulse.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
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
    private boolean isNew = true;

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
        if (criteriaList.get(i).getVariables().size() > 0) {
            buildString(i, 99, 99);
        }
        myViewHolder.criteria_name.setText(criteriaList.get(i).getUpdatedText());
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
//                        criteriaList.get(i).setText(taskEditText.getText().toString());
                        isNew = false;
                        buildString(i, 99, Long.valueOf(taskEditText.getText().toString()));
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton(context.getString(R.string.cancel), null)
                .create();
        dialog.show();
    }


    private Spannable[] getSpannedText(int pos, int value1, long value2) {
        String[] strings = criteriaList.get(pos).getText().split(" ");
        Spannable[] spannables = new Spannable[strings.length];
        int j = -1;
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].contains("$")) {
                j += 1;
                if (j < criteriaList.get(pos).getVariables().size()) {
                    if (criteriaList.get(pos).getVariables().get(j).getType().equals("value")) {
                        spannables[i] = new SpannableString(colordString(String.valueOf(criteriaList.get(pos).getVariables()
                                .get(j).getValuesList().get(0))));
                        if (!isNew) {
                            spannables[i] = colordString(String.valueOf(criteriaList.get(pos).getVariables()
                                    .get(j).getValuesList().get(value1)));
                        }
                    } else if (criteriaList.get(pos).getVariables().get(j).getType().equals("indicator")) {
                        spannables[i] = colordString(String.valueOf(criteriaList.get(pos).getVariables()
                                .get(j).getDefault_value()));
                        if (!isNew) {
                            spannables[i] = colordString(String.valueOf(value2));
                        }
                    }

                }

            } else {
                spannables[i] = new SpannableString(strings[i]);
            }
        }
        return spannables;
    }

    private void buildString(int pos, int value1, long value2) {
        Spannable[] spannables = getSpannedText(pos, value1, value2);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (spannables != null) {
            for (int i = 0; i < spannables.length; i++) {
                spannableStringBuilder.append(spannables[i]);
                if (i < spannables.length - 1) {
                    spannableStringBuilder.append(" ");
                }
            }
        }

        criteriaList.get(pos).setUpdatedText(spannableStringBuilder);
    }

    private Spannable colordString(String s) {
        Spannable spannable = new SpannableString(s);
        spannable.setSpan(new ForegroundColorSpan(Color.BLUE), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }


}
