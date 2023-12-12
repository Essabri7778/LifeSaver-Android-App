package com.example.lifesaver;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifesaver.bo.ContactBo;
import com.example.lifesaver.dao.ContactDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class AllContactsActivity extends AppCompatActivity {

    ContactDAO contactDAO;

    ListView listView;
    FloatingActionButton addButton;
    LinearLayout emptyLayout;

    ImageView backButton;
    TextView toolbartext;

    private static final int REQUEST_READ_CONTACTS_PERMISSION = 0;
    private static final int REQUEST_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contacts);

        // Intent to pick contacts

        listView = findViewById(R.id.contactList);
        addButton = findViewById(R.id.add_button);
        emptyLayout = findViewById(R.id.emptyLayout);



        populateListView();
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                ContactBo contact = (ContactBo) o;
                contactDetailsDialog(contact);

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    Object o = listView.getItemAtPosition(position);
                    ContactBo contact = (ContactBo) o;
                    showDeleteDialog(contact.getId());
                }
                return true;
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestContactsPermission();
            }
        });


        backButton = findViewById(R.id.backButton);
        toolbartext = findViewById(R.id.toolbartext);
        toolbartext.setText("All Contacts");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });



    }


    private void contactDetailsDialog(ContactBo contact) {
        Dialog dialog = new Dialog(AllContactsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.contact_details_popup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        TextView firstName;
        TextView secondName;
        TextView phoneNumber;
        ImageView photo;
        ImageView callBtn;
        ImageView messageBtn;

        callBtn = dialog.findViewById(R.id.call_btn);
        messageBtn = dialog.findViewById(R.id.message_btn);
        firstName = dialog.findViewById(R.id.name);
        secondName = dialog.findViewById(R.id.family_name);
        phoneNumber = dialog.findViewById(R.id.phone_nbr);
        photo = dialog.findViewById(R.id.contact_img);

        firstName.setText(contact.getPrimaryName());
        secondName.setText(contact.getAlternativeName());
        phoneNumber.setText(contact.getPhoneNumber());
        if(!(String.valueOf(contact.getPhoto()).equals(""))){
            photo.setImageURI(Uri.parse(String.valueOf(contact.getPhoto())));
        }

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent();
                callIntent.setAction(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+(contact.getPhoneNumber())));
                startActivity(callIntent);
            }
        });

        messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri smsUri = Uri.parse("smsto:" + (contact.getPhoneNumber()));
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, smsUri);
                startActivity(smsIntent);
            }
        });

        dialog.show();
    }

    private void populateListView(){
        contactDAO = new ContactDAO(AllContactsActivity.this);
        List<ContactBo> contactList = contactDAO.getAllContacts();
        if(!contactList.isEmpty()) {
            emptyLayout.setVisibility(View.GONE);
        }
            listView.setAdapter(new FindHelpCustomAdapter(this,contactList));

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
            String identifier = null;
            String firstName = null;
            String altName = null;
            String phoneNbr = null;
            Uri photoUri = Uri.parse("");
            Uri contactUri = data.getData();

            // Specify which fields you want your query to return values for
            String[] queryFields = new String[]{ContactsContract.Contacts._ID,
                                                ContactsContract.Contacts.PHOTO_URI,
                                                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
                                                ContactsContract.Contacts.DISPLAY_NAME_ALTERNATIVE,
                                                };


            // Perform your query - the contactUri is like a "where" clause here
            Cursor cursor = this.getContentResolver()
                    .query(contactUri, queryFields, null, null, null);
            try
            {
                // Double-check that you actually got results
                if (cursor.getCount() == 0) return;

                cursor.moveToFirst();


                identifier = cursor.getString(0);
                firstName = cursor.getString(2);
                altName = cursor.getString(3);
                photoUri = cursor.getString(1).equals("") ? Uri.parse(""):Uri.parse(cursor.getString(1));
            }catch (Exception e) {
                e.printStackTrace();
            } // Log the exception
            finally {cursor.close();}

            // cursor to get phone number by using identifier
            Cursor phoneCursor = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    new String[]{identifier},
                    null
            );

            try {
                phoneCursor.moveToFirst();
                phoneNbr=phoneCursor.getString(0);
            } catch (Exception e) {
                e.printStackTrace(); // Log the exception
            } finally {

                phoneCursor.close();

            }

            addContact(identifier,firstName,altName,photoUri,phoneNbr);

        }
    }

    public void addContact(String identifier, String primaryName, String alternativeName, Uri photo, String phoneNumber){
        ContactBo contact = new ContactBo();
        //check if already exist
        FindHelpCustomAdapter adapter = (FindHelpCustomAdapter) listView.getAdapter();
        List<ContactBo> ls = adapter.getDataList();
        for(ContactBo elem : ls){
            if (identifier.equals(elem.getIdentifier())) {
                // Contact already exists, do not add it again
                return;
            }
        }

        //if not add it

        contact.setIdentifier(identifier);
        contact.setPrimaryName(primaryName);
        contact.setAlternativeName(alternativeName);
        contact.setPhoneNumber(phoneNumber);
        contact.setPhoto(photo);



        //add a contact
        boolean res = contactDAO.addContact(contact);
        // Refresh the listView
        populateListView();
    }



    /** Delete contact logic */

    public void showDeleteDialog(int id){
        Dialog dialog = new Dialog(AllContactsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.contact_popup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        LinearLayout yes = dialog.findViewById(R.id.yes);
        LinearLayout no = dialog.findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact(id);
                Toast.makeText(AllContactsActivity.this,"Contact deleted successfully",Toast.LENGTH_LONG).show();
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

    public void deleteContact(int id){
        //delete the contact
        contactDAO.deleteContact(id);
        //refresh listView
        populateListView();
    }
}