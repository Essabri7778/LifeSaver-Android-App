package com.example.lifesaver.dao;

import static com.example.lifesaver.db.DatabaseHelper.COLUMN_SAFETY_ID;
import static com.example.lifesaver.db.DatabaseHelper.COLUMN_SAFETY_RESPONSE;
import static com.example.lifesaver.db.DatabaseHelper.SAFETY_PLAN_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lifesaver.db.DatabaseHelper;
import com.example.lifesaver.bo.SafetyPlanBo;

import java.util.ArrayList;
import java.util.List;

public class SafetyPlanDAO {

    private SQLiteDatabase db;

    public SafetyPlanDAO(Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean addOne(SafetyPlanBo safetyPlan){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SAFETY_RESPONSE, safetyPlan.getResponse());
        long insert = db.insert(SAFETY_PLAN_TABLE, null, cv);
        if(insert == -1){
            return false;
        }
        return true;

    }

    public int updateOne(SafetyPlanBo safetyPlan){
        ContentValues values = new ContentValues();
        values.put(COLUMN_SAFETY_RESPONSE,safetyPlan.getResponse());

        String whereClause = COLUMN_SAFETY_ID + "=?";
        String[] whereArgs = {String.valueOf(safetyPlan.getId())};

        return db.update(SAFETY_PLAN_TABLE, values, whereClause, whereArgs);

    }


    public List<SafetyPlanBo> getAll(){

        List<SafetyPlanBo> list = new ArrayList<>();

        String query = "SELECT * FROM "+ SAFETY_PLAN_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){

            do {
                int columnId = cursor.getInt(0);
                String columnResponse = cursor.getString(1);
                SafetyPlanBo sf = new SafetyPlanBo(columnId, columnResponse);
                list.add(sf);

            }while (cursor.moveToNext());

        }
        cursor.close();

        return list;
    }


}
