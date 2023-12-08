package com.example.lifesaver.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lifesaver.R;

public class HomeFragment extends Fragment {
    LinearLayout goToSpace;

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

        goToSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySpaceFragment mySpaceFragment = new MySpaceFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, mySpaceFragment);
                transaction.addToBackStack(null);  // Optional: Add to back stack for fragment navigation
                transaction.commit();
            }
        });
    }
}