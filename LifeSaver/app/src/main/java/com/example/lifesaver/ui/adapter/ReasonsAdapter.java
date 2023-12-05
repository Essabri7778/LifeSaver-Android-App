package com.example.lifesaver.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifesaver.EditReasonActivity;
import com.example.lifesaver.MainActivity;
import com.example.lifesaver.R;
import com.example.lifesaver.ReasonActivity;
import com.example.lifesaver.bo.Reason;
import com.example.lifesaver.dao.ReasonDAO;

import java.util.ArrayList;
import java.util.List;

public class ReasonsAdapter extends RecyclerView.Adapter<ReasonsAdapter.ReasonsHolder> {

    Context context;
    List<Reason> reasons;
    List<Reason> reasonsModified;
    boolean isEditMode;
    public ReasonsAdapter(Context context,List<Reason> reasons) {
        this.context=context;
        this.reasons=reasons;
        reasonsModified=new ArrayList<>();
    }
    public ReasonsAdapter(Context context) {
        this.context = context;
        this.reasons = new ArrayList<>();
        reasonsModified = new ArrayList<>();
    }

    public List<Reason> getReasons() {
        return reasons;
    }

    public void setEditMode(boolean isEditMode) {
        this.isEditMode = isEditMode;
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

        if(isEditMode){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (reason.isCheked() == 1) {
                        reason.setCheked(0);
                    } else {
                        reason.setCheked(1);
                    }

                    if(reasonsModified.contains(reason)) reasonsModified.remove(reason);
                    reasonsModified.add(reason);

                    Save();
                }
            });
        }
    }

    public void Save(){
        ReasonDAO reasonDAO = new ReasonDAO(context);
        for(Reason reason:reasonsModified){
            reasonDAO.updateCheckedStatus(reason.getId(), reason.isCheked());
        }
        reasonsModified.clear();
        notifyDataSetChanged();
    }

    public void addReason(Reason reason) {
        reasons.add(reason);
        notifyItemInserted(reasons.size() - 1);
    }

    @Override
    public int getItemCount() {
        return reasons!=null?reasons.size():0;
    }

    class ReasonsHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        TextView id;


        public ReasonsHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImg);
            textView = itemView.findViewById(R.id.reasonText);
            id = itemView.findViewById(R.id.idReason);
        }

        void setDetail(Reason reason){
            textView.setText(reason.getReason());
            if(reason.isCheked()==1){
                imageView.setImageResource(R.drawable.checked);
            }else {
                imageView.setImageResource(0);
            }
            id.setText(""+reason.getId());
        }
    }
}
