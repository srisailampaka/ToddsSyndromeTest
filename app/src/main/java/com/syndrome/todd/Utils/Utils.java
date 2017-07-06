package com.syndrome.todd.Utils;

import android.app.Activity;

import com.syndrome.todd.R;


/**
 * Created by srisailampaka on 06/07/17.
         */

public class Utils {

    public static void activityOpenBottomTransition(Activity activity) {
        if (activity != null) {
            activity.overridePendingTransition(R.anim.slide_up, R.anim.stay_its);
        }
    }

    public static void activityCloseBottomTransition(Activity activity) {
        if (activity != null) {
            activity.overridePendingTransition(R.anim.stay_its, R.anim.slide_down);
        }
    }
}
