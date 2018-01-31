package com.survey.school.cleanindiamision;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

import static android.R.attr.data;

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

    public static String[] getSchools(){
        String[] schools = {
                "KENDRIYA VIDYALAYA AFS BAKSHI KA TALAB LUCKNOW",
                "KENDRIYA VIDYALAYA AFS BAREILLY",
                "KENDRIYA VIDYALAYA AFS MEMAURA LUCKNOW",
                "KENDRIYA VIDYALAYA ALIGANJ",
                "KENDRIYA VIDYALAYA AMC",
                "KENDRIYA VIDYALAYA BALRAMPUR",
                "KENDRIYA VIDYALAYA BARABANKI",
                "KENDRIYA VIDYALAYA BHEL JAGDISHPUR",
                "KENDRIYA VIDYALAYA BUDAUN",
                "KENDRIYA VIDYALAYA CRPF LUCKNOW",
                "KENDRIYA VIDYALAYA FAIZABAD",
                "KENDRIYA VIDYALAYA GOMTINAGAR LUCKNOW",
                "KENDRIYA VIDYALAYA HARDOI",
                "KENDRIYA VIDYALAYA IFFCO AONLA BAREILLY",
                "KENDRIYA VIDYALAYA IIM LUCKNOW",
                "KENDRIYA VIDYALAYA IIT KANPUR",
                "KENDRIYA VIDYALAYA IVRI BAREILLY",
                "KENDRIYA VIDYALAYA JRC BAREILLY",
                "KENDRIYA VIDYALAYA KANPUR CANTT",
                "KENDRIYA VIDYALAYA LAKHIMPUR KHERI",
                "KENDRIYA VIDYALAYA LUCKNOW CANTT",
                "KENDRIYA VIDYALAYA MATI AKBARPUR KANPUR DEHAT",
                "KENDRIYA VIDYALAYA NER BAREILLY"
        };
        return schools;
    }
}
