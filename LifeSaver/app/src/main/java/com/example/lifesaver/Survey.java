package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifesaver.bo.SurveyBo;

public class Survey extends AppCompatActivity {

    TextView toolBarText , questionText;

    RadioGroup answer;

    RadioButton checkedButton;

    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        toolBarText = findViewById(R.id.toolbartext);
        toolBarText.setText("Survey");

        questionText = findViewById(R.id.question_text);
        answer = findViewById(R.id.answer);
        btn_next = findViewById(R.id.btn_next);

        SurveyBo surveyBo = new SurveyBo();

        questionText.setText(surveyBo.getCurrentQuestion());


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(answer.getCheckedRadioButtonId() == R.id.yes_radio_button){
                    surveyBo.incrementScore();
                }else if(answer.getCheckedRadioButtonId() == R.id.no_radio_button){
                    surveyBo.decrementScore();
                }
                else{
                    Toast.makeText(Survey.this , "Please answer the question", Toast.LENGTH_LONG).show();
                    return;
                }
                answer.clearCheck();

                if(surveyBo.getCurrent() >= surveyBo.getQuestions().size() - 1){
                    // redirection
                    if(surveyBo.getScore() >=7){
                        // Find help now

                    }else{
                        // MY Space

                    }


                }else{
                    surveyBo.incrementCurrent();
                    questionText.setText(surveyBo.getCurrentQuestion());
                    if(surveyBo.getCurrent() == surveyBo.getQuestions().size() - 1){
                        btn_next.setText("Finish");
                    }
                }


            }
        });
    }
}