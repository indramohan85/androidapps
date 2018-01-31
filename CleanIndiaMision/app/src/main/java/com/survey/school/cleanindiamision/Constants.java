package com.survey.school.cleanindiamision;

public abstract class Constants {
    public static final String DATABASE_NAME = "cleanindia";
    public static final String TABLE_NAME_QUES = "tbl_Question";
    public static final String COLUMN_QUES_ID = "question_id";
    public static final String COLUMN_QUES_TEXT = "question_value";

    public static final String TABLE_NAME_SCHOOL = "tbl_School";
    public static final String COLUMN_SCHOOL_ID = "school_id";
    public static final String COLUMN_SCHOOL_NAME = "school_name";

    public static final String TABLE_NAME_SURVEY = "tbl_Survey";
    public static final String COLUMN_SURVEY_ID = "survey_id";
    public static final String COLUMN_SURVEY_SCHOOL_ID = "survey_school_id";
    public static final String COLUMN_SURVEY_DATE = "survey_date";
    public static final String COLUMN_SURVEY_COMMENT = "survey_comment";
    public static final String COLUMN_SURVEY_RATING = "survey_rating";

    public static final String TABLE_NAME_SURVEY_DETAILS = "tbl_SurveyDetails";
    public static final String COLUMN_SURVEY_DETAILS_ID = "surveydetails_id";
    public static final String COLUMN_SURVEY_DETAILS_SURVEY_ID = "surveydetails_survey_id";
    public static final String COLUMN_SURVEY_DETAILS_QUES_ID = "surveydetails_question_id";
    public static final String COLUMN_SURVEY_DETAILS_RATING = "surveydetails_rating";

    public static String INSERT_QUES = "INSERT INTO "+ TABLE_NAME_QUES +"("+ COLUMN_QUES_TEXT +") VALUES ('%s')";

    public static String INSERT_SCHOOL = "INSERT INTO "+ TABLE_NAME_SCHOOL +"("+ COLUMN_SCHOOL_NAME +") VALUES ('%s')";

    public static  String INSERT_SURVEY = "INSERT INTO "+ TABLE_NAME_SURVEY +"("+ COLUMN_SURVEY_SCHOOL_ID +"," + COLUMN_SURVEY_COMMENT + "," + COLUMN_SURVEY_RATING + ") VALUES ('%d','%s','%.2f')";

    public static  String INSERT_SURVEY_DETAILS = "INSERT INTO "+ TABLE_NAME_SURVEY_DETAILS +"("+ COLUMN_SURVEY_DETAILS_SURVEY_ID +"," + COLUMN_SURVEY_DETAILS_QUES_ID + "," + COLUMN_SURVEY_DETAILS_RATING + ") VALUES ('%d','%d','%.2f')";

    public static String CREATE_TABLE_QUES = "CREATE TABLE "+ TABLE_NAME_QUES +" ("+ COLUMN_QUES_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLUMN_QUES_TEXT +" TEXT)";
    public static String CREATE_TABLE_SCHOOL = "CREATE TABLE "+ TABLE_NAME_SCHOOL +" ("+ COLUMN_SCHOOL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLUMN_SCHOOL_NAME +" TEXT)";
    public static String CREATE_TABLE_SURVEY = "CREATE TABLE "+ TABLE_NAME_SURVEY +" ("+ COLUMN_SURVEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLUMN_SURVEY_SCHOOL_ID +" INTEGER, "+ COLUMN_SURVEY_COMMENT +" TEXT, " + COLUMN_SURVEY_RATING + " DECIMAL(10,2), " + COLUMN_SURVEY_DATE +" DATETIME DEFAULT CURRENT_TIMESTAMP)";
    public static String CREATE_TABLE_SURVEY_DETAILS = "CREATE TABLE "+ TABLE_NAME_SURVEY_DETAILS +" ("+ COLUMN_SURVEY_DETAILS_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLUMN_SURVEY_DETAILS_SURVEY_ID +" INTEGER, "+ COLUMN_SURVEY_DETAILS_QUES_ID +" INTEGER, "+ COLUMN_SURVEY_DETAILS_RATING +" DECIMAL(10,2))";

    public static final String QUERY_SURVEY = "SELECT " +
            " " + COLUMN_SURVEY_ID +
            ", " + COLUMN_SCHOOL_NAME +
            ", " + COLUMN_SURVEY_RATING +
            ", " + COLUMN_SURVEY_COMMENT +
            " FROM" +
            " " + TABLE_NAME_SURVEY +
            " INNER JOIN "+ TABLE_NAME_SCHOOL +" ON "+ COLUMN_SCHOOL_ID +" = " + COLUMN_SURVEY_SCHOOL_ID;


}
