package com.syndrome.todd.ui.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.neurological.ToddSyndromeLogicChecker;
import com.neurological.model.Patient;
import com.syndrome.todd.R;
import com.syndrome.todd.Utils.Utils;

import java.util.Locale;



/**
 * Created by srisailampaka on 06/07/17.
 */

public class SuccessDetailsActivity extends BaseActivity {

    private static final String TAG = "TestCompleteDialog";
    public static final String KEY_RAW_DATA = "RawData";
    private Patient mPatientsData;
    private Button btnFinish;
    private TextView mResultText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_success);
      btnFinish=(Button)findViewById(R.id.btnFinish);
        mResultText=(TextView)findViewById(R.id.diagnosisResult);
        extractData();
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(MainActivity.KEY_RESULT_DATA, mPatientsData);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //opening transition animations
        Utils.activityOpenBottomTransition(SuccessDetailsActivity.this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Utils.activityCloseBottomTransition(SuccessDetailsActivity.this);
    }


    private void extractData() {
        if (getIntent().hasExtra(KEY_RAW_DATA)) {
            mPatientsData = (Patient) getIntent().getExtras().get(KEY_RAW_DATA);
            if (mPatientsData != null) {
                Log.d(TAG, String.format("Received Intent Data:%s", mPatientsData.toString()));
                mResultText.setText(getDisplayMessage());
            }

        }
    }

    private String getDisplayMessage() {
        Patient diagnosisPatientData = new ToddSyndromeLogicChecker(mPatientsData).calculateResult();
        return String.format(Locale.getDefault(), "Here is %2s Diagnosis result\n%d %% of Probability of syndrome detected.",
                diagnosisPatientData.getPatientName(), diagnosisPatientData.getSyndromePercentage());
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
