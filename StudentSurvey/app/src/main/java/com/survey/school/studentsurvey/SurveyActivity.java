package com.survey.school.studentsurvey;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SurveyActivity extends AppCompatActivity {
    Cursor questions;
    Map<Integer,String> mapOptions;
    TextView tvQues;
    RadioGroup rgOptions;
    Button btnNext;
    DatabaseHelper db;
    SurveyDetails surveyDetails;
    Student studentDetails;
    ArrayList<Survey> lstSurvey;
    int questionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        questionNumber++;
        studentDetails = new Student();
        studentDetails = (Student) getIntent().getSerializableExtra("STUDENT_OBJECT");

        db = new DatabaseHelper(this);
        mapOptions = new HashMap<Integer,String>();
        surveyDetails = new SurveyDetails();
        lstSurvey = new ArrayList<Survey>();

        tvQues = (TextView)findViewById(R.id.tvSurveyQues);
        rgOptions = (RadioGroup)findViewById(R.id.rgOptions);
        btnNext = (Button)findViewById(R.id.btnNext);

        getOptions();
        getQuestions();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNextSurvey();
            }
        });

        /*if(studentData != null){
            Utility.showMessage(
                            "Student data",
                            "Student name : " + studentData.getStudentName() + "\n" +
                            "Class : " + studentData.getStudentClass() + "\n" +
                            "Age : " + studentData.getAge() + "\n" +
                            "Father name : " + studentData.getFatherName() + "\n" +
                            "Mother name : " + studentData.getMotherName() + "\n",
                            this
            );
        }*/
    }

    private void getQuestions() {
        try {
            questions = db.getQuestionsCursor();
            questions.moveToFirst();
            tvQues.setText(Integer.toString(questionNumber) + " : " + questions.getString(questions.getColumnIndex(Constants.COLUMN_QUES_TEXT)));
        }
        catch (Exception ex){
            Utility.showMessage("Error getting questions", ex.getMessage(), this);
        }
    }

    private void getOptions() {
        mapOptions = db.getOptionsMap();
        RadioButton radioButtonOption;
        int count = 0;
        RadioGroup.LayoutParams radioParams = new RadioGroup.LayoutParams(getBaseContext(), null);
        radioParams.setMargins(0,10,0,10);
        try {
            for (String option : mapOptions.values()) {
                count++;
                radioButtonOption = new RadioButton(this);
                radioButtonOption.setId(count * 100 + count);
                radioButtonOption.setText(option);
                radioButtonOption.setTextSize(20);
                radioButtonOption.setLayoutParams(radioParams);
                rgOptions.addView(radioButtonOption);
            }
        }
        catch (Exception ex){
            Utility.showMessage("Error getting options", ex.getMessage(), this);
        }
    }

    private void setNextSurvey() {
        Survey survey = new Survey();
        int quesId, optionId = 0;
        int radioButtonId;
        RadioButton selectedRadio;
        try {
            if (questions == null) {
                getQuestions();
            } else if (questions.isLast()) {
                btnNext.setText("Submit Survey");
                radioButtonId = rgOptions.getCheckedRadioButtonId();
                if(radioButtonId < 0 ) return;
                surveyDetails.setStudentDetails(studentDetails);
                selectedRadio = (RadioButton) findViewById(radioButtonId);
                optionId = Integer.parseInt(Utility.getKeyFromValue(mapOptions, selectedRadio.getText().toString()));
                quesId = Integer.parseInt(questions.getString(questions.getColumnIndex(Constants.COLUMN_QUES_ID)));
                survey.setSelectedOptionId(optionId);
                survey.setQuestionId(quesId);
                if (!lstSurvey.contains(survey)) {
                    lstSurvey.add(survey);
                }
                surveyDetails.setListSurveyDetails(lstSurvey);
                Utility.showMessage("Survey", db.insertSurvey(surveyDetails), this);
            } else {
                radioButtonId = rgOptions.getCheckedRadioButtonId();
                if(radioButtonId < 0 ) return;
                selectedRadio = (RadioButton) findViewById(radioButtonId);
                optionId = Integer.parseInt(Utility.getKeyFromValue(mapOptions, selectedRadio.getText().toString()));
                quesId = Integer.parseInt(questions.getString(questions.getColumnIndex(Constants.COLUMN_QUES_ID)));
                survey.setSelectedOptionId(optionId);
                survey.setQuestionId(quesId);
                if (!lstSurvey.contains(survey)) {
                    lstSurvey.add(survey);
                }
                questions.moveToNext();
                questionNumber++;
                tvQues.setText(Integer.toString(questionNumber) + " : " + questions.getString(questions.getColumnIndex(Constants.COLUMN_QUES_TEXT)));
                rgOptions.clearCheck();
            }
        }
        catch (Exception ex){
            Utility.showMessage("Error while saving survey !!",ex.getMessage() + " :: " + ex.getStackTrace(), this);
        }
    }
}
