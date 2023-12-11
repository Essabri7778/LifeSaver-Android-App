package com.example.lifesaver;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lifesaver.dao.ContactDAO;
import com.example.lifesaver.fragments.AdviceFragment;
import com.example.lifesaver.fragments.HomeFragment;
import com.example.lifesaver.fragments.MySpaceFragment;
import com.example.lifesaver.fragments.SurveyFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;
    FloatingActionButton buttonEmergency;
    ContactDAO contactDAO;

    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        setContentView(R.layout.activity_home);

        contactDAO = new ContactDAO(HomeActivity.this);

        bottomNav = findViewById(R.id.bottomNav);
        buttonEmergency = findViewById(R.id.buttonEmergency);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if(id == R.id.home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.mySpace) {
                selectedFragment = new MySpaceFragment();
            }else if(id == R.id.emergency){
                Toast.makeText(HomeActivity.this,"mainButtonPhone.getText().toString()",Toast.LENGTH_LONG).show();
                return true;
            }else if(id == R.id.survey){
                selectedFragment = new SurveyFragment();
            }else if(id == R.id.advice){
                selectedFragment = new AdviceFragment();
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        });


        //for redirect from survey
        if(getIntent().getBooleanExtra("navigate_to_myspace",false)){
            MySpaceFragment mySpaceFragment = new MySpaceFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, mySpaceFragment)
                    .commit();
            updateSelectedItem(R.id.mySpace);
        }

        if(contactDAO.getByName("myHopeEmergency").getPhoneNumber().equals("")){
            Dialog dialog = new Dialog(HomeActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.intro_popus);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }


        //for main button
        buttonEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(HomeActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.main_button_layout);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                LinearLayout confirmMainButtonPhone = dialog.findViewById(R.id.confirmMainButtonPhone);
                EditText mainButtonPhone = dialog.findViewById(R.id.mainButtonPhone);
                LinearLayout contactMainBut = dialog.findViewById(R.id.contactMainBut);

                mainButtonPhone.setText(contactDAO.getByName("myHopeEmergency").getPhoneNumber());
                confirmMainButtonPhone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mainButtonPhone.getText().toString().equals("")){
                            Toast.makeText(HomeActivity.this,"Please enter a valid phone number⚠️",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(HomeActivity.this,"Emergency phone number updated\uD83D\uDEA8",Toast.LENGTH_LONG).show();
                            contactDAO.updatePhoneNum(contactDAO.getByName("myHopeEmergency").getId(),
                                    mainButtonPhone.getText().toString());
                            dialog.dismiss();
                        }
                    }
                });


                contactMainBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestContactsPermission();
                    }
                });

            }
        });


        buttonEmergency.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(contactDAO.getByName("myHopeEmergency").getPhoneNumber().equals("")){
                    Toast.makeText(HomeActivity.this,"Please first setup an emergency phone number by clicking once⚠️",Toast.LENGTH_LONG).show();
                }else{
                    if(ActivityCompat.checkSelfPermission(HomeActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions((Activity) HomeActivity.this, new String[]{Manifest.permission.CALL_PHONE},101);
                    }else{
                        Intent callIntent =new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel: "+contactDAO.getByName("myHopeEmergency").getPhoneNumber()));
                        HomeActivity.this.startActivity(callIntent);

                        Toast.makeText(HomeActivity.this,"\uD83D\uDEA8Emergency help is on the way.\nStay safe, You are not alone\uD83E\uDEF6",Toast.LENGTH_LONG).show();
                    }
                }
                return true;
            }
        });

    }

    public void updateSelectedItem(int itemId) {
        bottomNav.setSelectedItemId(itemId);
    }


    //used to: adding to main button from contacts
    private static final int REQUEST_READ_CONTACTS_PERMISSION = 0;
    private static final int REQUEST_CONTACT = 1;

    private boolean hasContactsPermission()
    {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED;
    }

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

            int updateContactMain = contactDAO.updatePhoneNum(contactDAO.getByName("myHopeEmergency").getId(), phoneNbr);
            if (updateContactMain==1) {
                Toast.makeText(this, "Emergency phone number updated\uD83D\uDEA8", Toast.LENGTH_LONG).show();
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            } else {
                Toast.makeText(this, "Failed to update emergency phone number⚠️", Toast.LENGTH_LONG).show();
            }
        }
    }

}