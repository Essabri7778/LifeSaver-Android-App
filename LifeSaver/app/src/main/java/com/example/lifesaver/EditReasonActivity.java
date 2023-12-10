package com.example.lifesaver;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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

public class EditReasonActivity extends AppCompatActivity {

    RecyclerView recycle;
    List<ReasonSection> reasonSections;
    ReasonSectionAdapter adapter;
    BottomNavigationView bottomNav;
    Button add;
    ImageView backButton;
    ReasonSectionDAO reasonSectionDAO;
    ReasonDAO reasonDAO;
    EditText ownReason;
    TextView idReason, toolbartext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reason);

        reasonDAO = new ReasonDAO(this);

        reasonSectionDAO = new ReasonSectionDAO(this);
        reasonSections = reasonSectionDAO.getAll();

        bottomNav = findViewById(R.id.bottomNav);
        add = findViewById(R.id.add_reason_own);
        ownReason = findViewById(R.id.ownReason);
        idReason = findViewById(R.id.idReason);
        backButton = findViewById(R.id.backButton);
        toolbartext = findViewById(R.id.toolbartext);
        toolbartext.setText("Edit Reasons To Live");


        recycle = findViewById(R.id.reasonSection);
        recycle.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        adapter = new ReasonSectionAdapter(this, reasonSections);
        recycle.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newReasonText = ownReason.getText().toString();
                if(!newReasonText.equals("")){
                    Reason reason = new Reason(ownReason.getText().toString(),1,5);
                    reasonDAO.addOne(reason);
                    adapter.getCurrentReasonsAdapter().addReason(reason);
                    Toast.makeText(EditReasonActivity.this,"Reason added successfully \uD83C\uDF89",Toast.LENGTH_LONG).show();
                    ownReason.setText("");
                }else{
                    Toast.makeText(EditReasonActivity.this,"Please enter a valid Reason ⚠️",Toast.LENGTH_LONG).show();
                }

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