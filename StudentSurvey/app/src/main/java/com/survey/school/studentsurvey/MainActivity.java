package com.survey.school.studentsurvey;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {

    Button admin, studentSurvey, showSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        admin = (Button)findViewById(R.id.btnAdmin);
        studentSurvey = (Button)findViewById(R.id.btnStudentSurvey);
        showSurvey = (Button)findViewById(R.id.btnShow);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminIntent = new Intent(MainActivity.this,AdminActivity.class);
                startActivity(adminIntent);
            }
        });

        studentSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent studentSurveyIntent = new Intent(MainActivity.this, StudentDetailActivity.class);
                startActivity(studentSurveyIntent);
            }
        });

        showSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showStudentsIntent = new Intent(MainActivity.this, ShowStudentsActivity.class);
                startActivity(showStudentsIntent);
            }
        });
    }
}
