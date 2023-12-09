package com.example.lifesaver;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.lifesaver.dao.ContactDAO;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class MenuHelper {

    Context context;
    ContactDAO contactDAO;


    public MenuHelper(Context context) {
        this.context = context;
        contactDAO = new ContactDAO(context);
    }

    public void setupMenu(){
        //BottomNavigationView bottomNav = ((Activity) context).findViewById(R.id.bottomNav);
        FloatingActionButton buttonEmergency = ((Activity) context).findViewById(R.id.buttonEmergency);

        /*
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return menuNavigation(item);
            }
        });

         */

        buttonEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.main_button_layout);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                LinearLayout confirmMainButtonPhone = dialog.findViewById(R.id.confirmMainButtonPhone);
                EditText mainButtonPhone = dialog.findViewById(R.id.mainButtonPhone);
                mainButtonPhone.setText(contactDAO.getByName("myHopeEmergency").getPhoneNumber());
                confirmMainButtonPhone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,mainButtonPhone.getText().toString(),Toast.LENGTH_LONG).show();
                        contactDAO.updatePhoneNum(contactDAO.getByName("myHopeEmergency").getId(),
                                mainButtonPhone.getText().toString());
                        dialog.dismiss();
                    }
                });

            }
        });

        buttonEmergency.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //TODO: call emergency
                if(ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE},101);
                }
                Intent callIntent =new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel: "+contactDAO.getByName("myHopeEmergency").getPhoneNumber()));
                context.startActivity(callIntent);

                Toast.makeText(context,"\uD83D\uDEA8Emergency help is on the way.\nStay safe, You are not alone\uD83E\uDEF6",Toast.LENGTH_LONG).show();
                return true;
            }
        });


    }

    /*
    public boolean menuNavigation(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.home) {
            navigateToActivity(context, ReasonActivity.class);
            return true;
        } else if (id == R.id.mySpace) {
            navigateToActivity(context, EditReasonActivity.class);
            return true;
        }else{
            return false;
        }
    }

    private void navigateToActivity(Context context,Class<?> destinationActivity) {
        if (destinationActivity == context.getClass()) {
            return;
        }
        Intent intent = new Intent(context, destinationActivity);
        context.startActivity(intent);
    }

     */


}
