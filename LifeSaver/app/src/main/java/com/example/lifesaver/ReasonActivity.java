package com.example.lifesaver;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lifesaver.bo.Reason;
import com.example.lifesaver.bo.ReasonSection;
import com.example.lifesaver.dao.ReasonDAO;
import com.example.lifesaver.dao.ReasonSectionDAO;
import com.example.lifesaver.ui.adapter.ReasonSectionAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class ReasonActivity extends AppCompatActivity {


    RecyclerView recycle;
    List<Reason> reasons;
    List<Reason> reasons2 = new ArrayList<>();

    List<ReasonSection> reasonSections;

    ReasonSectionAdapter adapter;

    BottomNavigationView bottomNav;

    ReasonSectionDAO reasonSectionDAO;

    ReasonDAO reasonDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reason_main);

        reasonSectionDAO = new ReasonSectionDAO(this);
        reasonSections = reasonSectionDAO.getAll();

        bottomNav = findViewById(R.id.bottomNav);

        recycle=findViewById(R.id.reasonSection);
        recycle.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        adapter = new ReasonSectionAdapter(this,reasonSections);
        recycle.setAdapter(adapter);


        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.home) {
                    navigateToActivity(ReasonActivity.class);
                    return true;
                } else if (id == R.id.advice) {
                    navigateToActivity(SafetyPlan.class);
                    return true;
                }else {
                    return false;
                }
            }
        });

    }

    private void navigateToActivity(Class<?> destinationActivity) {
        Intent intent = new Intent(this, destinationActivity);
        this.startActivity(intent);
    }
}