package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lifesaver.bo.SafetyPlanBo;
import com.example.lifesaver.dao.SafetyPlanDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SafetyPlan extends AppCompatActivity {

    private SafetyPlanDAO safetyPlanDAO;

    TextView tv1, tv2, tv3, tv4, tv5, tv6 , toolBarText;

    ArrayList<TextView> arrayList;

    FloatingActionButton goEdit;

    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_plan);

        safetyPlanDAO = new SafetyPlanDAO(this);

        tv1 = findViewById(R.id.tv1);tv2 = findViewById(R.id.tv2);tv3 = findViewById(R.id.tv3);tv4 = findViewById(R.id.tv4);tv5 = findViewById(R.id.tv5);tv6 = findViewById(R.id.tv6);

        back = findViewById(R.id.backButton);

        toolBarText = findViewById(R.id.toolbartext);
        toolBarText.setText("Safety Plan");

        arrayList= new ArrayList<>();
        arrayList.add(tv1);arrayList.add(tv2);arrayList.add(tv3);arrayList.add(tv4);arrayList.add(tv5);arrayList.add(tv6);


        List<SafetyPlanBo> list = safetyPlanDAO.getAll();

        if(list.isEmpty()){
            Intent myIntent = new Intent(SafetyPlan.this, EditSafetyPlan.class);
            startActivity(myIntent);
            finish();
        }else{
            fillViews();
        }




        goEdit = findViewById(R.id.btn_go_to_edit);

        goEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SafetyPlan.this, EditSafetyPlan.class);
                startActivity(myIntent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }

    public void fillViews(){
        List<SafetyPlanBo> list = safetyPlanDAO.getAll();
        for(int i=0 ; i<list.size() ; i++){
            String response = list.get(i).getResponse();
            (arrayList.get(i)).setText(response);
        }
    }
}