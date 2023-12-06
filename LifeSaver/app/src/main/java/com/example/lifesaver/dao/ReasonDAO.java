package com.example.lifesaver.dao;

import static com.example.lifesaver.db.DatabaseHelper.COLUMN_SAFETY_ID;
import static com.example.lifesaver.db.DatabaseHelper.COLUMN_SAFETY_RESPONSE;
import static com.example.lifesaver.db.DatabaseHelper.REASON_COLUMN_CHECKED;
import static com.example.lifesaver.db.DatabaseHelper.REASON_COLUMN_ID;
import static com.example.lifesaver.db.DatabaseHelper.REASON_COLUMN_SECTION_ID;
import static com.example.lifesaver.db.DatabaseHelper.REASON_COLUMN_SECTION_TITLE;
import static com.example.lifesaver.db.DatabaseHelper.REASON_COLUMN_TEXT;
import static com.example.lifesaver.db.DatabaseHelper.REASON_TABLE;
import static com.example.lifesaver.db.DatabaseHelper.REASON_TABLE_SECTIONS;
import static com.example.lifesaver.db.DatabaseHelper.SAFETY_PLAN_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lifesaver.R;
import com.example.lifesaver.bo.Reason;
import com.example.lifesaver.bo.ReasonSection;
import com.example.lifesaver.bo.SafetyPlanBo;
import com.example.lifesaver.db.DatabaseHelper;
import java.util.ArrayList;
import java.util.List;

public class ReasonDAO {

    private SQLiteDatabase db;

    public ReasonDAO(Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        if(getAll().size()==0) initiateSection();
    }

    public boolean addOne(Reason reason){
        ContentValues cv = new ContentValues();
        cv.put(REASON_COLUMN_TEXT, reason.getReason());
        cv.put(REASON_COLUMN_SECTION_ID, reason.getSectionId());
        cv.put(REASON_COLUMN_CHECKED, reason.isCheked());
        long insert = db.insert(REASON_TABLE,null, cv);
        if(insert == -1){
            return false;
        }
        return true;
    }

    private void initiateSection() {
        addOne(new Reason("I care enough about myself to live", 0,1));
        addOne(new Reason("I have the courage to face life",0,1));
        addOne(new Reason("I want to experience all that life has to offer and there are many experiences I haven't had yet which I want to have",1,1));
        addOne(new Reason("No matter how badly I feel I know that it will not last",1,1));
        addOne(new Reason("I believe I can learn to adjust or cope with my problems",1,1));
        addOne(new Reason("I am afraid of the unknown ",1,1));
        addOne(new Reason("I love and enjoy my family and friends too much and could not leave them",1,2));
        addOne(new Reason("I am worried my family and friends might believe I did not love them",1,2));
        addOne(new Reason("I believe killing myself would not really accomplish or solve anything",1,3));
        addOne(new Reason("I do not want to die",1,3));
        addOne(new Reason("I am afraid of the actual \"act\" of killing myself",1,3));
        addOne(new Reason("I believe I have control over my life and destiny",1,3));
        addOne(new Reason("I have hope that things will improve and the future will be happier",1,4));
        addOne(new Reason("I believe I can find other solutions to my problems",1,4));
        addOne(new Reason("I believe I can find a purpose in life, a reason to live",1,4));
        addOne(new Reason("I am curious about what will happen in the future",1,4));
        addOne(new Reason("Life is all we have and is better than nothing",1,4));


    }

    public List<Reason> getAll(){

        List<Reason> list = new ArrayList<>();

        String query = "SELECT * FROM "+ REASON_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){

            do {
                int columnId = cursor.getInt(0);
                String columnText = cursor.getString(1);
                int columnSection = cursor.getInt(3);
                int columnCheck = cursor.getInt(2);
                Reason rs = new Reason(columnId, columnText, columnCheck, columnSection);
                list.add(rs);

            }while (cursor.moveToNext());

        }
        cursor.close();

        return list;
    }

    public int updateCheckedStatus(int reasonId, int isChecked) {
        ContentValues values = new ContentValues();
        values.put(REASON_COLUMN_CHECKED, isChecked);

        String whereClause = REASON_COLUMN_ID + "=?";
        String[] whereArgs = {String.valueOf(reasonId)};

        return db.update(REASON_TABLE, values, whereClause, whereArgs);
    }


    public List<Reason> getBySection(int sectionId) {

        List<Reason> list = new ArrayList<>();

        String query = "SELECT * FROM " + REASON_TABLE + " WHERE " + REASON_COLUMN_SECTION_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(sectionId)});

        if(cursor.moveToFirst()){

            do {
                int columnId = cursor.getInt(0);
                String columnText = cursor.getString(1);
                int columnSection = cursor.getInt(3);
                int columnCheck = cursor.getInt(2);
                Reason rs = new Reason(columnId, columnText, columnCheck, columnSection);
                list.add(rs);

            }while (cursor.moveToNext());

        }
        cursor.close();

        return list;
    }

}
