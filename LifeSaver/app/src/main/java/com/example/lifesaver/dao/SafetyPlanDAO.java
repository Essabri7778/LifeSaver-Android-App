package com.example.lifesaver.dao;

import static com.example.lifesaver.db.DatabaseHelper.COLUMN_SAFETY_RESPONSE;
import static com.example.lifesaver.db.DatabaseHelper.SAFETY_PLAN_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.lifesaver.db.DatabaseHelper;
import com.example.lifesaver.bo.SafetyPlanBo;

public class SafetyPlanDAO {

    private SQLiteDatabase db;

    public SafetyPlanDAO(Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean addOne(SafetyPlanBo safetyPlanModel){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SAFETY_RESPONSE, safetyPlanModel.getResponse());
        long insert = db.insert(SAFETY_PLAN_TABLE, null, cv);
        if(insert == -1){
            return false;
        }
        return true;


    }

    public boolean updateOne(SafetyPlanBo safetyPlanModel){


        return true;
    }
}
