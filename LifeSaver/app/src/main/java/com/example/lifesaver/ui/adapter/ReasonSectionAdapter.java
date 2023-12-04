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

import com.example.lifesaver.R;
import com.example.lifesaver.bo.ReasonSection;

import java.util.List;

public class ReasonSectionAdapter extends RecyclerView.Adapter<ReasonSectionAdapter.ReasonSectionHolder> {

    Context context;
    List<ReasonSection> reasons;
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
        holder.setDetail(reason,position);
    }

    @Override
    public int getItemCount() {
        return reasons.size();
    }

    class ReasonSectionHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        RecyclerView recyclerView;
        TextView textView;
        ReasonsAdapter adapter;

        LinearLayout reasonsSection;

        int backgroundColor;

        public ReasonSectionHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.reasonImg);
            textView = itemView.findViewById(R.id.reasonTitle);
            recyclerView = itemView.findViewById(R.id.listreasons);
            reasonsSection= itemView.findViewById(R.id.reasonOnly);
        }

        void setDetail(ReasonSection reason, int position){
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
            recyclerView.setAdapter(adapter);
        }
    }
}
