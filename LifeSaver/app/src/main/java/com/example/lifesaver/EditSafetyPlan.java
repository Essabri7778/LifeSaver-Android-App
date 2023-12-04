package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lifesaver.dao.SafetyPlanDAO;
import com.example.lifesaver.db.DatabaseHelper;

public class EditSafetyPlan extends AppCompatActivity {

    private SafetyPlanDAO safetyPlanDAO;
    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_safety_plan);

        safetyPlanDAO = new SafetyPlanDAO(this);

        btn_save = findViewById(R.id.btn_save_plan);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }
}