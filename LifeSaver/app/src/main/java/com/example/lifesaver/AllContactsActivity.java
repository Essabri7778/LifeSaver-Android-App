package com.example.lifesaver;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class AllContactsActivity extends AppCompatActivity {

    String contacts[]= {"Mamixa", "namnam bennmamoun"};
    ListView listView;
    FloatingActionButton addButton;
    TextView text;
    private static final int REQUEST_READ_CONTACTS_PERMISSION = 0;
    private static final int REQUEST_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contacts);

        // Intent to pick contacts

        listView = findViewById(R.id.contactList);
        addButton = findViewById(R.id.add_button);
        text = findViewById(R.id.test);


        listView.setAdapter(new FindHelpCustomAdapter(this,contacts));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestContactsPermission();
            }
        });



    }

    private boolean hasContactsPermission()
    {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED;
    }

    // Request contact permission if it has not been granted already
    private void requestContactsPermission()
    {
        if (!hasContactsPermission())
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS_PERMISSION);
        }else{
            pickContact();
        }
    }

    private void pickContact(){
        final Intent pickContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(pickContactIntent, REQUEST_CONTACT);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_READ_CONTACTS_PERMISSION )
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted, proceed with picking a contact
                pickContact();
            } else {
                // Permission denied, show a message or take appropriate action
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) return;

        if (requestCode == REQUEST_CONTACT && data != null)
        {
            Uri contactUri = data.getData();

            // Specify which fields you want your
            // query to return values for
            String[] queryFields = new String[]{ContactsContract.Contacts.DISPLAY_NAME};


            // Perform your query - the contactUri
            // is like a "where" clause here
            Cursor cursor = this.getContentResolver()
                    .query(contactUri, queryFields, null, null, null);
            try
            {
                // Double-check that you
                // actually got results
                if (cursor.getCount() == 0) return;

                // Pull out the first column
                // of the first row of data
                // that is your contact's name
                cursor.moveToFirst();

                String name = cursor.getString(0);
                text.setText(name);

            }
            finally
            {
                cursor.close();
            }
        }
    }
}