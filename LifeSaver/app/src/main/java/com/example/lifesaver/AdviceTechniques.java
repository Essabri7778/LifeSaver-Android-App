package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

public class AdviceTechniques extends AppCompatActivity {

    TextView toolBarText;

    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice_techniques);

        backButton = findViewById(R.id.backButton);
        toolBarText = findViewById(R.id.toolbartext);
        toolBarText.setText("Grounding Techniques");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }
}