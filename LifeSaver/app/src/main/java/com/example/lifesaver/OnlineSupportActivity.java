package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lifesaver.bo.OnlineSupportBo;
import com.example.lifesaver.db.FirebaseDatabaseHelper;
import com.example.lifesaver.ui.adapter.OnlineSupportCustomAdapter;

import java.util.List;

public class OnlineSupportActivity extends AppCompatActivity {
    FirebaseDatabaseHelper firebaseDatabaseHelper;
    ListView onlineSupportList;
    LinearLayout emptyListMsg;
    ImageView backButton;
    TextView toolbartext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_support);

        onlineSupportList = findViewById(R.id.online_resource);
        emptyListMsg = findViewById(R.id.emptyLayout);

        populateListView();






        /** START TOOLBAR CONFIGURATION */
        backButton = findViewById(R.id.backButton);
        toolbartext = findViewById(R.id.toolbartext);
        toolbartext.setText("Online Support");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
        /** END TOOLBAR CONFIGURATION */
    }

    private void populateListView() {
        firebaseDatabaseHelper = new FirebaseDatabaseHelper();
        firebaseDatabaseHelper.readOnlineSupport(new FirebaseDatabaseHelper.OnlineResourcesReadCallback() {

            public void onDataRead(List<OnlineSupportBo> supportList, List<String> keys) {

                if(!supportList.isEmpty()) {
                    emptyListMsg.setVisibility(View.GONE);
                }
                onlineSupportList.setAdapter(new OnlineSupportCustomAdapter(OnlineSupportActivity.this,supportList,keys));
            }
        });
    }
}