package com.survey.school.studentsurvey;

public class Survey {
    private int QuestionId;
    private String Question;
    private String SelectedOption;
    private int SelectedOptionId;

    public String getQuestion() { return Question; }

    public void setQuestion(String question) { Question = question; }

    public String getSelectedOption() { return SelectedOption; }

    public void setSelectedOption(String selectedOption) { SelectedOption = selectedOption; }

    public int getQuestionId() { return QuestionId; }

    public void setQuestionId(int questionId) {
        QuestionId = questionId;
    }

    public int getSelectedOptionId() {
        return SelectedOptionId;
    }

    public void setSelectedOptionId(int selectedOptionId) {
        SelectedOptionId = selectedOptionId;
    }
}
