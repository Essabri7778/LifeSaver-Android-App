package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DisplayOnlineSupportActivity extends AppCompatActivity {
    ImageView backButton;
    ImageView logoImg;
    LinearLayout emailLayout;
    LinearLayout websiteLayout;
    TextView name;
    TextView description;
    TextView email;
    TextView website;
    TextView toolbartext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_online_support);

        name = findViewById(R.id.resource_name);
        description = findViewById(R.id.resource_desc);
        email = findViewById(R.id.resource_email);
        website = findViewById(R.id.resource_website);
        emailLayout = findViewById(R.id.email_layout);
        websiteLayout = findViewById(R.id.website_layout);
        logoImg = findViewById(R.id.logo);

        Intent i = this.getIntent();
        if(i != null){
            name.setText(i.getStringExtra("name"));
            description.setText(i.getStringExtra("desc"));
            email.setText(i.getStringExtra("email"));
            website.setText(i.getStringExtra("website"));
            Picasso.get().load(i.getStringExtra("logo")).into(logoImg);
        }

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




        /** START CONFIGURATION TOOLBAR */
        backButton = findViewById(R.id.backButton);
        toolbartext = findViewById(R.id.toolbartext);
        toolbartext.setText("Online Support");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
        /** END CONFIGURATION TOOLBAR */

    }
}