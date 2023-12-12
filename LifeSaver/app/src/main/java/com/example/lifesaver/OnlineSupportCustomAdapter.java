package com.example.lifesaver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lifesaver.bo.OnlineSupportBo;
import com.example.lifesaver.bo.ResourceBo;
import com.example.lifesaver.dao.ResourceDAO;
import com.example.lifesaver.db.FirebaseDatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OnlineSupportCustomAdapter extends BaseAdapter {
    private FirebaseDatabaseHelper firebaseDatabaseHelper;
    private ResourceDAO resourceDAO;
    private Context context;
    private List<OnlineSupportBo> supportList;
    private LayoutInflater inflater;
    private List<String> keys;

    public OnlineSupportCustomAdapter(Context context, List<OnlineSupportBo> supportList,List<String> keys) {
        this.context = context;
        this.supportList = supportList;
        this.inflater = LayoutInflater.from(context);
        resourceDAO = new ResourceDAO(context);
        this.keys = keys;
        firebaseDatabaseHelper = new FirebaseDatabaseHelper();
    }

    @Override
    public int getCount() {
        return supportList.size();
    }

    @Override
    public Object getItem(int position) {
        return supportList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OnlineSupportCustomAdapter.ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.online_support_item,null);
            holder = new OnlineSupportCustomAdapter.ViewHolder();
            holder.supportNameView = (TextView) convertView.findViewById(R.id.support_name);
            holder.supportDescView = (TextView) convertView.findViewById(R.id.support_desc);
            holder.supportWebsiteView = (TextView) convertView.findViewById(R.id.support_website);
            holder.supportEmailView = (TextView) convertView.findViewById(R.id.support_email);
            holder.supportLogoView = (ImageView) convertView.findViewById(R.id.support_logo);
            holder.websiteLayout = (LinearLayout) convertView.findViewById(R.id.website_layout);
            holder.emailLayout = (LinearLayout) convertView.findViewById(R.id.email_layout);
            holder.bookBtn = (FloatingActionButton) convertView.findViewById(R.id.booked_button);
            holder.key = (TextView) convertView.findViewById(R.id.key);
            convertView.setTag(holder);
        }else{
            holder = (OnlineSupportCustomAdapter.ViewHolder) convertView.getTag();
        }
        holder.supportNameView.setText(supportList.get(position).getName());
        holder.supportDescView.setText(supportList.get(position).getDescription());
        holder.supportEmailView.setText(supportList.get(position).getEmail());
        holder.supportWebsiteView.setText(supportList.get(position).getWebsite());

        boolean condition = (supportList.get(position).getBooked() == 0);
        holder.bookBtn.setImageResource(condition ? R.drawable.outline_bookmark_add_24_blue:R.drawable.baseline_bookmark_added_24_white);
        holder.key.setText(keys.get(position));

        Picasso.get().load(supportList.get(position).getLogo()).into(holder.supportLogoView);
        holder.emailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiateEmail(supportList.get(position).getEmail());
            }
        });
        holder.websiteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisiteWebSite(supportList.get(position).getWebsite());
            }
        });

        holder.bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (supportList.get(position).getBooked() == 0) {
                    ResourceBo resource = new ResourceBo();
                    resource.setName(supportList.get(position).getName());
                    resource.setDescription(supportList.get(position).getDescription());
                    resource.setType(supportList.get(position).getType());
                    resource.setWebsite(supportList.get(position).getWebsite());
                    resource.setEmail(supportList.get(position).getEmail());
                    resource.setLogo(supportList.get(position).getLogo());
                    resourceDAO.addResource(resource);
                    //holder.bookBtn.setImageResource(R.drawable.baseline_bookmark_added_24_white);
                    firebaseDatabaseHelper.updateOnlineBookedStatus(keys.get(position),1);
                } else {
                    resourceDAO.deleteByName(supportList.get(position).getName());
                    //holder.bookBtn.setImageResource(R.drawable.outline_bookmark_add_24_blue);
                    firebaseDatabaseHelper.updateOnlineBookedStatus(keys.get(position),0);
                }
                notifyDataSetChanged();
            }

            //handleBookedResources();

        });
        return convertView;
    }

    private void handleBookedResources() {

    }

    private void initiateEmail(String emailAddress) {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + emailAddress));

        // Access the context from the adapter
        if (context != null && context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.startActivity(Intent.createChooser(emailIntent, "Send Email"));
        }}


    private void VisiteWebSite(String url){
        Uri webpage = Uri.parse("https://www."+url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

        // Access the context from the adapter
        if (context != null && context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.startActivity(webIntent);
        }
    }



    public List<OnlineSupportBo> getDataList(){
        return supportList;
    }


    static class ViewHolder {
        TextView supportNameView;
        TextView supportDescView;
        TextView supportEmailView;
        TextView supportWebsiteView;
        ImageView supportLogoView;
        LinearLayout emailLayout;
        LinearLayout websiteLayout;
        FloatingActionButton bookBtn;
        TextView key;
    }
}
