package com.example.lifesaver.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifesaver.R;
import com.example.lifesaver.bo.Reason;
import com.example.lifesaver.dao.ReasonDAO;

import java.util.ArrayList;
import java.util.List;

public class ReasonsAdapter extends RecyclerView.Adapter<ReasonsAdapter.ReasonsHolder> {

    Context context;
    List<Reason> reasons;
    List<Reason> reasonsModified;

    boolean isEditMode;

    ReasonDAO reasonDAO;

    public ReasonsAdapter(Context context,List<Reason> reasons) {
        this.context=context;
        this.reasons=reasons;
        reasonsModified=new ArrayList<>();
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

            if(reason.getSectionId()==5){
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if(holder.pip.getVisibility()==View.GONE){
                            holder.pip.setVisibility(View.VISIBLE);
                        }else{
                            holder.pip.setVisibility(View.GONE);
                            return true;
                        }
                        holder.ownReason.setText(reason.getReason());
                        reasonDAO = new ReasonDAO(context);
                        holder.edit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String editReason = holder.ownReason.getText().toString();
                                if(!editReason.equals("")){
                                    reason.setReason(holder.ownReason.getText().toString());
                                    reasonDAO.updateText(reason.getId(),holder.ownReason.getText().toString());
                                    holder.pip.setVisibility(View.GONE);
                                    notifyDataSetChanged();
                                    Toast.makeText(context,"Reason successfully edited \uD83C\uDF89",Toast.LENGTH_LONG).show();
                                }else if(editReason.equals(reason.getReason())){
                                    Toast.makeText(context,"You kept the same Reason ⚠️",Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(context,"Please enter a valid edited Reason ⚠️",Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                        holder.delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                reasonDAO.deleteReason(reason.getId());
                                holder.pip.setVisibility(View.GONE);
                                notifyDataSetChanged();
                                holder.itemView.setVisibility(View.GONE);
                                Toast.makeText(context,"Reason successfully deleted",Toast.LENGTH_LONG).show();
                            }
                        });
                        return true;
                    }
                });
            }
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
        LinearLayout pip;

        Button edit ,delete;
        EditText ownReason;

        public ReasonsHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImg);
            textView = itemView.findViewById(R.id.reasonText);
            id = itemView.findViewById(R.id.idReason);
            pip = itemView.findViewById(R.id.popip);
            edit = itemView.findViewById(R.id.edit_reason_own);
            delete = itemView.findViewById(R.id.delete_reason_own);
            ownReason = itemView.findViewById(R.id.ownReason);
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
