package com.survey.school.cleanindiamision;

import java.io.Serializable;

public class QuestionDetail implements Serializable {
    private int questionId;
    private float rating;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
