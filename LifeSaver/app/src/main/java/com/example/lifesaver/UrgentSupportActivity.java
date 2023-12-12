package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lifesaver.bo.UrgentSupportBo;
import com.example.lifesaver.db.FirebaseDatabaseHelper;

import java.util.List;

public class UrgentSupportActivity extends AppCompatActivity {

    FirebaseDatabaseHelper firebaseDatabaseHelper;
    ListView urgentSupportList;
    LinearLayout emptyListMsg;

    ImageView backButton;
    TextView toolbartext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urgent_support);

        urgentSupportList = findViewById(R.id.urgentResourceList);
        emptyListMsg = findViewById(R.id.emptyLayout);


        populateListView();


        /** START TOOLBAR CONFIGURATION */
        backButton = findViewById(R.id.backButton);
        toolbartext = findViewById(R.id.toolbartext);
        toolbartext.setText("Urgent Support");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
        /** ENd TOOLBAR CONFIGURATION */
    }

    public void populateListView(){
        firebaseDatabaseHelper = new FirebaseDatabaseHelper();
        firebaseDatabaseHelper.readUrgentSupport(new FirebaseDatabaseHelper.UrgentResourcesReadCallback() {

            public void onDataRead(List<UrgentSupportBo> supportList,List<String> keys) {

                if(!supportList.isEmpty()) {
                    emptyListMsg.setVisibility(View.GONE);
                }
                urgentSupportList.setAdapter(new UrgentSupportCustomAdapter(UrgentSupportActivity.this,supportList,keys));
            }
        });
    }
}