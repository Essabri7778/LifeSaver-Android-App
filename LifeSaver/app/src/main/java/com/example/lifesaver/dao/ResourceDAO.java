package com.example.lifesaver.dao;

import static com.example.lifesaver.db.DatabaseHelper.COLUMN_RESOURCE_DESCRIPTION;
import static com.example.lifesaver.db.DatabaseHelper.COLUMN_RESOURCE_EMAIL;
import static com.example.lifesaver.db.DatabaseHelper.COLUMN_RESOURCE_ID;
import static com.example.lifesaver.db.DatabaseHelper.COLUMN_RESOURCE_LOGO;
import static com.example.lifesaver.db.DatabaseHelper.COLUMN_RESOURCE_NAME;
import static com.example.lifesaver.db.DatabaseHelper.COLUMN_RESOURCE_PHONE;
import static com.example.lifesaver.db.DatabaseHelper.COLUMN_RESOURCE_SMS;
import static com.example.lifesaver.db.DatabaseHelper.COLUMN_RESOURCE_TYPE;
import static com.example.lifesaver.db.DatabaseHelper.COLUMN_RESOURCE_WEBSITE;
import static com.example.lifesaver.db.DatabaseHelper.RESOURCE_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.lifesaver.bo.ResourceBo;
import com.example.lifesaver.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ResourceDAO {
    private SQLiteDatabase db;

    public ResourceDAO(Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean addResource(ResourceBo resource){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_RESOURCE_NAME,resource.getName());
        cv.put(COLUMN_RESOURCE_DESCRIPTION,resource.getDescription());
        cv.put(COLUMN_RESOURCE_PHONE,resource.getPhone());
        cv.put(COLUMN_RESOURCE_SMS,resource.getSms());
        cv.put(COLUMN_RESOURCE_EMAIL,resource.getEmail());
        cv.put(COLUMN_RESOURCE_WEBSITE,resource.getWebsite());
        cv.put(COLUMN_RESOURCE_LOGO,resource.getLogo());
        cv.put(COLUMN_RESOURCE_TYPE,resource.getType());

        long insert = db.insert(RESOURCE_TABLE,null,cv);
        return (insert == -1) ? false : true;
    }

    public List<ResourceBo> getOwnResources(){
        List<ResourceBo> list = new ArrayList<>();
        String query = "SELECT * FROM "+RESOURCE_TABLE+ " WHERE "+ COLUMN_RESOURCE_TYPE + " = ?" ;
        Cursor cursor = db.rawQuery(query, new String[]{"1"});
        if(cursor.moveToFirst()){

            do {
                int columnId = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String phone = cursor.getString(3);
                String sms = cursor.getString(4);
                String email = cursor.getString(5);
                String website = cursor.getString(6);
                String logo = cursor.getString(7);
                int type = cursor.getInt(8);
                ResourceBo sf = new ResourceBo(columnId, name,description,
                        phone, sms,email, website,logo,type);
                list.add(sf);

            }while (cursor.moveToNext());

        }
        cursor.close();
        return list;
    }
    public List<ResourceBo> getBookedResources(){
        List<ResourceBo> list = new ArrayList<>();
        String query = "SELECT * FROM "+RESOURCE_TABLE+ " WHERE "+ COLUMN_RESOURCE_TYPE + " != ?" ;
        Cursor cursor = db.rawQuery(query, new String[]{"1"});
        if(cursor.moveToFirst()){

            do {
                int columnId = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String phone = cursor.getString(3);
                String sms = cursor.getString(4);
                String email = cursor.getString(5);
                String website = cursor.getString(6);
                String logo = cursor.getString(7);
                int type = cursor.getInt(8);
                ResourceBo sf = new ResourceBo(columnId, name,description,
                        phone, sms,email, website,logo,type);
                list.add(sf);

            }while (cursor.moveToNext());

        }
        cursor.close();
        return list;
    }

    // Deleting a single resource ===> unbooked
    public boolean deleteResource(int id){
        int result = db.delete(RESOURCE_TABLE, COLUMN_RESOURCE_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();

        return (result == -1 ) ? false : true;
    }
}
