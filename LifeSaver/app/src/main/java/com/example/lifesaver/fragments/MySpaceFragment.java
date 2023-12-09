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

public class MySpaceFragment extends Fragment {

    LinearLayout goToReason, goToSafety;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_space, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        goToReason = view.findViewById(R.id.gotToReason);
        goToSafety = view.findViewById(R.id.goToSafety);

        goToReason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReasonActivity.class);
                startActivity(intent);
            }
        });

        goToSafety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SafetyPlan.class);
                startActivity(intent);
            }
        });
    }
}