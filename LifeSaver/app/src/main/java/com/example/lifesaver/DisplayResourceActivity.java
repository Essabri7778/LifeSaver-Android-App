package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayResourceActivity extends AppCompatActivity {

    TextView name;
    TextView description;
    TextView phone;
    TextView sms;
    TextView email;
    TextView website;

    LinearLayout phoneLayout;
    LinearLayout smsLayout;
    LinearLayout emailLayout;
    LinearLayout websiteLayout;

    ImageView backButton;
    TextView toolbartext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_resource);

        name = findViewById(R.id.resource_name);
        description = findViewById(R.id.resource_desc);
        phone = findViewById(R.id.resource_phone);
        sms = findViewById(R.id.resource_sms);
        email = findViewById(R.id.resource_email);
        website = findViewById(R.id.resource_website);
        phoneLayout = findViewById(R.id.phone_layout);
        smsLayout = findViewById(R.id.sms_layout);
        emailLayout = findViewById(R.id.email_layout);
        websiteLayout = findViewById(R.id.website_layout);

        Intent i = this.getIntent();

        if(i!=null){
            name.setText(i.getStringExtra("name"));
            description.setText(i.getStringExtra("desc"));
            phone.setText(i.getStringExtra("phone"));
            sms.setText(i.getStringExtra("sms"));
            email.setText(i.getStringExtra("email"));
            website.setText(i.getStringExtra("website"));
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

        websiteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = i.getStringExtra("website");

                Uri webpage = Uri.parse("https://www."+url);
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);

            }
        });

        emailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddress = i.getStringExtra("email");

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + emailAddress));
                startActivity(Intent.createChooser(emailIntent, "Send Email"));
            }
        });


        backButton = findViewById(R.id.backButton);
        toolbartext = findViewById(R.id.toolbartext);
        toolbartext.setText("Resources");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }
}