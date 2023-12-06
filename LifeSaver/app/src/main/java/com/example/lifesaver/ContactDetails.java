package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lifesaver.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactDetails extends AppCompatActivity {

    TextView firstName;
    TextView secondName;
    TextView phoneNumber;
    CircleImageView photo;
    ImageView callBtn;
    ImageView messageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        firstName = findViewById(R.id.name);
        secondName = findViewById(R.id.family_name);
        phoneNumber = findViewById(R.id.phone_nbr);
        photo = findViewById(R.id.contact_img);
        callBtn = findViewById(R.id.call_btn);
        messageBtn = findViewById(R.id.message_btn);

        Intent i =this.getIntent();
        if(i != null){
            firstName.setText(i.getStringExtra("firstName"));
            secondName.setText(i.getStringExtra("secondName"));
            phoneNumber.setText(i.getStringExtra("phone"));
            if(!(i.getStringExtra("photo").equals(""))){
                photo.setImageURI(Uri.parse(i.getStringExtra("photo")));
            }
        }

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent();
                callIntent.setAction(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+(i.getStringExtra("phone")).toString()));
                startActivity(callIntent);
            }
        });

        messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri smsUri = Uri.parse("smsto:" + (i.getStringExtra("phone")).toString());
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, smsUri);
                startActivity(smsIntent);
            }
        });
    }
}