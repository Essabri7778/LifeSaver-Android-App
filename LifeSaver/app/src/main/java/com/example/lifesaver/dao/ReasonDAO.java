package com.example.lifesaver.dao;

import static com.example.lifesaver.db.DatabaseHelper.COLUMN_SAFETY_RESPONSE;
import static com.example.lifesaver.db.DatabaseHelper.REASON_COLUMN_SECTION_ICON;
import static com.example.lifesaver.db.DatabaseHelper.REASON_COLUMN_SECTION_TITLE;
import static com.example.lifesaver.db.DatabaseHelper.REASON_TABLE_SECTIONS;
import static com.example.lifesaver.db.DatabaseHelper.SAFETY_PLAN_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.lifesaver.R;
import com.example.lifesaver.bo.ReasonSection;
import com.example.lifesaver.bo.SafetyPlanBo;
import com.example.lifesaver.db.DatabaseHelper;

public class ReasonDAO {

    private SQLiteDatabase db;

    public ReasonDAO(Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        initiateReasonSection();
    }

    public boolean addOne(ReasonSection reasonSection){
        ContentValues cv = new ContentValues();
        cv.put(REASON_COLUMN_SECTION_TITLE, reasonSection.getTitle());
        cv.put(REASON_COLUMN_SECTION_ICON, reasonSection.getIcon());
        long insert = db.insert(REASON_TABLE_SECTIONS, null, cv);
        if(insert == -1){
            return false;
        }
        return true;
    }

    private void initiateReasonSection() {
        addOne(new ReasonSection("MySelf", R.drawable.myself,null));
        addOne(new ReasonSection("Family and Friends", R.drawable.myself,null));
        addOne(new ReasonSection("Understanding my choices", R.drawable.myself,null));
        addOne(new ReasonSection("Hopes and beliefs", R.drawable.myself,null));
        addOne(new ReasonSection("Your own reasons", R.drawable.myself,null));
    }


    


}
