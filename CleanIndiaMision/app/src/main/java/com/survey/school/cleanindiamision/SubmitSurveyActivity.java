package com.survey.school.cleanindiamision;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubmitSurveyActivity extends AppCompatActivity {

    SurveyDetail surveyDetail;
    TextView message;
    EditText comment;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_survey);

        surveyDetail = new SurveyDetail();
        surveyDetail = (SurveyDetail) getIntent().getSerializableExtra("SURVEY_DETAIL_OBJECT");

        message = (TextView) findViewById(R.id.tvMessage);
        comment = (EditText) findViewById(R.id.edComment);
        btnSubmit = (Button) findViewById(R.id.btnSubmitSurvey);

        String messageToUser = "Thank you !!!\n\nFinal rating of school : " + surveyDetail.getSchoolName() + " is " + Float.toString(surveyDetail.getRating());

        message.setText(messageToUser);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitSurvey(surveyDetail, message);
            }
        });

        Utility.showToastMessage("Rating : " + Float.toString(surveyDetail.getRating()), getApplicationContext());
    }

    private void submitSurvey(SurveyDetail surveyDetail, TextView message) {
        try{
            DBHelper db = new DBHelper(getApplicationContext());
            surveyDetail.setComment(comment.getText().toString().trim());
            String msg = db.insertSurvey(surveyDetail);
            Utility.showToastMessage(msg, getApplicationContext());
            Intent showSurvey = new Intent(SubmitSurveyActivity.this, ShowSurveyActivity.class);
            startActivity(showSurvey);
        }catch (Exception ex){
            Utility.showToastMessage(ex.getMessage(), getApplicationContext());
        }
    }
}
