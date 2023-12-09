package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AdviceMyths extends AppCompatActivity {

    TextView toolBarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice_myths);

        toolBarText = findViewById(R.id.toolbartext);
        toolBarText.setText("Myths About Suicide");


    }
}