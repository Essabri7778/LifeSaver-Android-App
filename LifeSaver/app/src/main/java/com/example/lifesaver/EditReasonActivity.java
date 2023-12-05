package com.example.lifesaver;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lifesaver.bo.Reason;
import com.example.lifesaver.bo.ReasonSection;
import com.example.lifesaver.dao.ReasonDAO;
import com.example.lifesaver.dao.ReasonSectionDAO;
import com.example.lifesaver.ui.adapter.ReasonSectionAdapter;
import com.example.lifesaver.ui.adapter.ReasonsAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class EditReasonActivity extends AppCompatActivity {

    RecyclerView recycle;
    List<ReasonSection> reasonSections;
    ReasonSectionAdapter adapter;
    BottomNavigationView bottomNav;
    Button save, add;
    ReasonSectionDAO reasonSectionDAO;
    ReasonDAO reasonDAO;
    ReasonsAdapter reasonAdapter;

    EditText ownReason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reason);

        reasonDAO = new ReasonDAO(this);

        reasonSectionDAO = new ReasonSectionDAO(this);
        reasonSections = reasonSectionDAO.getAll();

        bottomNav = findViewById(R.id.bottomNav);
        save = findViewById(R.id.save);
        add = findViewById(R.id.add);
        ownReason = findViewById(R.id.ownReason);


        recycle = findViewById(R.id.reasonSection);
        recycle.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        adapter = new ReasonSectionAdapter(this, reasonSections);
        recycle.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reason reason = new Reason(ownReason.getText().toString(),1,5);
                reasonDAO.addOne(reason);
                adapter.getCurrentReasonsAdapter().addReason(reason);
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
                    navigateToActivity(EditReasonActivity.class);
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