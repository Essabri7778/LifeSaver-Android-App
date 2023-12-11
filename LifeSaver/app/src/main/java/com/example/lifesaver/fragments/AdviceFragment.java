package com.example.lifesaver.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lifesaver.AboutApp;
import com.example.lifesaver.AdviceMyths;
import com.example.lifesaver.AdviceTechniques;
import com.example.lifesaver.HomeActivity;
import com.example.lifesaver.R;
import com.example.lifesaver.ReasonActivity;

public class AdviceFragment extends Fragment {

    LinearLayout goToMyths, goToGround, goToAbout;

    ImageView myImageView;
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


        myImageView = view.findViewById(R.id.bookImg);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;

        if (screenHeight > 1000) {
            myImageView.setVisibility(View.VISIBLE);
        } else {
            myImageView.setVisibility(View.GONE);
        }


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