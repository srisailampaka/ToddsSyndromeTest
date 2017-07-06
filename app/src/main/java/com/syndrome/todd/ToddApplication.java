package com.syndrome.todd;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;


import com.syndrome.todd.sqlite.ToddDBHelper;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by srisailampaka on 06/07/17.
 */
public class ToddApplication extends Application {

    private static ToddApplication mToddApplication;
    public SQLiteDatabase mSqLiteDatabase;


    public static ToddApplication getToddApplication() {
        return mToddApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mToddApplication = this;
        loadLocalDataBase();
        loadCalligraphy();
    }

    private void loadLocalDataBase() {
        ToddDBHelper toddLiteDB = new ToddDBHelper(this, ToddDBHelper.DATABASE_NAME, null, ToddDBHelper.DATABASE_VERSION);
        // it return null if first time database create onCreate() called,
        // once database create it will open the database so openDataBase() called
        mSqLiteDatabase = toddLiteDB.getWritableDatabase();
    }

    private void loadCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getResources().getString(R.string.font_mont_regular))
                .setFontAttrId(R.attr.fontPath).build());
    }
}
