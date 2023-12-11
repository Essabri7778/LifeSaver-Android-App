package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutApp extends AppCompatActivity {

    TextView toolBarText;
    ImageView backButton;

    LinearLayout che, ben, ess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        backButton = findViewById(R.id.backButton);
        toolBarText = findViewById(R.id.toolbartext);

        che = findViewById(R.id.che);
        ben = findViewById(R.id.ben);
        ess = findViewById(R.id.ess);


        toolBarText.setText("About Your Hope Space");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        che.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGithub("https://github.com/AbdelaliChe");
            }
        });

        ben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGithub("https://github.com/nassimBenmamoun");
            }
        });

        ess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGithub("https://github.com/Essabri7778");
            }
        });
    }

    public void goToGithub(String uri){
        Intent i =new Intent();
        i.setAction(i.ACTION_VIEW);
        i.setData(Uri.parse(uri));
        startActivity(i);
    }
}