package com.survey.school.cleanindiamision;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectSchoolActivity extends AppCompatActivity {

    Spinner spSchool;
    Button btnStartSurvey;
    int schoolId = 0;
    String schoolName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_select_school);

            spSchool = (Spinner) findViewById(R.id.spinnerSchool);
            btnStartSurvey = (Button) findViewById(R.id.btnStartSurvey);

            btnStartSurvey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(schoolId == 0) {
                        Utility.showToastMessage("Select a school before continuing...", getApplicationContext());
                        return;
                    }
                    startSurvey();
                }
            });

            DBHelper db = new DBHelper(getApplicationContext());
            ArrayList<School> lstSchool = new ArrayList<>();
            lstSchool = db.getSchoolsWithId();

            ArrayAdapter<School> adapter = new ArrayAdapter<School>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, lstSchool);
            adapter.setDropDownViewResource(R.layout.spinner_item);

            spSchool.setAdapter(adapter);
            //spSchool.setSelection(adapter.getPosition(myItem));//Optional to set the selected item.

            spSchool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    School school = (School) parent.getSelectedItem();
                    schoolId = Integer.parseInt(school.getSchoolId());
                    schoolName = school.getSchoolName();
                    // Notify the selected item text
                    /*Toast.makeText
                            (getApplicationContext(), "Selected : id : " + school.getSchoolId() + ", Name : " + school.getSchoolName(), Toast.LENGTH_SHORT)
                            .show();*/
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }catch (Exception ex){
            Utility.showMessage("Error in select school activity", "" + ex.getStackTrace(), this);
        }
    }

    private void startSurvey() {
        Intent intent = new Intent(SelectSchoolActivity.this, SurveyActivity.class);
        intent.putExtra("SCHOOL_ID", Integer.toString(schoolId));
        intent.putExtra("SCHOOL_NAME", schoolName);
        startActivity(intent);
    }
}
