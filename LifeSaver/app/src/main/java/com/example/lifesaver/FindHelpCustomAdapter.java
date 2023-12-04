package com.example.lifesaver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FindHelpCustomAdapter extends BaseAdapter {
    Context context;
    String listContacts[];
    LayoutInflater inflater;

    public FindHelpCustomAdapter(Context context, String[] listContacts) {
        this.context = context;
        this.listContacts = listContacts;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listContacts.length;
    }

    @Override
    public Object getItem(int position) {
        return listContacts[position];
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
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.contactNameView.setText(listContacts[position]);
        return convertView;

    }

    static class ViewHolder {
        TextView contactNameView;
        ImageView contactImgView;
    }
}
