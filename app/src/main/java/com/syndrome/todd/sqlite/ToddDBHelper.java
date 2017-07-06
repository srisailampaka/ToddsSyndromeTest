package com.syndrome.todd.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by srisailampaka on 06/07/17.
 */

public class ToddDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "ToddPatientsDB";
    // Database Name
    public static final String DATABASE_NAME = "Toddo.db";
    public static final String TABLE_NAME = "Toddo";
    public static int DATABASE_VERSION = 1;
    public static String COLOUMN_PATIENT_ID = "id";
    public static String COLOUMN_PATIENT_NAME = "name";
    public static String COLOUMN_AGE = "age";
    public static String COLOUMN_GENDER = "gender";
    public static String COLOUMN_MIGRAINS = "migrains";
    public static String COLOUMN_DRUGS = "drugs";

    public ToddDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLOUMN_PATIENT_ID + " INTEGER PRIMARY KEY," + COLOUMN_PATIENT_NAME + " TEXT,"
                + COLOUMN_AGE + " TEXT," +COLOUMN_GENDER + " TEXT,"
                + COLOUMN_MIGRAINS+ " TEXT," +COLOUMN_DRUGS+ " TEXT"+")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(sqLiteDatabase);
    }
}
