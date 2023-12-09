package com.example.lifesaver.dao;

import static com.example.lifesaver.db.DatabaseHelper.COLUMN_CONTACT_ALTERNATIVE_NAME;
import static com.example.lifesaver.db.DatabaseHelper.COLUMN_CONTACT_ID;
import static com.example.lifesaver.db.DatabaseHelper.COLUMN_CONTACT_IDENTIFIER;
import static com.example.lifesaver.db.DatabaseHelper.COLUMN_CONTACT_PHONE_NUMBER;
import static com.example.lifesaver.db.DatabaseHelper.COLUMN_CONTACT_PHOTO_URI;
import static com.example.lifesaver.db.DatabaseHelper.COLUMN_CONTACT_PRIMARY_NAME;
import static com.example.lifesaver.db.DatabaseHelper.CONTACT_TABLE;
import static com.example.lifesaver.db.DatabaseHelper.REASON_COLUMN_CHECKED;
import static com.example.lifesaver.db.DatabaseHelper.REASON_COLUMN_ID;
import static com.example.lifesaver.db.DatabaseHelper.REASON_COLUMN_SECTION_TITLE;
import static com.example.lifesaver.db.DatabaseHelper.REASON_TABLE;
import static com.example.lifesaver.db.DatabaseHelper.REASON_TABLE_SECTIONS;
import static com.example.lifesaver.db.DatabaseHelper.SAFETY_PLAN_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;


import com.example.lifesaver.R;
import com.example.lifesaver.bo.ContactBo;
import com.example.lifesaver.bo.ReasonSection;
import com.example.lifesaver.bo.SafetyPlanBo;
import com.example.lifesaver.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    private SQLiteDatabase db;

    public ContactDAO(Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        if(getAllContacts().size()==0) initiateEmergency();
    }

    public boolean addContact(ContactBo contact){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CONTACT_IDENTIFIER,contact.getIdentifier());
        cv.put(COLUMN_CONTACT_PRIMARY_NAME,contact.getPrimaryName());
        cv.put(COLUMN_CONTACT_ALTERNATIVE_NAME,contact.getAlternativeName());
        cv.put(COLUMN_CONTACT_PHONE_NUMBER,contact.getPhoneNumber());
        cv.put(COLUMN_CONTACT_PHOTO_URI,contact.getPhoto().toString());

        long insert = db.insert(CONTACT_TABLE,null,cv);
        if(insert == -1) {
            return false;
        }
        return true;

    }

    public List<ContactBo> getAllContacts(){
        List<ContactBo> list = new ArrayList<>();
        String query = "SELECT * FROM "+ CONTACT_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){

            do {
                int columnId = cursor.getInt(0);
                String contactId = cursor.getString(1);
                String firstName = cursor.getString(2);
                String secondName = cursor.getString(3);
                String phoneNbr = cursor.getString(4);
                String uri = cursor.getString(5);
                ContactBo sf = new ContactBo(columnId, contactId,firstName,secondName, Uri.parse(uri),phoneNbr);
                list.add(sf);

            }while (cursor.moveToNext());

        }
        cursor.close();

        return list;
    }
    // Deleting single contact
    public boolean deleteContact(int id){
        int result = db.delete(CONTACT_TABLE, COLUMN_CONTACT_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();

        return (result == -1 ) ? false : true;
    }


    //all code below is for the main emergency button
    private void initiateEmergency() {

        int resourceId = R.drawable.appicon;
        Uri resourceUri = Uri.parse("android.resource://com.example.lifesaver/" + resourceId);

        addContact(new ContactBo("","myHopeEmergency",
                "myHopeEmergency", resourceUri,"066"));
    }

    public ContactBo getByName(String name) {
        ContactBo contactBo = null;

        String query = "SELECT * FROM " + CONTACT_TABLE + " WHERE " + COLUMN_CONTACT_PRIMARY_NAME + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{name});

        if (cursor != null && cursor.moveToFirst()) {
            int columnId = cursor.getInt(0);
            String contactId = cursor.getString(1);
            String firstName = cursor.getString(2);
            String secondName = cursor.getString(3);
            String phoneNbr = cursor.getString(4);
            String uri = cursor.getString(5);

            contactBo = new ContactBo(columnId, contactId,firstName,secondName, Uri.parse(uri),phoneNbr);
        }

        if (cursor != null) {
            cursor.close();
        }

        return contactBo;
    }

    public int updatePhoneNum(int contactId, String number) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTACT_PHONE_NUMBER, number);

        String whereClause = COLUMN_CONTACT_ID + "=?";
        String[] whereArgs = {String.valueOf(contactId)};

        return db.update(CONTACT_TABLE, values, whereClause, whereArgs);
    }

}
