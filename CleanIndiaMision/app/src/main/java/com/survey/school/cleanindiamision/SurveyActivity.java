package com.survey.school.cleanindiamision;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

public class SurveyActivity extends AppCompatActivity {

    int schoolId = 0;
    String schoolName = "";
    TextView tvQues;
    Button btnNext;
    RatingBar rbRating;
    DBHelper db;
    Cursor questions;
    int questionNumber = 0;
    ArrayList<QuestionDetail> lstQues;
    SurveyDetail surveyDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_survey);

            schoolId = Integer.parseInt(getIntent().getStringExtra("SCHOOL_ID").toString());
            schoolName = getIntent().getStringExtra("SCHOOL_NAME").toString();
            questionNumber++;

            tvQues = (TextView) findViewById(R.id.tvQuestion);
            btnNext = (Button) findViewById(R.id.btnNext);
            rbRating = (RatingBar) findViewById(R.id.rbUserRating);
            rbRating.setRating(0);
            rbRating.setNumStars(5);
            rbRating.setStepSize(1.0f);

            lstQues = new ArrayList<QuestionDetail>();
            surveyDetail = new SurveyDetail();

            db = new DBHelper(getApplicationContext());
            getQuestions();

            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setNextSurvey();
                }
            });
        }catch (Exception ex){
            Utility.showMessage("Error launching survey", ex.getMessage(), this);
        }
    }

    //Function to get questions from db and showing on UI
    private void getQuestions() {
        try {
            questions = db.getQuestionsCursor();
            questions.moveToFirst();
            tvQues.setText(Integer.toString(questionNumber) + " : " + questions.getString(questions.getColumnIndex(Constants.COLUMN_QUES_TEXT)));
            tvQues.setTag(questions.getString(questions.getColumnIndex(Constants.COLUMN_QUES_ID)));
        }
        catch (Exception ex){
            Utility.showMessage("Error getting questions", ex.getMessage(), this);
        }
    }

    //Function to set next survey question on UI
    private void setNextSurvey() {
        int radioButtonId;
        try {
            if (questions == null) {
                getQuestions();
            } else if (rbRating.getRating() == 0f){
                Utility.showToastMessage("Please select a rating...", getApplicationContext());
                return;
            } else if (questions.isLast()) {
                //TODO :launch next activity
                addSurveyObjectToList(rbRating.getRating(), true);
            } else {
                questions.moveToNext();
                questionNumber++;
                tvQues.setText(Integer.toString(questionNumber) + " : " + questions.getString(questions.getColumnIndex(Constants.COLUMN_QUES_TEXT)));
                tvQues.setTag(questions.getString(questions.getColumnIndex(Constants.COLUMN_QUES_ID)));
                addSurveyObjectToList(rbRating.getRating(), false);
                rbRating.setRating(0f);
            }
        }
        catch (Exception ex){
            Utility.showMessage("Error while saving survey !!",ex.getMessage() + " :: " + ex.getStackTrace(), this);
        }
    }

    //Function to add survey class object to list
    private void addSurveyObjectToList(float rating, boolean isLastQuestion) {
        QuestionDetail ques = new QuestionDetail();
        ques.setRating(rating);
        ques.setQuestionId(Integer.parseInt(questions.getString(questions.getColumnIndex(Constants.COLUMN_QUES_ID))));
        lstQues.add(ques);
        surveyDetail.setSchoolId(schoolId);
        if (!lstQues.contains(ques)) {
            lstQues.add(ques);
        }
        if(isLastQuestion) {
            float totalRating = 0;
            float avgRating = 0;
            for (int i = 0; i < lstQues.size(); i++){
                totalRating = totalRating + lstQues.get(i).getRating();
            }
            avgRating = totalRating / lstQues.size();
            surveyDetail.setRating(avgRating);
            surveyDetail.setSchoolId(schoolId);
            surveyDetail.setSchoolName(schoolName);
            surveyDetail.setLstQuestion(lstQues);

            Intent intent = new Intent(SurveyActivity.this, SubmitSurveyActivity.class);
            intent.putExtra("SURVEY_DETAIL_OBJECT", surveyDetail);
            startActivity(intent);
        }
    }
}
