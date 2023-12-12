package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.text.TextRunShaper;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DisplayUrgentSuportActivity extends AppCompatActivity {

    ImageView backButton;
    ImageView logoImg;
    LinearLayout phoneLayout;
    LinearLayout smsLayout;
    TextView name;
    TextView description;
    TextView phone;
    TextView sms;
    TextView toolbartext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_urgent_suport);

        name = findViewById(R.id.resource_name);
        description = findViewById(R.id.resource_desc);
        phone = findViewById(R.id.resource_phone);
        sms = findViewById(R.id.resource_sms);
        phoneLayout = findViewById(R.id.phone_layout);
        smsLayout = findViewById(R.id.sms_layout);
        logoImg = findViewById(R.id.logo);

        Intent i = this.getIntent();
        if(i != null){
            name.setText(i.getStringExtra("name"));
            description.setText(i.getStringExtra("desc"));
            phone.setText(i.getStringExtra("phone"));
            sms.setText(i.getStringExtra("sms"));
            Picasso.get().load(i.getStringExtra("logo")).into(logoImg);
        }

        phoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent();
                callIntent.setAction(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+(i.getStringExtra("phone")).toString()));
                startActivity(callIntent);
            }
        });

        smsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri smsUri = Uri.parse("smsto:" + (i.getStringExtra("phone")).toString());
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, smsUri);
                startActivity(smsIntent);
            }
        });



        /** START CONFIGURATION TOOLBAR */
        backButton = findViewById(R.id.backButton);
        toolbartext = findViewById(R.id.toolbartext);
        toolbartext.setText("Urgent Support");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
        /** END CONFIGURATION TOOLBAR */

    }
}