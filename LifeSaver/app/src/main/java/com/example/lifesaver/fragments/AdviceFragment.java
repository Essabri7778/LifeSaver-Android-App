package com.example.lifesaver.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lifesaver.AboutApp;
import com.example.lifesaver.AdviceMyths;
import com.example.lifesaver.AdviceTechniques;
import com.example.lifesaver.HomeActivity;
import com.example.lifesaver.R;
import com.example.lifesaver.ReasonActivity;

public class AdviceFragment extends Fragment {

    LinearLayout goToMyths, goToGround, goToAbout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_advice, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        goToMyths = view.findViewById(R.id.goToMyths);
        goToGround = view.findViewById(R.id.goToGrounding);
        goToAbout = view.findViewById(R.id.goToAbout);

        goToMyths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdviceMyths.class);
                startActivity(intent);
            }
        });

        goToGround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdviceTechniques.class);
                startActivity(intent);
            }
        });

        goToAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutApp.class);
                startActivity(intent);
            }
        });

    }
}