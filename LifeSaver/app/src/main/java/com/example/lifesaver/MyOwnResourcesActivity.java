package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lifesaver.bo.ContactBo;
import com.example.lifesaver.bo.ResourceBo;
import com.example.lifesaver.dao.ContactDAO;
import com.example.lifesaver.dao.ResourceDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MyOwnResourcesActivity extends AppCompatActivity {

    ResourceDAO resourceDAO;
    ListView ownResource;
    ListView bookmark;
    FloatingActionButton addBtn;
    LinearLayout layout1;
    LinearLayout layout2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_own_resources);

        ownResource = findViewById(R.id.resourcesList);
        bookmark = findViewById(R.id.bookmarksList);
        addBtn = findViewById(R.id.add_button);
        layout1 = findViewById(R.id.emptyLayout1);
        layout2 = findViewById(R.id.emptyLayout2);
        resourceDAO = new ResourceDAO(MyOwnResourcesActivity.this);


        populateListView();
        ownResource.setClickable(true);
        bookmark.setClickable(true);

        ownResource.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = ownResource.getItemAtPosition(position);
                ResourceBo resource = (ResourceBo) o;
                populateOwnResourceForm(resource);
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyOwnResourcesActivity.this, AddResourceActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void populateOwnResourceForm(ResourceBo resource) {
        Intent i = new Intent(MyOwnResourcesActivity.this, DisplayResourceActivity.class);
        i.putExtra("name",resource.getName());
        i.putExtra("desc",resource.getDescription());
        i.putExtra("phone",resource.getPhone());
        i.putExtra("sms",resource.getSms());
        i.putExtra("email",resource.getEmail());
        i.putExtra("website",resource.getWebsite());
        startActivity(i);
    }


    public void populateListView(){
        List<ResourceBo> ownResourceList = new ArrayList<>();
        List<ResourceBo> bookedResourceList = new ArrayList<>();
        ownResourceList = resourceDAO.getOwnResources();
        bookedResourceList = resourceDAO.getBookedResources();
        if(!ownResourceList.isEmpty()) {
            layout1.setVisibility(View.GONE);
        }
        if(!bookedResourceList.isEmpty()) {
            layout2.setVisibility(View.GONE);
        }
        ownResource.setAdapter(new ResourceCustomAdapter(this,ownResourceList));
        bookmark.setAdapter(new ResourceCustomAdapter(this,bookedResourceList));
    }
}