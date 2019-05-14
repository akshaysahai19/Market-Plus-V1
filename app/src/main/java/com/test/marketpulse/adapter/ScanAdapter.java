package com.test.marketpulse.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.marketpulse.activity.CriteriaActivity;
import com.test.marketpulse.R;
import com.test.marketpulse.model.Scan;

import java.io.Serializable;
import java.util.List;

public class ScanAdapter extends RecyclerView.Adapter<ScanAdapter.ScanViewHolder> {

    private Context context;
    private List<Scan> scanArrayList;

    public ScanAdapter(Context context, List<Scan> scanArrayList) {
        this.context = context;
        this.scanArrayList = scanArrayList;
    }

    @NonNull
    @Override
    public ScanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout, viewGroup,
                false);
        return new ScanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScanViewHolder scanViewHolder, int i) {
        final Scan scan = scanArrayList.get(i);
        scanViewHolder.recyclerview_inside_name.setText(scan.getName());
        scanViewHolder.recyclerview_inside_tag.setText(scan.getTag());

        scanViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CriteriaActivity.class);
                intent.putExtra("title",scan.getName());
                intent.putExtra("sub_title",scan.getTag());
                intent.putExtra("criteria", (Serializable) scan.getCriteria());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return scanArrayList.size();
    }

    class ScanViewHolder extends RecyclerView.ViewHolder {

        TextView recyclerview_inside_name, recyclerview_inside_tag;

        ScanViewHolder(View view) {
            super(view);
            recyclerview_inside_name = view.findViewById(R.id.recyclerview_inside_name);
            recyclerview_inside_tag = view.findViewById(R.id.recyclerview_inside_tag);
        }
    }
}
