package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifesaver.bo.ResourceBo;
import com.example.lifesaver.dao.ResourceDAO;
import com.example.lifesaver.ui.adapter.ResourceCustomAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MyOwnResourcesActivity extends AppCompatActivity {

    ResourceDAO resourceDAO;
    ListView ownResource;
    ListView bookmark;
    FloatingActionButton addBtn;
    LinearLayout layout1;
    LinearLayout layout2;

    ImageView backButton;
    TextView toolbartext;



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
        ownResource.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = ownResource.getItemAtPosition(position);
                ResourceBo contact = (ResourceBo) o;
                showDeleteDialog(contact.getId());
                return true;
            }
        });
        bookmark.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = bookmark.getItemAtPosition(position);
                ResourceBo resource = (ResourceBo) o;
                populateBookedResourceForm(resource);
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyOwnResourcesActivity.this, AddResourceActivity.class);
                startActivity(i);
                
            }
        });

        backButton = findViewById(R.id.backButton);
        toolbartext = findViewById(R.id.toolbartext);
        toolbartext.setText("My Own Resources");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }

    private void populateBookedResourceForm(ResourceBo resource) {
        Intent i = null;
        Class<? extends AppCompatActivity> aClass = resource.getType() == 2 ? DisplayUrgentSuportActivity.class : DisplayOnlineSupportActivity.class;
        i = new Intent(MyOwnResourcesActivity.this, aClass);
        i.putExtra("name",resource.getName());
        i.putExtra("desc",resource.getDescription());
        i.putExtra("phone",resource.getPhone());
        i.putExtra("sms",resource.getSms());
        i.putExtra("email",resource.getEmail());
        i.putExtra("website",resource.getWebsite());
        i.putExtra("logo",resource.getLogo());
        startActivity(i);
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
        List<ResourceBo> ownResourceList;
        List<ResourceBo> bookedResourceList;
        ownResourceList = resourceDAO.getOwnResources();
        bookedResourceList = resourceDAO.getOtherResources();
        if(!ownResourceList.isEmpty()) {
            layout1.setVisibility(View.GONE);
        }
        if(!bookedResourceList.isEmpty()) {
            layout2.setVisibility(View.GONE);
        }
        ownResource.setAdapter(new ResourceCustomAdapter(this,ownResourceList));
        bookmark.setAdapter(new ResourceCustomAdapter(this,bookedResourceList));
    }

    public void showDeleteDialog(int id){
        Dialog dialog = new Dialog(MyOwnResourcesActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.contact_popup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        LinearLayout yes = dialog.findViewById(R.id.yes);
        LinearLayout no = dialog.findViewById(R.id.no);
        TextView title = dialog.findViewById(R.id.title);
        TextView para = dialog.findViewById(R.id.para);

        title.setText("Delete your own?");
        para.setText("Are you sure you would like to delete this resource?");

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteResource(id);
                Toast.makeText(MyOwnResourcesActivity.this,"Contact deleted successfully",Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void  deleteResource(int id){

        resourceDAO.deleteResource(id);
        populateListView();
    }

}