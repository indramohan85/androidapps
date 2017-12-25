package com.survey.school.studentsurvey;

import java.io.Serializable;

public class Student implements Serializable {
    private int SurveyId;
    private String StudentName;
    private String StudentClass;
    private String FatherName;
    private String MotherName;
    private int Age;

    public int getSurveyId() { return SurveyId; }

    public void setSurveyId(int surveyId) { SurveyId = surveyId; }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStudentClass() {
        return StudentClass;
    }

    public void setStudentClass(String studentClass) {
        StudentClass = studentClass;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getMotherName() {
        return MotherName;
    }

    public void setMotherName(String motherName) {
        MotherName = motherName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
}
