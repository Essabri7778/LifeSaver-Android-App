package com.example.lifesaver.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifesaver.R;
import com.example.lifesaver.bo.Reason;

import java.util.List;

public class ReasonsAdapter extends RecyclerView.Adapter<ReasonsAdapter.ReasonsHolder> {

    Context context;
    List<Reason> reasons;
    public ReasonsAdapter(Context context,List<Reason> reasons) {
        this.context=context;
        this.reasons=reasons;
    }

    @NonNull
    @Override
    public ReasonsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_reason,parent,false);
        return new ReasonsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReasonsHolder holder, int position) {
        Reason reason = reasons.get(position);
        holder.setDetail(reason);
    }

    @Override
    public int getItemCount() {
        return reasons!=null?reasons.size():0;
    }

    class ReasonsHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;


        public ReasonsHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImg);
            textView = itemView.findViewById(R.id.reasonText);
        }

        void setDetail(Reason reason){
            textView.setText(reason.getReason());
            imageView.setImageResource(reason.getIcon());
        }
    }
}
