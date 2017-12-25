package com.survey.school.studentsurvey;

import java.util.ArrayList;

public class SurveyDetails {
    private Student StudentDetails;
    private ArrayList<Survey> ListSurveyDetails;

    public Student getStudentDetails() {
        return StudentDetails;
    }

    public void setStudentDetails(Student studentDetails) {
        StudentDetails = studentDetails;
    }

    public ArrayList<Survey> getListSurveyDetails() {
        return ListSurveyDetails;
    }

    public void setListSurveyDetails(ArrayList<Survey> listSurveyDetails) {
        ListSurveyDetails = listSurveyDetails;
    }
}
