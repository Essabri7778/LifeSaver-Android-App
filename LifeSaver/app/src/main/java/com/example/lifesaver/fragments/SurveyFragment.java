package com.example.lifesaver.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lifesaver.R;
import com.example.lifesaver.ReasonActivity;
import com.example.lifesaver.SafetyPlan;
import com.example.lifesaver.Survey;

public class SurveyFragment extends Fragment {

    LinearLayout goToSurvey;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_survey, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        goToSurvey = view.findViewById(R.id.goToSurvey);

        goToSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Survey.class);
                startActivity(intent);
            }
        });
    }
}