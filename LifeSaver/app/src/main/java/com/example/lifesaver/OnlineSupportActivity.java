package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class OnlineSupportActivity extends AppCompatActivity {

    ImageView backButton;
    TextView toolbartext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_support);

        backButton = findViewById(R.id.backButton);
        toolbartext = findViewById(R.id.toolbartext);
        toolbartext.setText("Online Support");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }
}