package com.example.lifesaver;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;

import com.example.lifesaver.bo.ContactBo;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FindHelpCustomAdapter extends BaseAdapter {
    private Context context;
    private List<ContactBo> contactList;
    private LayoutInflater inflater;

    public FindHelpCustomAdapter(Context context, List<ContactBo> contactList) {
        this.context = context;
        this.contactList = contactList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.contact_item,null);
            holder = new ViewHolder();
            holder.contactNameView = (TextView) convertView.findViewById(R.id.contact_name);
            // add the image
            holder.contactImgView = convertView.findViewById(R.id.contact_img);
            holder.hiddenId = (TextView) convertView.findViewById(R.id.hidden_id);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.contactNameView.setText(contactList.get(position).getPrimaryName());
        holder.hiddenId.setText(contactList.get(position).getIdentifier());
        if(!(contactList.get(position).getPhoto().equals(Uri.parse(""))) ){
            holder.contactImgView.setImageURI(contactList.get(position).getPhoto());

        }else{
            int color = ContextCompat.getColor(context, R.color.colorPrimary);
            holder.contactImgView.setImageResource(R.drawable.myself);
            ImageViewCompat.setImageTintList(holder.contactImgView, ColorStateList.valueOf(color));
        }
        return convertView;

    }

    public List<ContactBo> getDataList(){
        return contactList;
    }

    static class ViewHolder {
        TextView contactNameView;
        ImageView contactImgView;
        TextView hiddenId;
    }
}
