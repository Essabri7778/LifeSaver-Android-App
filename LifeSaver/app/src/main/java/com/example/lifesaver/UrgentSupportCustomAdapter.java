package com.example.lifesaver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lifesaver.bo.ResourceBo;
import com.example.lifesaver.bo.UrgentSupportBo;
import com.example.lifesaver.dao.ResourceDAO;
import com.example.lifesaver.db.FirebaseDatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UrgentSupportCustomAdapter extends BaseAdapter {
    private FirebaseDatabaseHelper firebaseDatabaseHelper;
    private ResourceDAO resourceDAO;
    private Context context;
    private List<UrgentSupportBo> supportList;
    private LayoutInflater inflater;
    private List<String> keys;

    public UrgentSupportCustomAdapter(Context context, List<UrgentSupportBo> supportList,List<String> keys) {
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
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.urgent_support_item,null);
            holder = new UrgentSupportCustomAdapter.ViewHolder();
            holder.supportNameView = (TextView) convertView.findViewById(R.id.support_name);
            holder.supportDescView = (TextView) convertView.findViewById(R.id.support_desc);
            holder.supportPhoneView = (TextView) convertView.findViewById(R.id.support_phone);
            holder.supportSmsView = (TextView) convertView.findViewById(R.id.support_sms);
            holder.supportLogoView = (ImageView) convertView.findViewById(R.id.support_logo);
            holder.phoneLayout = (LinearLayout) convertView.findViewById(R.id.phone_layout);
            holder.smsLayout = (LinearLayout) convertView.findViewById(R.id.sms_layout);
            holder.bookBtn = (FloatingActionButton) convertView.findViewById(R.id.booked_button);
            holder.moreInfoBtn = (Button) convertView.findViewById(R.id.more_info);
            holder.key = (TextView) convertView.findViewById(R.id.key);
            convertView.setTag(holder);
        }else{
            holder = (UrgentSupportCustomAdapter.ViewHolder) convertView.getTag();
        }
        holder.supportNameView.setText(supportList.get(position).getName());
        holder.supportDescView.setText(supportList.get(position).getDescription());
        holder.supportSmsView.setText(supportList.get(position).getSms());
        holder.supportPhoneView.setText(supportList.get(position).getPhone());

        boolean condition = (supportList.get(position).getBooked() == 0);
        holder.bookBtn.setImageResource(condition ? R.drawable.outline_bookmark_add_24_blue:R.drawable.baseline_bookmark_added_24_white);
        holder.key.setText(keys.get(position));


        /** LOADING LOGO */
        Picasso.get().load(supportList.get(position).getLogo()).into(holder.supportLogoView);



        /** START HANDLE PHONE BUTTON */
        holder.phoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatePhoneCall(supportList.get(position).getPhone());
            }
        });
        /** END HANDLE PHONE BUTTON */

        /** START HANDLE SMS BUTTON */
        holder.smsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiateSmsMessage(supportList.get(position).getSms());
            }
        });
        /** END HANDLE SMS BUTTON */


        /** START HANDLE BOOKED BUTTON */
        holder.bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (supportList.get(position).getBooked() == 0) {
                    ResourceBo resource = new ResourceBo();
                    resource.setName(supportList.get(position).getName());
                    resource.setDescription(supportList.get(position).getDescription());
                    resource.setType(supportList.get(position).getType());
                    resource.setPhone(supportList.get(position).getPhone());
                    resource.setSms(supportList.get(position).getSms());
                    resource.setLogo(supportList.get(position).getLogo());
                    resourceDAO.addResource(resource);
                    firebaseDatabaseHelper.updateUrgentBookedStatus(keys.get(position),1);
                } else {
                    resourceDAO.deleteByName(supportList.get(position).getName());
                    //holder.bookBtn.setImageResource(R.drawable.outline_bookmark_add_24_blue);
                    firebaseDatabaseHelper.updateUrgentBookedStatus(keys.get(position),0);
                }
                notifyDataSetChanged();
               }

                //handleBookedResources();

        });
        /** ENS HANDLE BOOKED BUTTON */

        /** START HANDLE MORE INFO BUTTON */
        holder.moreInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateUrgentSupportForm(supportList.get(position));
            }
        });

        /** ENd HANDLE MORE INFO BUTTON */


        return convertView;
    }

    private void handleBookedResources() {

    }

    private void initiatePhoneCall(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));

        // Access the context from the adapter
        if (context != null && context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.startActivity(callIntent);
        }}

    private void initiateSmsMessage(String sms){
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setData(Uri.parse("smsto:" + sms));

        // Access the context from the adapter
        if (context != null && context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.startActivity(smsIntent);
        }}

    private void populateUrgentSupportForm(UrgentSupportBo urgentSupportBo){
        Intent i = new Intent(context,DisplayUrgentSuportActivity.class);
        i.putExtra("name",urgentSupportBo.getName());
        i.putExtra("logo",urgentSupportBo.getLogo());
        i.putExtra("desc",urgentSupportBo.getDescription());
        i.putExtra("phone",urgentSupportBo.getPhone());
        i.putExtra("sms",urgentSupportBo.getSms());
        i.putExtra("booked",urgentSupportBo.getBooked());
        i.putExtra("type",urgentSupportBo.getType());

        if (context != null && context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.startActivity(i);
        }
    }




    public List<UrgentSupportBo> getDataList(){
        return supportList;
    }


    static class ViewHolder {
        TextView supportNameView;
        TextView supportDescView;
        TextView supportPhoneView;
        TextView supportSmsView;
        ImageView supportLogoView;
        LinearLayout smsLayout;
        LinearLayout phoneLayout;
        FloatingActionButton bookBtn;
        TextView key;
        Button moreInfoBtn;
    }
}
