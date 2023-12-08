package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;
import com.example.lifesaver.fragments.HomeFragment;
import com.example.lifesaver.fragments.MySpaceFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
                Toast.makeText(this,"survey",Toast.LENGTH_LONG).show();
                return true;
            }else if(id == R.id.advice){
                Toast.makeText(this,"survey",Toast.LENGTH_LONG).show();
                return true;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        });
    }

}