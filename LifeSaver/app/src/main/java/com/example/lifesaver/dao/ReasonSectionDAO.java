package com.example.lifesaver.dao;

import static com.example.lifesaver.db.DatabaseHelper.REASON_COLUMN_SECTION_ICON;
import static com.example.lifesaver.db.DatabaseHelper.REASON_COLUMN_SECTION_TITLE;
import static com.example.lifesaver.db.DatabaseHelper.REASON_TABLE_SECTIONS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lifesaver.MainActivity;
import com.example.lifesaver.R;
import com.example.lifesaver.bo.Reason;
import com.example.lifesaver.bo.ReasonSection;
import com.example.lifesaver.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ReasonSectionDAO {

    private SQLiteDatabase db;
    ReasonDAO reasonDAO;

    public ReasonSectionDAO(Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        reasonDAO = new ReasonDAO(context);
        db = dbHelper.getWritableDatabase();
        if(getAll().size()==0) initiateReasonSection();
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
        addOne(new ReasonSection("Family and Friends", R.drawable.family,null));
        addOne(new ReasonSection("Understanding my choices", R.drawable.choices,null));
        addOne(new ReasonSection("Hopes and beliefs", R.drawable.beliefs,null));
        addOne(new ReasonSection("Your own reasons", R.drawable.own,null));
    }

    public List<ReasonSection> getAll(){

        List<ReasonSection> list = new ArrayList<>();

        String query = "SELECT * FROM "+ REASON_TABLE_SECTIONS;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){

            do {
                int columnId = cursor.getInt(0);
                String columnTitle = cursor.getString(1);
                int columnIcon = cursor.getInt(2);
                ReasonSection rs = new ReasonSection(columnId, columnTitle,columnIcon,reasonDAO.getBySection(columnId));
                list.add(rs);

            }while (cursor.moveToNext());

        }
        cursor.close();

        return list;
    }

    public ReasonSection getByTitle(String title) {
        ReasonSection reasonSection = null;

        String query = "SELECT * FROM " + REASON_TABLE_SECTIONS + " WHERE " + REASON_COLUMN_SECTION_TITLE + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{title});

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            int icon = cursor.getInt(2);

            reasonSection = new ReasonSection(id, title, icon, null);
        }

        if (cursor != null) {
            cursor.close();
        }

        return reasonSection;
    }



}
