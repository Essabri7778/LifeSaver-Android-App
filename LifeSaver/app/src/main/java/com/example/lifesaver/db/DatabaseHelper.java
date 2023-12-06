package com.example.lifesaver.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "life_saver.db";
    private static final int DATABASE_VERSION = 1;


    // COLUMNS OF TABLE SAFETY_PLAN
    public static final String SAFETY_PLAN_TABLE = "SAFETY_PLAN";
    public static final String COLUMN_SAFETY_ID = "SAFETY_ID";
    public static final String COLUMN_SAFETY_RESPONSE = "SAFETY_RESPONSE";


    public static final String CONTACT_TABLE = "CONTACT";
    public static final String COLUMN_CONTACT_ID = "CONTACT_ID";
    public static final String COLUMN_CONTACT_IDENTIFIER = "CONTACT_IDENTIFIER";
    public static final String COLUMN_CONTACT_PRIMARY_NAME = "CONTACT_PRIMARY_NAME";
    public static final String COLUMN_CONTACT_ALTERNATIVE_NAME = "CONTACT_ALTERNATIVE_NAME";
    public static final String COLUMN_CONTACT_PHONE_NUMBER = "CONTACT_PHONE_NUMBER";
    public static final String COLUMN_CONTACT_PHOTO_URI = "CONTACT_PHOTO_URI";




    // reason section table columns
    public static final String REASON_COLUMN_SECTION_ID = "REASON_SECTIONS_ID";
    public static final String REASON_COLUMN_SECTION_TITLE = "REASON_SECTIONS_TITLE";
    public static final String REASON_COLUMN_SECTION_ICON = "REASON_SECTIONS_ICON";
    public static final String REASON_TABLE_SECTIONS = "REASON_SECTIONS";

    // reason table columns
    public static final String REASON_TABLE = "REASON";
    public static final String REASON_COLUMN_TEXT = "REASON_TEXT";
    public static final String REASON_COLUMN_ID = "REASON_ID";
    public static final String REASON_COLUMN_CHECKED = "REASON_CHECKED";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create TABLE SAFETY_PLAN
        String createSafetyStatement = "CREATE TABLE " + SAFETY_PLAN_TABLE + " (" + COLUMN_SAFETY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SAFETY_RESPONSE + " TEXT)";

        db.execSQL(createSafetyStatement);


        // Create TABLE CONTACT
        String createContactStatement = "CREATE TABLE " + CONTACT_TABLE + " (" + COLUMN_CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                                               + COLUMN_CONTACT_IDENTIFIER + " TEXT, "
                                                                               + COLUMN_CONTACT_PRIMARY_NAME + " TEXT, "
                                                                               + COLUMN_CONTACT_ALTERNATIVE_NAME + " TEXT, "
                                                                               + COLUMN_CONTACT_PHONE_NUMBER + " TEXT, "
                                                                               + COLUMN_CONTACT_PHOTO_URI + " TEXT)";

        db.execSQL(createContactStatement);

        // Create TABLE 2

        // reason section table
        String createSectionsReasonQuery = "CREATE TABLE " + REASON_TABLE_SECTIONS + " (" + REASON_COLUMN_SECTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + REASON_COLUMN_SECTION_TITLE + " TEXT, " + REASON_COLUMN_SECTION_ICON + " INTEGER)";

        db.execSQL(createSectionsReasonQuery);


        String createReasonQuery = "CREATE TABLE " + REASON_TABLE + " (" +
                REASON_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                REASON_COLUMN_TEXT + " TEXT, " +
                REASON_COLUMN_CHECKED + " INTEGER, " +
                REASON_COLUMN_SECTION_ID + " INTEGER, " +
                "FOREIGN KEY(" + REASON_COLUMN_SECTION_ID + ") REFERENCES " +
                REASON_TABLE_SECTIONS + "(" + REASON_COLUMN_SECTION_ID + "))";

        db.execSQL(createReasonQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}
