package com.survey.school.studentsurvey;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import static android.R.attr.label;

public class ShowStudentsActivity extends AppCompatActivity {

    private ListView lvStudents;
    private StudentListAdapter studentAdapter;
    private ArrayList<Student> students;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_students);
        students = new ArrayList<Student>();
        try {
            db = new DatabaseHelper(this);
            students = db.getStudentDetail();
            if(students.size() == 0){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("Student survey");
                builder.setMessage("No survey found !!");
                builder.show();
                //Utility.showMessage("Student survey", "No survey found !!", getApplicationContext());
                Intent intent = new Intent(ShowStudentsActivity.this, MainActivity.class);
                startActivity(intent);
            }
            studentAdapter = new StudentListAdapter(getApplicationContext(), students);

            lvStudents = (ListView) findViewById(R.id.lvStudent);
            lvStudents.setAdapter(studentAdapter);

            lvStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent showSurveyIntent = new Intent(ShowStudentsActivity.this, ShowSurveyActivity.class);
                    showSurveyIntent.putExtra("SURVEY_ID", view.getTag().toString());
                    startActivity(showSurveyIntent);
                    //Utility.showToastMessage("Survey id : " + view.getTag().toString(),getApplicationContext());
                }
            });
        }
        catch (Exception ex){
            Utility.showToastMessage(ex.getMessage() + " :: " + ex.getStackTrace(),getApplicationContext());
        }
    }
}
