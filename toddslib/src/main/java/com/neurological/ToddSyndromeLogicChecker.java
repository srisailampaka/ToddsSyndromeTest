package com.neurological;


import com.neurological.enums.Gender;
import com.neurological.model.Patient;
/**
 * Created by srisailampaka on 06/07/17.
 */
public class ToddSyndromeLogicChecker {
    private boolean[] answers;
    private Patient mPatientsData;
    private int baseLineAge;

    public ToddSyndromeLogicChecker(Patient patientsData) {
        this.baseLineAge = 15;
        if (patientsData != null) {
            this.mPatientsData = patientsData;
            answers = new boolean[4];
        }
    }

    public ToddSyndromeLogicChecker(Patient patientsData, int baseLineAge) {
        this.baseLineAge = baseLineAge;
        if (patientsData != null) {
            this.mPatientsData = patientsData;
            answers = new boolean[4];
        }
    }

    public Patient calculateResult() {
        answers[0] = patientAgeCheck();
        answers[1] = checkMigraines();
        answers[2] = checkGender();
        answers[3] = isTackingDrugs();

        int percentage = 0;
        for (boolean r : answers) {
            if (r) {
                percentage = percentage + 25;
            }
        }
        mPatientsData.setSyndromePercentage(percentage);
        return mPatientsData;
    }

    private boolean patientAgeCheck() {
        boolean result = false;
        try {
            if (mPatientsData.getAge() != null || mPatientsData.getAge().length() != 0) {
                //1. People 15 years old or younger are more likely to have it.
                int patientAge = Integer.parseInt(mPatientsData.getAge());
                if (patientAge >= this.baseLineAge) {
                    result = true;
                }
            } else {
                result = false;
            }
        } catch (Exception e) {
            result = false;
        }
        return result;

    }

    private boolean checkMigraines() {
        return mPatientsData.isMigraines();
    }

    private boolean checkGender() {
        return mPatientsData.getGender() == Gender.MALE;
    }

    private boolean isTackingDrugs() {
        return mPatientsData.isIncreasesDrugs();
    }
}

