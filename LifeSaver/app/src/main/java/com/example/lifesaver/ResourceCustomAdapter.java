package com.example.lifesaver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.lifesaver.bo.ResourceBo;

import java.util.List;


public class ResourceCustomAdapter extends BaseAdapter {
    private Context context;
    private List<ResourceBo> resourceList;
    private LayoutInflater inflater;

    public ResourceCustomAdapter(Context context, List<ResourceBo> resourceList) {
        this.context = context;
        this.resourceList = resourceList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return resourceList.size();
    }

    @Override
    public Object getItem(int position) {
        return resourceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.resource_item,null);
            holder = new ResourceCustomAdapter.ViewHolder();
            holder.resourceNameView = (TextView) convertView.findViewById(R.id.resource_name);
            holder.resourceDescView = (TextView) convertView.findViewById(R.id.resource_desc);
            holder.hiddenId = (TextView) convertView.findViewById(R.id.hidden_id);
            convertView.setTag(holder);
        }else{
            holder = (ResourceCustomAdapter.ViewHolder) convertView.getTag();
        }
        holder.resourceNameView.setText(resourceList.get(position).getName());
        holder.resourceDescView.setText(resourceList.get(position).getDescription());
        //holder.hiddenId.setText(resourceList.get(position).getId());
        notifyDataSetChanged();
        return convertView;

    }

    public List<ResourceBo> getDataList(){
        return resourceList;
    }

    static class ViewHolder {
        TextView resourceNameView;
        TextView resourceDescView;
        TextView hiddenId;
    }
}
