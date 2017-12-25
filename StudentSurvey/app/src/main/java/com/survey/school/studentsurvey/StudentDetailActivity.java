package com.survey.school.studentsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentDetailActivity extends AppCompatActivity {

    EditText name, studentClass, age, fatherName, motherName;
    Button startSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        name = (EditText)findViewById(R.id.edName);
        studentClass = (EditText)findViewById(R.id.edClass);
        age = (EditText)findViewById(R.id.edAge);
        fatherName = (EditText)findViewById(R.id.edFather);
        motherName = (EditText)findViewById(R.id.edMother);

        startSurvey = (Button)findViewById(R.id.btnStartSurvey);

        startSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStudentSurvey();
            }
        });
    }

    private void startStudentSurvey() {
        String stuName, stuClass, stuFather, stuMother;
        int stuAge;

        stuName = name.getText().toString().trim();
        stuClass = studentClass.getText().toString().trim();
        stuFather = fatherName.getText().toString().trim();
        stuMother = motherName.getText().toString().trim();
        stuAge = Integer.parseInt(age.getText().toString().trim());

        Student studentDetail = new Student();
        studentDetail.setStudentName(stuName);
        studentDetail.setFatherName(stuFather);
        studentDetail.setMotherName(stuMother);
        studentDetail.setStudentClass(stuClass);
        studentDetail.setAge(stuAge);

        Intent studentIntent = new Intent(StudentDetailActivity.this, SurveyActivity.class);
        studentIntent.putExtra("STUDENT_OBJECT", studentDetail);
        startActivity(studentIntent);
    }
}
