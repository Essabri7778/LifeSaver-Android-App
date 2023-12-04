package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SafetyPlan extends AppCompatActivity {

    TextView tv1, tv2, tv3, tv4, tv5, tv6;

    Button goEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_plan);


        goEdit = findViewById(R.id.btn_go_to_edit);

        goEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SafetyPlan.this, EditSafetyPlan.class);
                startActivity(myIntent);
                finish();
            }
        });
    }
}