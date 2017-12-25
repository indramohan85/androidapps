package com.survey.school.studentsurvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    Button question, option, showQuestion, showOptions, clearSurveyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        question = (Button)findViewById(R.id.btnQuestion);
        option = (Button)findViewById(R.id.btnOptions);
        showQuestion = (Button)findViewById(R.id.btnShowQuestion);
        showOptions = (Button) findViewById(R.id.btnShowOptions);
        clearSurveyData = (Button)findViewById(R.id.btnClearSurveyData);

        showQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuestions();
            }
        });

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertQuestionsFromFile();
            }
        });

        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertOptionsFromFile();
            }
        });

        showOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptions();
            }
        });

        clearSurveyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSurveyData();
            }
        });
    }

    private void deleteSurveyData() {
        try{
            DatabaseHelper db = new DatabaseHelper(this);
            String message = db.dropAndCreateTables();
            Utility.showMessage("Survey data", message, this);
        }
        catch (Exception ex){
            Utility.showMessage("Error in AdminActivity", "" + ex.getMessage(), this);
        }
    }

    private void showOptions() {
        ArrayList<String> options = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        try{
            DatabaseHelper db = new DatabaseHelper(this);
            options = db.getOptions();
            if(options.size() > 0) {
                for (String option: options) {
                    sb.append(option + "\n");
                }
                Utility.showMessage("Options in DB", sb.toString(), this);
            }else{
                Utility.showMessage("Options in DB", "No questions found !!", this);
            }
        }
        catch (Exception ex){
            Utility.showMessage("Error in AdminActivity", "" + ex.getStackTrace(), this);
        }
    }

    private void insertOptionsFromFile() {
        DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<String> lstOptions = new ArrayList<String>();
        BufferedReader reader;
        String question = "";
        InputStream stream = this.getResources().openRawResource(R.raw.question);
        try {
            reader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.options), "UTF-16LE"));
            question = reader.readLine();
            while(question != null){
                lstOptions.add(question);
                question = reader.readLine();
            }
            if(db.insertOptions(lstOptions)) {
                Utility.showMessage("Options", "Data inserted !!", this);
            }
        } catch (Exception e) {
            Utility.showMessage("Error in AdminActivity", "" + e.getStackTrace(), this);
        }
    }

    private void showQuestions() {
        DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<String> questions = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        try{
            questions = db.getQuestions();
            if(questions.size() > 0) {
                for (String question: questions) {
                    sb.append(question + "\n");
                }
                Utility.showMessage("Questions in DB", sb.toString(), this);
            }else{
                Utility.showMessage("Questions in DB", "No questions found !!", this);
            }
        }
        catch (Exception ex){
            Utility.showMessage("Error in AdminActivity", "" + ex.getStackTrace(), this);
        }
    }

    private void insertQuestionsFromFile() {
        DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<String> lstQues = new ArrayList<String>();
        BufferedReader reader;
        String question = "";
        InputStream stream = this.getResources().openRawResource(R.raw.question);
        try {
            reader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.question), "UTF-16LE"));
            question = reader.readLine();
            while(question != null){
                lstQues.add(question);
                question = reader.readLine();
            }
            if(db.insertQuestions(lstQues)) {
                Utility.showMessage("Questions", "Data inserted !!", this);
            }
        } catch (Exception e) {
            Utility.showMessage("Error in AdminActivity", "" + e.getStackTrace(), this);
        }
    }
}
