package com.survey.school.studentsurvey;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import java.util.Map;

public abstract class Utility {
    public static void showMessage(String title, String message, Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public static void showToastMessage(String message, Context context){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public static String getKeyFromValue(Map map, String value) {
        for (Object key : map.keySet()) {
            if (map.get(key).equals(value)) {
                return key.toString();
            }
        }
        return "";
    }
}
