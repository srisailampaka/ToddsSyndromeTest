package com.syndrome.todd.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.neurological.enums.Gender;
import com.neurological.model.Patient;
import com.syndrome.todd.ToddApplication;
import java.util.ArrayList;

/**
 * Created by srisailampaka on 06/07/17.
 */

public class ToddoDb {

    //insert
    public boolean insetPatientDetails(Patient patient)
    {
        SQLiteDatabase db=ToddApplication.getToddApplication().mSqLiteDatabase;
        ContentValues contentValues=new ContentValues();
        contentValues.put(ToddDBHelper.COLOUMN_PATIENT_NAME,patient.getPatientName());
        contentValues.put(ToddDBHelper.COLOUMN_AGE,patient.getAge());
        contentValues.put(ToddDBHelper.COLOUMN_GENDER,patient.getGender() == Gender.MALE ? 0:1);
        contentValues.put(ToddDBHelper.COLOUMN_MIGRAINS,patient.isMigraines());
        contentValues.put(ToddDBHelper.COLOUMN_DRUGS,patient.getIncreasesDrugs());
         return (db.insert(ToddDBHelper.TABLE_NAME,null,contentValues)!=-1)?true:false;

    }


   //read
   public ArrayList<Patient> getAllPatientDetails() {
       ArrayList<Patient> contactList = new ArrayList<Patient>();
       // Select All Query
       String selectQuery = "SELECT  * FROM " + ToddDBHelper.TABLE_NAME;

       SQLiteDatabase db = ToddApplication.getToddApplication().mSqLiteDatabase;
       Cursor cursor = db.rawQuery(selectQuery, null);

       // looping through all rows and adding to list
       if (cursor.moveToFirst()) {
           do {
               Patient patient = new Patient();
               patient.setPatientName(cursor.getString(0));
               patient.setAge(cursor.getString(1));
               patient.setGender((cursor.getInt(2)==0)? Gender.MALE:Gender.FEMALE);
               patient.setIncreasesDrugs((cursor.getInt(3)==1)?true:false);
               patient.setMigraines((cursor.getInt(4)==1)?true:false);

               // Adding contact to list
               contactList.add(patient);
           } while (cursor.moveToNext());
       }

       // return contact list
       return contactList;
   }
}
