package com.example.lifesaver.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifesaver.EditReasonActivity;
import com.example.lifesaver.R;
import com.example.lifesaver.ReasonActivity;
import com.example.lifesaver.bo.ReasonSection;

import java.util.List;

public class ReasonSectionAdapter extends RecyclerView.Adapter<ReasonSectionAdapter.ReasonSectionHolder> {

    Context context;
    List<ReasonSection> reasons;

    ReasonsAdapter adapter;


    public ReasonSectionAdapter(Context context, List<ReasonSection> reasons) {
        this.context=context;
        this.reasons=reasons;
    }



    @NonNull
    @Override
    public ReasonSectionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.reason_layout,parent,false);
        return new ReasonSectionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReasonSectionHolder holder, int position) {
        ReasonSection reason = reasons.get(position);
        if (context instanceof ReasonActivity) {
            holder.setDetailViewMode(reason,position);
        } else if (context instanceof EditReasonActivity){
            holder.setDetailEditMode(reason,position);
        }
    }

    @Override
    public int getItemCount() {
        return reasons.size();
    }

    public void updateData(List<ReasonSection> all) {
        this.reasons = all;
        notifyDataSetChanged();
    }

    class ReasonSectionHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        RecyclerView recyclerView;
        TextView textView;

        LinearLayout reasonsSection;

        int backgroundColor;

        public ReasonSectionHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.reasonImg);
            textView = itemView.findViewById(R.id.reasonTitle);
            recyclerView = itemView.findViewById(R.id.listreasons);
            reasonsSection= itemView.findViewById(R.id.reasonOnly);
        }


        void setDetailViewMode(ReasonSection reason, int position){
            textView.setText(reason.getTitle());
            imageView.setImageResource(reason.getIcon());

            if(position%2==0){
                backgroundColor = ContextCompat.getColor(context, R.color.colorPrimary);
            }else {
                backgroundColor = ContextCompat.getColor(context, R.color.colorPrimaryLight);
            }

            reasonsSection.setBackgroundColor(backgroundColor);

            recyclerView.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
            adapter = new ReasonsAdapter(context.getApplicationContext(),reason.getReasonsChecked());
            adapter.setEditMode(false);
            recyclerView.setAdapter(adapter);
        }

        void setDetailEditMode(ReasonSection reason, int position){
            textView.setText(reason.getTitle());
            imageView.setImageResource(reason.getIcon());

            if(position%2==0){
                backgroundColor = ContextCompat.getColor(context, R.color.colorPrimary);
            }else {
                backgroundColor = ContextCompat.getColor(context, R.color.colorPrimaryLight);
            }

            reasonsSection.setBackgroundColor(backgroundColor);

            recyclerView.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
            adapter = new ReasonsAdapter(context.getApplicationContext(),reason.getReasons());
            adapter.setEditMode(true);
            recyclerView.setAdapter(adapter);
        }
    }
    public ReasonsAdapter getCurrentReasonsAdapter() {
        return adapter;
    }
}
