package com.survey.school.studentsurvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowSurveyActivity extends AppCompatActivity {
    ListView lvShowSurvey;
    DatabaseHelper db;
    SurveyListAdapter surveyAdapter;
    ArrayList<Survey> lstSurvey;
    String surveyId = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_survey);

        surveyId = getIntent().getStringExtra("SURVEY_ID");

        lstSurvey = new ArrayList<Survey>();
        db = new DatabaseHelper(getApplicationContext());
        lstSurvey = db.getSurveyDetails(surveyId);
        surveyAdapter = new SurveyListAdapter(getApplicationContext(), lstSurvey);
        lvShowSurvey = (ListView)findViewById(R.id.lvSurvey);
        lvShowSurvey.setAdapter(surveyAdapter);
    }
}
