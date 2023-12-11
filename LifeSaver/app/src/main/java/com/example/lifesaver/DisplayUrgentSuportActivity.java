package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayUrgentSuportActivity extends AppCompatActivity {

    //ImageView backButton;
    //TextView toolbartext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_urgent_suport);

        /*
        backButton = findViewById(R.id.backButton);
        toolbartext = findViewById(R.id.toolbartext);
        toolbartext.setText("Edit Reason Activity");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
         */
    }
}