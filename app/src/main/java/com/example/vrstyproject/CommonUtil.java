package com.example.vrstyproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class CommonUtil {


    public static void goToNextActivity(Context context, final Class<? extends Activity> targetActivity) {
        Intent in = new Intent(context, targetActivity);
        context.startActivity(in);
    }
}
