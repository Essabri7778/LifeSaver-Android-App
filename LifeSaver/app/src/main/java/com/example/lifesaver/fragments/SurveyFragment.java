package com.example.lifesaver.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lifesaver.HomeActivity;
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

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                HomeFragment homeFragment = new HomeFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, homeFragment);
                transaction.commit();
                ((HomeActivity) requireActivity()).updateSelectedItem(R.id.home);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }
}