package com.example.lifesaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;
    MenuHelper menuHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Schedule the first notification at 10:00 AM
        scheduleNotification(getApplicationContext(), 1, "Morning Check-In", "Good morning! How are you feeling today? Remember, you're not alone. If you need someone to talk to, we're here for you.", 10, 00);

        // Schedule the second notification at 8:00 PM
        scheduleNotification(getApplicationContext(), 2, "End-of-Day Reflection", "As the day winds down, take a moment to reflect on your feelings. If today was tough, tomorrow is a new opportunity. Reach out if you need support—we care about you.", 20, 00);

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

    private void scheduleNotification(Context context, int notificationId, String title, String message, int hour, int minute) {
        // Set the time for the notification
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        // Create an intent to trigger the BroadcastReceiver
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("title", title);
        intent.putExtra("message", message);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get the AlarmManager
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Schedule the notification
        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

}