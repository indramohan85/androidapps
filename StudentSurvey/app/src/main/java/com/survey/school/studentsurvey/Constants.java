package com.survey.school.studentsurvey;

/**
 * Created by IndraMohan on 16/12/2017.
 */

public abstract class Constants {
    public static final String DATABASE_NAME = "survey";
    public static final String TABLE_NAME_QUES = "tbl_Questions";
    public static final String COLUMN_QUES_ID = "q_id";
    public static final String COLUMN_QUES_TEXT = "q_text";

    public static final String TABLE_NAME_OPTION = "tbl_Options";
    public static final String COLUMN_OPT_ID = "opt_id";
    public static final String COLUMN_OPT_TEXT = "opt_text";

    public static final String TABLE_NAME_STUDENT = "tbl_Student";
    public static final String COLUMN_STUDENT_ID = "stu_id";
    public static final String COLUMN_STUDENT_NAME = "stu_name";
    public static final String COLUMN_STUDENT_CLASS = "stu_class";
    public static final String COLUMN_STUDENT_FATHER = "stu_father";
    public static final String COLUMN_STUDENT_MOTHER = "stu_mother";
    public static final String COLUMN_STUDENT_AGE = "stu_age";

    public static final String TABLE_NAME_SURVEY_QUE_MAP = "tbl_SurveyQuesMapping";
    public static final String COLUMN_SURVEY_QUES_MAP_ID = "surveyQuesMap_id";
    public static final String COLUMN_SURVEY_QUES_MAP_SURVEY_ID = "survey_id";
    public static final String COLUMN_SURVEY_QUES_MAP_QUE_ID = "survey_que_id";
    public static final String COLUMN_SURVEY_QUES_MAP_OPT_ID = "survey_opt_id";

    public static final String TABLE_NAME_SURVEY_STU_MAP = "tbl_SurveyStudentMapping";
    public static final String COLUMN_SURVEY_STU_MAP_ID = "surveyStuMap_id";
    public static final String COLUMN_SURVEY_STU_MAP_STU_ID = "surveyStuMap_stu_id";
    public static final String COLUMN_SURVEY_STU_MAP_SURVEY_DATE = "surveyStuMap_date";

    public static final String QUERY_STUDENT_DETAIL_WITH_SURVEY_ID = "SELECT " +
            " " + COLUMN_STUDENT_NAME +
            ", " + COLUMN_STUDENT_CLASS +
            ", " + COLUMN_STUDENT_FATHER +
            ", " + COLUMN_STUDENT_MOTHER +
            ", " + COLUMN_STUDENT_AGE +
            ", " + COLUMN_SURVEY_STU_MAP_ID +
            " FROM" +
            " " + TABLE_NAME_SURVEY_STU_MAP +
            " INNER JOIN "+ TABLE_NAME_STUDENT +" ON "+ COLUMN_SURVEY_STU_MAP_STU_ID +" = " + COLUMN_STUDENT_ID;

    public static final String QUERY_SURVEY_DETAILS = "SELECT" +
            " " + COLUMN_QUES_TEXT +
            ", " + COLUMN_OPT_TEXT +
            " FROM" +
            " " + TABLE_NAME_SURVEY_QUE_MAP +
            " INNER JOIN " + TABLE_NAME_QUES + " ON " + COLUMN_QUES_ID + " = " + COLUMN_SURVEY_QUES_MAP_QUE_ID +
            " INNER JOIN " + TABLE_NAME_OPTION + " ON " + COLUMN_OPT_ID + " = " + COLUMN_SURVEY_QUES_MAP_OPT_ID +
            " WHERE " + COLUMN_SURVEY_QUES_MAP_SURVEY_ID + " = ";

    public static final String QUERY_STUDENT_DETAIL = "SELECT stu_id, surveyStuMap_id, stu_name, stu_class, stu_father, stu_mother, stu_age FROM tbl_Student INNER JOIN tbl_SurveyStudentMapping ON stu_id = surveyStuMap_stu_id";

    public static String CREATE_TABLE_QUES = "CREATE TABLE tbl_Questions (q_id INTEGER PRIMARY KEY AUTOINCREMENT, q_text TEXT)";
    public static String CREATE_TABLE_OPTION = "CREATE TABLE tbl_Options (opt_id INTEGER PRIMARY KEY AUTOINCREMENT, opt_text TEXT)";
    public static String CREATE_TABLE_STUDENT = "CREATE TABLE tbl_Student (stu_id INTEGER PRIMARY KEY AUTOINCREMENT, stu_name TEXT, stu_class TEXT, stu_father TEXT, stu_mother TEXT, stu_age INTEGER)";
    public static String CREATE_TABLE_SURVEY_QUES_MAP = "CREATE TABLE tbl_SurveyQuesMapping (surveyQuesMap_id INTEGER PRIMARY KEY AUTOINCREMENT, survey_id INTEGER, survey_que_id INTEGER, survey_opt_id INTEGER)";
    public static String CREATE_TABLE_SURVEY_STU_MAP = "CREATE TABLE tbl_SurveyStudentMapping (surveyStuMap_id INTEGER PRIMARY KEY AUTOINCREMENT, surveyStuMap_stu_id INTEGER,surveyStuMap_date DATETIME DEFAULT CURRENT_TIMESTAMP)";

    //INSERT INTO tbl_Questions(q_text) VALUES ('%s')
    //INSERT INTO tbl_Options(opt_text) VALUES ('%s')
    //INSERT INTO tbl_Options(opt_text) VALUES ('%s')
    //INSERT INTO tbl_Options(opt_text) VALUES ('%s')
    public static String INSERT_OPTION = "INSERT INTO tbl_Options(opt_text) VALUES ('%s')";
    public static String INSERT_STUDENT = "INSERT INTO tbl_Student (stu_name, stu_class, stu_father, stu_mother, stu_age) VALUES ('%s','%s','%s','%s',%d)";
    public static String INSERT_SURVEY_QUES_MAP = "INSERT INTO tbl_SurveyQuesMapping (survey_id, survey_que_id, survey_opt_id) VALUES (%s, %s, %s)";
    public static String INSERT_SURVEY_STU_MAP = "INSERT INTO tbl_SurveyStudentMapping (surveyStuMap_stu_id) VALUES (%s)";
    public static String INSERT_QUES = "INSERT INTO tbl_Questions(q_text) VALUES ('%s')";
}
