package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FindHelpNowActivity extends AppCompatActivity {
    TextView toolBarText;
    RelativeLayout contacts;
    RelativeLayout urgentSupport;
    RelativeLayout onlineSupport;
    RelativeLayout ownResource;

    ImageView backButton;
    TextView toolbartext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_help_now);

        toolBarText = findViewById(R.id.toolbartext);
        toolBarText.setText("Find Help Now");
        contacts = findViewById(R.id.contacts);
        ownResource = findViewById(R.id.own_resources);
        urgentSupport = findViewById(R.id.urgent_support);
        onlineSupport = findViewById(R.id.online_resource);

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FindHelpNowActivity.this,AllContactsActivity.class);
                startActivity(i);
            }
        });
        ownResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FindHelpNowActivity.this, MyOwnResourcesActivity.class);
                startActivity(i);
            }
        });

        urgentSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FindHelpNowActivity.this, UrgentSupportActivity.class);
                startActivity(i);
            }
        });
        onlineSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FindHelpNowActivity.this,OnlineSupportActivity.class);
                startActivity(i);
            }
        });

        backButton = findViewById(R.id.backButton);
        toolbartext = findViewById(R.id.toolbartext);
        toolbartext.setText("Find help now");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

    }
}