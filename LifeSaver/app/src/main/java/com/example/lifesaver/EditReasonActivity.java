package com.example.lifesaver;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class EditReasonActivity extends AppCompatActivity {

    RecyclerView recycle;
    List<ReasonSection> reasonSections;
    ReasonSectionAdapter adapter;
    BottomNavigationView bottomNav;
    Button add, edit, delete;
    ImageView backButton;
    ReasonSectionDAO reasonSectionDAO;
    ReasonDAO reasonDAO;
    EditText ownReason,ownReasonId;

    TextView idReason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reason);

        reasonDAO = new ReasonDAO(this);

        reasonSectionDAO = new ReasonSectionDAO(this);
        reasonSections = reasonSectionDAO.getAll();

        bottomNav = findViewById(R.id.bottomNav);
        add = findViewById(R.id.add_reason_own);
        edit = findViewById(R.id.edit_reason_own);
        delete = findViewById(R.id.delete_reason_own);
        ownReason = findViewById(R.id.ownReason);
        backButton = findViewById(R.id.backButton);
        idReason = findViewById(R.id.idReason);
        ownReasonId = findViewById(R.id.idownReason);


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

        /*
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reasonDAO.updateText(Integer.parseInt(ownReasonId.getText().toString()),ownReason.getText().toString());
                adapter.getCurrentReasonsAdapter().notifyDataSetChanged();
            }
        });

         */

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
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

    @Override
    protected void onResume() {
        super.onResume();
        // Update the data in the adapter
        adapter.updateData(reasonSectionDAO.getAll());
    }
}