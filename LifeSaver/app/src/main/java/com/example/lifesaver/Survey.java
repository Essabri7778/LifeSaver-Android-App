package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActionBar;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifesaver.bo.SurveyBo;
import com.example.lifesaver.fragments.MySpaceFragment;
import android.content.Intent;


public class Survey extends AppCompatActivity {

    TextView toolBarText , questionText , dialogParagraph;

    RadioGroup answer;

    Button btn_next , btn_dialog;

    View popUpView;

    ImageView backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        toolBarText = findViewById(R.id.toolbartext);
        toolBarText.setText("Survey");

        questionText = findViewById(R.id.question_text);
        answer = findViewById(R.id.answer);
        btn_next = findViewById(R.id.btn_next);
        backButton = findViewById(R.id.backButton);

        popUpView = getLayoutInflater().inflate(R.layout.popup_survey , null);


        dialogParagraph = popUpView.findViewById(R.id.paragraph);
        btn_dialog = popUpView.findViewById(R.id.btn_choose);


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
                    Dialog dialog = new Dialog(Survey.this);
                    dialog.setCancelable(false);
                    dialog.setContentView(popUpView);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    if(surveyBo.getScore() >=7){
                        // Find help now
                        dialogParagraph.setText("Your well-being is important. If you're experiencing thoughts of self-harm or struggling emotionally, it's crucial to seek help immediately.\nReach out to a mental health professional, a friend, or a family member. You are not alone, and support is available.\nPress the button below to connect with help now and take a positive step towards your mental health and safety.");
                        btn_dialog.setText("Find Help NOW");
                        btn_dialog.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Survey.this, FindHelpNowActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(Survey.this , "Redirect to HELP...", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }else{
                        // MY Space
                        dialogParagraph.setText("Your commitment to your well-being is commendable.\nTo further empower yourself and enhance your mental health, consider creating you own space.\nIn your space, you can add a safety plan and reasons to live. Reflect on positive aspects in your life that bring joy and purpose.\nPress the button below to start building your Space. Your well-being matters, and your space can be a valuable resource during challenging times.");
                        btn_dialog.setText("Edit your space");
                        btn_dialog.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Survey.this, HomeActivity.class);
                                intent.putExtra("navigate_to_myspace", true);
                                startActivity(intent);
                                finish();
                                Toast.makeText(Survey.this , "Redirect to SPACE...", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }


                    dialog.show();


                }else{
                    surveyBo.incrementCurrent();
                    questionText.setText(surveyBo.getCurrentQuestion());
                    if(surveyBo.getCurrent() == surveyBo.getQuestions().size() - 1){
                        btn_next.setText("Finish");
                    }
                }


            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }
}