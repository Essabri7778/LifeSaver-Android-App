package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifesaver.bo.SafetyPlanBo;
import com.example.lifesaver.dao.SafetyPlanDAO;
import com.example.lifesaver.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class EditSafetyPlan extends AppCompatActivity {

    private SafetyPlanDAO safetyPlanDAO;

    EditText et1, et2, et3, et4, et5, et6;
    ArrayList<EditText> arrayList;
    Button btn_save;

    ImageView back;

    TextView toolBarText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_safety_plan);

        safetyPlanDAO = new SafetyPlanDAO(this);

        et1 = findViewById(R.id.et1);et2 = findViewById(R.id.et2);et3 = findViewById(R.id.et3);et4 = findViewById(R.id.et4);et5 = findViewById(R.id.et5);et6 = findViewById(R.id.et6);

        arrayList= new ArrayList<>();
        arrayList.add(et1);arrayList.add(et2);arrayList.add(et3);arrayList.add(et4);arrayList.add(et5);arrayList.add(et6);

        btn_save = findViewById(R.id.btn_save_plan);

        back = findViewById(R.id.backButton);

        toolBarText = findViewById(R.id.toolbartext);
        toolBarText.setText("Edit Safety Plan");

        List<SafetyPlanBo> list = safetyPlanDAO.getAll();
        if(!list.isEmpty()) fillEditText();




        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<SafetyPlanBo> list = safetyPlanDAO.getAll();
                if(list.isEmpty()){
                    addAll();
                }else{
                    updateAll();
                }

                Intent myIntent = new Intent(EditSafetyPlan.this, SafetyPlan.class);
                startActivity(myIntent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(EditSafetyPlan.this , SafetyPlan.class);
                startActivity(myIntent);
                finish();
            }
        });

    }

    public void addAll(){
        for(int i=0 ; i<arrayList.size() ; i++){
            SafetyPlanBo sf = new SafetyPlanBo();
            String response = (arrayList.get(i)).getText().toString();
            if(response.equals("")){
                response = "Edit your plan to complete this section";
            }
            sf.setResponse(response);
            safetyPlanDAO.addOne(sf);
        }
    }


    public void updateAll(){

        for(int i=0 ; i<arrayList.size() ; i++){
            SafetyPlanBo sf = new SafetyPlanBo();
            String response = (arrayList.get(i)).getText().toString();
            sf.setId(i+1);
            if(response.equals("")){
                response = "Edit your plan to complete this section";
            }
            sf.setResponse(response);
            safetyPlanDAO.updateOne(sf);
        }

    }

    public void fillEditText(){
        List<SafetyPlanBo> list = safetyPlanDAO.getAll();
        for(int i=0 ; i<list.size() ; i++){
            String response = list.get(i).getResponse();
            if(response.equals("Edit your plan to complete this section")){
                response = "";
            }
            (arrayList.get(i)).setText(response);
        }
    }
}