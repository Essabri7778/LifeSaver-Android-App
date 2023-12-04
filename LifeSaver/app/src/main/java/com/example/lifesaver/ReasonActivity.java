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
import com.example.lifesaver.ui.adapter.ReasonSectionAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class ReasonActivity extends AppCompatActivity {


    RecyclerView recycle;
    List<Reason> reasons = new ArrayList<>();
    List<Reason> reasons2 = new ArrayList<>();

    List<ReasonSection> reasonSections = new ArrayList<>();

    ReasonSectionAdapter adapter;

    BottomNavigationView bottomNav;

    ReasonDAO reasonDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reason_main);

        reasonDAO = new ReasonDAO(this);

        bottomNav = findViewById(R.id.bottomNav);

        recycle=findViewById(R.id.reasonSection);
        recycle.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        Reason reason1 = new Reason("I care enough about myself to live");
        Reason reason2 = new Reason("I have the courage to face life");
        Reason reason3 = new Reason("I want to experience all that life has to offer and there are many experiences I haven't had yet which I want to have");
        reasons.add(reason1);
        reasons.add(reason2);
        reasons.add(reason3);

        Reason reason11 = new Reason("My Self");
        Reason reason22 = new Reason("Reason 22");
        Reason reason33 = new Reason("Reason 33");
        reasons2.add(reason11);
        reasons2.add(reason22);
        reasons2.add(reason33);


        ReasonSection reasonSection1 = new ReasonSection("Title1", R.drawable.myself,reasons);
        ReasonSection reasonSection2 = new ReasonSection("Title2", R.drawable.advice,reasons2);
        ReasonSection reasonSection3 = new ReasonSection("Title1", R.drawable.home,reasons);

        reasonSections.add(reasonSection1);
        reasonSections.add(reasonSection2);
        reasonSections.add(reasonSection3);

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