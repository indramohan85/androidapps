package com.survey.school.cleanindiamision;

import java.io.Serializable;
import java.util.List;

public class SurveyDetail implements Serializable {
    private float rating;
    private int schoolId;
    private String comment;
    private String schoolName;
    private List<QuestionDetail> lstQuestion;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public List<QuestionDetail> getLstQuestion() {
        return lstQuestion;
    }

    public void setLstQuestion(List<QuestionDetail> lstQuestion) {
        this.lstQuestion = lstQuestion;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

