package com.syndrome.todd.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.neurological.model.Patient;
import com.syndrome.todd.R;
import com.syndrome.todd.ToddApplication;
import com.syndrome.todd.adapters.PatientListAdapter;
import com.syndrome.todd.sqlite.ToddoDb;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by srisailampaka on 06/07/17.
 */

public class MainActivity extends BaseActivity {
    protected static final int REQUEST_FOR_DIAGNOSIS = 1;
    public static final String KEY_RESULT_DATA = "rawdata";
    private LinearLayout mEmptyView;
    private FloatingActionButton mFabButton;
    private RecyclerView mRecyclerView;
    private ToddoDb mToddoDb;
    private PatientListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToddoDb=new ToddoDb();
        mFabButton=(FloatingActionButton)findViewById(R.id.floating_button);
        mRecyclerView=(RecyclerView)findViewById(R.id.patientList) ;
        mEmptyView=(LinearLayout)findViewById(R.id.emptyView);
        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(MainActivity.this, NewPatientActivity.class);
                startActivityForResult(mIntent, REQUEST_FOR_DIAGNOSIS);
            }
        });
        fetchAllResultData();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_FOR_DIAGNOSIS)
        {
            if (resultCode == RESULT_OK) {
                extractIntentData(data);
            } else {
                Snackbar.make(mFabButton,  "Cancel Diagnosis test!", Snackbar.LENGTH_SHORT).show();
            }
        }

    }

    private void extractIntentData(Intent intentData) {
        if (intentData != null && intentData.hasExtra(KEY_RESULT_DATA)) {
            Patient nwPatientData = (Patient) intentData.getExtras().get(KEY_RESULT_DATA);
            if (nwPatientData != null) {
                boolean isSaved = mToddoDb.insetPatientDetails(nwPatientData);
                if (isSaved) {
                    if ( adapter!= null)
                        adapter.addPatientItemOnTop(nwPatientData);
                }

            }

        }


    }
    private void fetchAllResultData() {
        List<Patient> patientsDataList = mToddoDb.getAllPatientDetails();
        if (patientsDataList != null && patientsDataList.size() > 0) {
            mEmptyView.setVisibility(View.GONE);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
             adapter=new PatientListAdapter();
            adapter.addPatientDataSet(patientsDataList);
            mRecyclerView.setAdapter(adapter);
        } else {
            mEmptyView.setVisibility(View.VISIBLE);
        }
    }
}
