package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifesaver.bo.ResourceBo;
import com.example.lifesaver.dao.ContactDAO;
import com.example.lifesaver.dao.ResourceDAO;

public class AddResourceActivity extends AppCompatActivity {

    ResourceDAO resourceDao;
    TextView nameTxt;
    TextView emailTxt;
    TextView descTxt;
    TextView phoneTxt;
    TextView smsTxt;
    TextView websiteTxt;
    Button saveBtn;

    ImageView backButton;
    TextView toolbartext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resource);

        nameTxt = findViewById(R.id.resource_name);
        descTxt = findViewById(R.id.resource_desc);
        phoneTxt = findViewById(R.id.resource_phone);
        smsTxt = findViewById(R.id.resource_sms);
        websiteTxt = findViewById(R.id.resource_website);
        emailTxt = findViewById(R.id.resource_email);
        saveBtn = findViewById(R.id.save_btn);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameTxt.getText().toString();
                String description = descTxt.getText().toString();
                String sms = smsTxt.getText().toString();
                String phone = phoneTxt.getText().toString();
                String website = websiteTxt.getText().toString();
                String email = emailTxt.getText().toString();
                resourceDao = new ResourceDAO(AddResourceActivity.this);


                if(name.equals("") || description.equals("") || sms.equals("")
                        || phone.equals("") || website.equals("")|| email.equals("")){
                    Toast.makeText(AddResourceActivity.this,"Please enter all fields!",Toast.LENGTH_SHORT).show();
                }else {
                    ResourceBo resource = new ResourceBo(name,description,phone,sms,email,website,"",1);
                    boolean res = resourceDao.addResource(resource);

                    if(res){
                        Toast.makeText(AddResourceActivity.this,"Resource added successfully",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AddResourceActivity.this,"Operation failed",Toast.LENGTH_SHORT).show();

                    }
                    Intent i = new Intent(AddResourceActivity.this,MyOwnResourcesActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        backButton = findViewById(R.id.backButton);
        toolbartext = findViewById(R.id.toolbartext);
        toolbartext.setText("Add Own Resources");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

    }
}