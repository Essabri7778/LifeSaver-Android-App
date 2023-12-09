package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lifesaver.dao.ContactDAO;
import com.example.lifesaver.fragments.AdviceFragment;
import com.example.lifesaver.fragments.HomeFragment;
import com.example.lifesaver.fragments.MySpaceFragment;
import com.example.lifesaver.fragments.SurveyFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;
    MenuHelper menuHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ContactDAO contactDAO = new ContactDAO(this);

        bottomNav = findViewById(R.id.bottomNav);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if(id == R.id.home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.mySpace) {
                selectedFragment = new MySpaceFragment();
            }else if(id == R.id.emergency){
                Toast.makeText(this,"mainButtonPhone.getText().toString()",Toast.LENGTH_LONG).show();
                return true;
            }else if(id == R.id.survey){
                selectedFragment = new SurveyFragment();
            }else if(id == R.id.advice){
                selectedFragment = new AdviceFragment();
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        });


        menuHelper = new MenuHelper(this);
        menuHelper.setupMenu();

        if(getIntent().getBooleanExtra("navigate_to_myspace",false)){
            MySpaceFragment mySpaceFragment = new MySpaceFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, mySpaceFragment)
                    .commit();
        }
    }

    public void updateSelectedItem(int itemId) {
        bottomNav.setSelectedItemId(itemId);
    }

}