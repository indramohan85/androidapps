package com.survey.school.cleanindiamision;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowSurveyActivity extends AppCompatActivity {

    ListView lvShowSurvey;
    DBHelper db;
    CustomAdapter customAdapter;
    ArrayList<SurveyDetail> lstSurvey;
    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_survey);
        try {
            db = new DBHelper(getApplicationContext());
            lstSurvey = db.getSurveyDetails();

            customAdapter = new CustomAdapter(lstSurvey, getApplicationContext());

            lvShowSurvey = (ListView) findViewById(R.id.lvSurvey);
            lvShowSurvey.setAdapter(customAdapter);

            btnHome = (Button) findViewById(R.id.btnHome);
            btnHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ShowSurveyActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }
        catch (Exception ex){
            Utility.showToastMessage(ex.getMessage(), getApplicationContext());
        }
    }
}
