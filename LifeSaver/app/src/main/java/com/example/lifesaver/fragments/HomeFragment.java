package com.example.lifesaver.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lifesaver.HomeActivity;
import com.example.lifesaver.R;
import com.example.lifesaver.ReasonActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {
    LinearLayout goToSpace, goToSurvey, goToAdvice, goToHelp;
    BottomNavigationView bottomNav;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        goToSpace = view.findViewById(R.id.gotToSpace);
        goToSurvey = view.findViewById(R.id.goToSurvey);
        goToAdvice = view.findViewById(R.id.goToAdvice);
        goToHelp = view.findViewById(R.id.goToHelp);

        bottomNav = view.findViewById(R.id.bottomNav);


        goToSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySpaceFragment mySpaceFragment = new MySpaceFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, mySpaceFragment);
                transaction.commit();
                ((HomeActivity) requireActivity()).updateSelectedItem(R.id.mySpace);
            }
        });

        goToSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SurveyFragment surveyFragment = new SurveyFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, surveyFragment);
                transaction.commit();
                ((HomeActivity) requireActivity()).updateSelectedItem(R.id.survey);
            }
        });

        goToAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdviceFragment adviceFragment = new AdviceFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, adviceFragment);
                transaction.commit();
                ((HomeActivity) requireActivity()).updateSelectedItem(R.id.advice);
            }
        });

        goToHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ReasonActivity.class);
                startActivity(intent);
            }
        });
    }
}