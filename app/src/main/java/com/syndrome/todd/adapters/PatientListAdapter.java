package com.syndrome.todd.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.neurological.ToddSyndromeLogicChecker;
import com.neurological.model.Patient;
import com.syndrome.todd.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by srisailampaka on 06/07/17.
 */

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.PatientListViewHolder> {
    public List<Patient> mPatientList=new ArrayList<>();
    private final static int FADE_DURATION = 1000;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;



    public void addPatientDataSet(List<Patient> dataset) {
        if (mPatientList.size() > 0) {
            mPatientList.clear();
        }
        mPatientList.addAll(dataset);
        notifyDataSetChanged();
    }

    public void addPatientItemOnTop(Patient patientsData) {
        if (mPatientList.size() > 0) {
            Collections.reverse(mPatientList);
            mPatientList.add(patientsData);
            Collections.reverse(mPatientList);
            lastPosition = -1;
        } else {
            mPatientList.add(patientsData);
        }
        notifyItemInserted(0);
    }


    @Override
    public PatientListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_patient, parent, false);

        return new PatientListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PatientListViewHolder holder, int position) {
    holder.mPatientName.setText(getDisplayMessage(mPatientList.get(position)));
        setAnimation(holder.mPatientName, position);
    }

    @Override
    public int getItemCount() {
        return mPatientList.size();
    }

    public class PatientListViewHolder extends RecyclerView.ViewHolder {
       public TextView mPatientName;

        public PatientListViewHolder(View view) {
            super(view);
            mPatientName=(TextView)view.findViewById(R.id.patient_name);

        }
    }
    private String getDisplayMessage(Patient mPatientsData) {
        Patient diagnosisPatientData = new ToddSyndromeLogicChecker(mPatientsData).calculateResult();
        return String.format(Locale.getDefault(), "Here is %2s Diagnosis result\n%d %% of Probability of syndrome detected.",
                diagnosisPatientData.getPatientName(), diagnosisPatientData.getSyndromePercentage());
    }
    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            setScaleAnimation(viewToAnimate);

            lastPosition = position;
        }
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

}
