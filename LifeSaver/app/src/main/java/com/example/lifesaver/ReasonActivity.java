package com.example.lifesaver;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class ReasonActivity extends AppCompatActivity {

    RecyclerView recycle;
    List<ReasonSection> reasonSections;
    ReasonSectionAdapter adapter;
    BottomNavigationView bottomNav;
    ReasonSectionDAO reasonSectionDAO;

    FloatingActionButton edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reason_main);

        reasonSectionDAO = new ReasonSectionDAO(this);
        reasonSections = reasonSectionDAO.getAll();

        bottomNav = findViewById(R.id.bottomNav);
        edit = findViewById(R.id.edit);

        recycle=findViewById(R.id.reasonSection);
        recycle.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        adapter = new ReasonSectionAdapter(this,reasonSections);
        recycle.setAdapter(adapter);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivity(EditReasonActivity.class);
            }
        });


        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.home) {
                    navigateToActivity(ReasonActivity.class);
                    return true;
                } else if (id == R.id.advice) {
                    Dialog dialog = new Dialog(ReasonActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.main_button_layout);
                    dialog.show();
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

    @Override
    protected void onResume() {
        super.onResume();
        // Update the data in the adapter
        adapter.updateData(reasonSectionDAO.getAll());
    }
}