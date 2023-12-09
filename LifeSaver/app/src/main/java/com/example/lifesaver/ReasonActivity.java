package com.example.lifesaver;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class ReasonActivity extends AppCompatActivity {

    RecyclerView recycle;
    List<ReasonSection> reasonSections;
    ReasonSectionAdapter adapter;
    ReasonSectionDAO reasonSectionDAO;
    FloatingActionButton edit;
    ImageView backButton;
    TextView toolbartext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reason_main);

        backButton = findViewById(R.id.backButton);
        toolbartext = findViewById(R.id.toolbartext);
        toolbartext.setText("Reason Activity");

        reasonSectionDAO = new ReasonSectionDAO(this);
        reasonSections = reasonSectionDAO.getAll();

        edit = findViewById(R.id.edit);

        recycle=findViewById(R.id.reasonSection);
        recycle.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        adapter = new ReasonSectionAdapter(this,reasonSections);
        recycle.setAdapter(adapter);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReasonActivity.this, EditReasonActivity.class);
                ReasonActivity.this.startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update the data in the adapter
        adapter.updateData(reasonSectionDAO.getAll());
    }
}