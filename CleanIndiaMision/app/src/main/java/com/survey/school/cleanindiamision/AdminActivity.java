package com.survey.school.cleanindiamision;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    Button btnAddQues, btnShowQues, btnAddSchool, btnShowSchool, btnCreateTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin);

            btnAddQues = (Button) findViewById(R.id.btnAddQuestion);
            btnShowQues = (Button) findViewById(R.id.btnShowQuestion);
            btnAddSchool = (Button) findViewById(R.id.btnAddSchool);
            btnShowSchool = (Button) findViewById(R.id.btnShowSchool);
            btnCreateTable = (Button) findViewById(R.id.btnDropCreateTable);

            btnAddQues.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addQuestion();
                }
            });

            btnShowQues.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showQuestion();
                }
            });

            btnAddSchool.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addSchool();
                }
            });

            btnShowSchool.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSchool();
                }
            });

            btnCreateTable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dropAndCreateTables();
                }
            });
        }
        catch (Exception ex){
            Utility.showToastMessage(ex.getMessage(), getApplicationContext());
        }
    }

    private void dropAndCreateTables() {
        try{
            DBHelper db = new DBHelper(this);
            String message = db.dropAndCreateTables();
            Utility.showMessage("Table creation", message, this);
        }catch (Exception ex){
            Utility.showMessage("Table creation", "" + ex.getStackTrace(), this);
        }
    }

    private void showSchool() {
        DBHelper db = new DBHelper(this);
        ArrayList<String> schools = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        try{
            schools = db.getSchools();
            if(schools.size() > 0) {
                for (String school: schools) {
                    sb.append(school + "\n");
                }
                Utility.showMessage("Schools in DB", sb.toString(), this);
            }else{
                Utility.showMessage("Schools in DB", "No schools found !!", this);
            }
        }
        catch (Exception ex){
            Utility.showMessage("Error showing schools", "" + ex.getStackTrace(), this);
        }
    }

    private void addSchool() {
        try{
            DBHelper db = new DBHelper(this);
            if(db.insertSchool(Utility.getSchools())) {
                Utility.showMessage("Schools", "Data inserted !!", this);
            }
        } catch (Exception e) {
            Utility.showMessage("Error adding school", "" + e.getStackTrace(), this);
        }
    }

    private void showQuestion() {
        DBHelper db = new DBHelper(this);
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
            Utility.showMessage("Error showing questions", "" + ex.getStackTrace(), this);
        }
    }

    private void addQuestion() {
        try{
            DBHelper db = new DBHelper(this);
            if(db.insertQuestions()) {
                Utility.showMessage("Questions", "Data inserted !!", this);
            }
        } catch (Exception e) {
            Utility.showMessage("Error in AdminActivity", "" + e.getStackTrace(), this);
        }
    }
}
