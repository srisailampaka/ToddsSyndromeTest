package com.syndrome.todd.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.neurological.enums.Gender;
import com.neurological.model.Patient;
import com.syndrome.todd.R;
import com.syndrome.todd.Utils.Utils;
import com.syndrome.todd.sqlite.ToddoDb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by srisailampaka on 06/07/17.
 */

public class NewPatientActivity extends BaseActivity implements View.OnClickListener{

    private AppCompatEditText mPatientName;
    private AppCompatEditText mAge;
    private Button mSaveButton;
    private Button mCancelButton;
    private SwitchCompat mSwitchMigraines;
    private SwitchCompat mSwitchDrugs;
    private RadioButton mRadioButton;
    private ToddoDb mToddoDb;
    private Patient patient;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient);
        ButterKnife.bind(this);
        mToddoDb = new ToddoDb();
        mPatientName = (AppCompatEditText) findViewById(R.id.patient_name);
        mAge = (AppCompatEditText) findViewById(R.id.age_edit_text);
        mSaveButton = (Button) findViewById(R.id.btnSave);
        mCancelButton = (Button) findViewById(R.id.btnCancel);
        mSwitchDrugs = (SwitchCompat) findViewById(R.id.switchdrugs);
        mSwitchMigraines = (SwitchCompat) findViewById(R.id.switchMigraines);
        mRadioButton=(RadioButton)findViewById(R.id.btnMale);


        mSaveButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);
    }

 private void loadThePatientDetails(){
        patient=new Patient();
        patient.setPatientName(mPatientName.getText().toString().trim());
        patient.setAge((mAge.getText().toString().trim()));
        Gender genderType = mRadioButton.isChecked() ? Gender.MALE : Gender.FEMALE;
        patient.setGender(genderType);
        patient.setMigraines(mSwitchMigraines.isChecked());
        patient.setIncreasesDrugs(mSwitchDrugs.isChecked());
    }



    @Override
    protected void onResume() {
        super.onResume();
        //opening transition animations
        Utils.activityOpenBottomTransition(NewPatientActivity.this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Utils.activityCloseBottomTransition(NewPatientActivity.this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnSave:
                if(!TextUtils.isEmpty(mPatientName.getText().toString().trim())&&!TextUtils.isEmpty(mAge.getText().toString().trim()))
                { loadThePatientDetails();
                  startSuccessCompleteActivity(patient);
                finish();}
                else
                {
                    Toast.makeText(getApplicationContext(),"Please enter minimum PatientName and Age",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnCancel:
                finish();
                break;
        }



    }
    public  void startSuccessCompleteActivity( Patient mPatientsData) {
        Intent intent= new Intent(NewPatientActivity.this, SuccessDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        intent.putExtra(SuccessDetailsActivity.KEY_RAW_DATA, mPatientsData);
        startActivity(intent);
    }
}




