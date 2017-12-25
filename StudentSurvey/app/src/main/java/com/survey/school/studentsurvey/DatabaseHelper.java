package com.survey.school.studentsurvey;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper{

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE_QUES);
        db.execSQL(Constants.CREATE_TABLE_OPTION);
        db.execSQL(Constants.CREATE_TABLE_STUDENT);
        db.execSQL(Constants.CREATE_TABLE_SURVEY_QUES_MAP);
        db.execSQL(Constants.CREATE_TABLE_SURVEY_STU_MAP);
    }

    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME , null, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_OPTION);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_QUES);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_SURVEY_QUE_MAP);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_SURVEY_STU_MAP);
        onCreate(db);
    }

    public String dropAndCreateTables(){
        String result = "";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_STUDENT);
            db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_SURVEY_QUE_MAP);
            db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_SURVEY_STU_MAP);

            db.execSQL(Constants.CREATE_TABLE_STUDENT);
            db.execSQL(Constants.CREATE_TABLE_SURVEY_QUES_MAP);
            db.execSQL(Constants.CREATE_TABLE_SURVEY_STU_MAP);
            result = "Data cleaned successfully !!";
        }
        catch (Exception ex){
            result = "Error while cleaning data : " + ex.getMessage();
        }
        return result;
    }

    private int getLastInsertedId(String tableName, String columnName){
        int id = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + columnName +" FROM "+ tableName +" ORDER BY " + columnName + " DESC LIMIT 1";
        Cursor res = db.rawQuery(query, null);
        if(res != null && res.moveToFirst()) {
            res.moveToFirst();
            id = Integer.parseInt(res.getString(res.getColumnIndex(columnName)));
        }
        return id;
    }

    public String insertSurvey(SurveyDetails surveyDetails) throws Exception {
        //dropAndCreateTables();
        String result = "";
        SQLiteDatabase db = this.getWritableDatabase();
        String insertStudentQuery, surveyStuMapQuery, surveyQuesMapQuery = "";
        int studentId, survey_stu_map_id = 0;
        ArrayList<Survey> lstSurvey = new ArrayList<Survey>();
        try {
            lstSurvey = surveyDetails.getListSurveyDetails();
            String temp = Constants.INSERT_STUDENT;
            insertStudentQuery = String.format(Constants.INSERT_STUDENT, surveyDetails.getStudentDetails().getStudentName(),surveyDetails.getStudentDetails().getStudentClass(), surveyDetails.getStudentDetails().getFatherName(),surveyDetails.getStudentDetails().getMotherName(), surveyDetails.getStudentDetails().getAge());
            try {
                db.execSQL(insertStudentQuery);
            }
            catch (Exception exStudent){
                throw new Exception("Error while saving student data : " + exStudent.getMessage());
            }
            studentId = getLastInsertedId(Constants.TABLE_NAME_STUDENT, Constants.COLUMN_STUDENT_ID);
            if(studentId > 0){
                surveyStuMapQuery = String.format(Constants.INSERT_SURVEY_STU_MAP, studentId);
                try {
                    db.execSQL(surveyStuMapQuery);
                }
                catch (Exception exStuSurveyMap){
                    throw new Exception("Error while saving surevy student map data : " + exStuSurveyMap.getMessage());
                }
                survey_stu_map_id = getLastInsertedId(Constants.TABLE_NAME_SURVEY_STU_MAP, Constants.COLUMN_SURVEY_STU_MAP_ID);
                try{
                    if(survey_stu_map_id > 0) {
                        for (Survey survey : lstSurvey) {
                            surveyQuesMapQuery = "";
                            surveyQuesMapQuery = String.format(Constants.INSERT_SURVEY_QUES_MAP, survey_stu_map_id, survey.getQuestionId(), survey.getSelectedOptionId());
                            db.execSQL(surveyQuesMapQuery);
                        }
                    }else{
                        db.close();
                        result = "Error while saving surevy student map data !!";
                        throw new Exception("Error while saving surevy student map data !!");
                    }
                }
                catch (Exception exSurveyQueMap){
                    throw new Exception("Error while saving surevy question map data : " + exSurveyQueMap.getMessage());
                }
            }else{
                db.close();
                result = "Error while saving student data !!";
                throw new Exception("Error while saving student data !!");
            }
            db.close();
            result = "Survey saved successfully !!";
            //  result = checkInsertedData();
            return result;
        }
        catch (Exception ex){
            db.close();
            result = ex.getMessage();
            //throw ex;
        }
        return result;
    }

    public String checkInsertedData(){
        //SQLiteDatabase db = this.getReadableDatabase();
        String result = "";
        try {
            int studentId = getLastInsertedId(Constants.TABLE_NAME_STUDENT, Constants.COLUMN_STUDENT_ID);
            int survey_stu_map_id = getLastInsertedId(Constants.TABLE_NAME_SURVEY_STU_MAP, Constants.COLUMN_SURVEY_STU_MAP_ID);
            int survey_que_map_id = getLastInsertedId(Constants.TABLE_NAME_SURVEY_QUE_MAP, Constants.COLUMN_SURVEY_QUES_MAP_ID);
            result = Constants.TABLE_NAME_STUDENT + " : " + Integer.toString(studentId) + "\n" +
                     Constants.TABLE_NAME_SURVEY_STU_MAP + " : " + Integer.toString(survey_stu_map_id) + "\n" +
                    Constants.TABLE_NAME_SURVEY_QUE_MAP + " : " + Integer.toString(survey_que_map_id) + "\n";
        }
        catch(Exception ex){
            throw ex;
        }
        return result;
    }

    public ArrayList<Student> getStudentDetail(){
        ArrayList<Student> lstStudent = new ArrayList<Student>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(Constants.QUERY_STUDENT_DETAIL_WITH_SURVEY_ID, null);
        int s = res.getCount();
        res.moveToFirst();

        Student student;
        while (res.isAfterLast() == false) {
            student = new Student();
            student.setAge(Integer.parseInt(res.getString(res.getColumnIndex(Constants.COLUMN_STUDENT_AGE))));
            student.setStudentClass(res.getString(res.getColumnIndex(Constants.COLUMN_STUDENT_CLASS)));
            student.setMotherName(res.getString(res.getColumnIndex(Constants.COLUMN_STUDENT_MOTHER)));
            student.setFatherName(res.getString(res.getColumnIndex(Constants.COLUMN_STUDENT_FATHER)));
            student.setStudentName(res.getString(res.getColumnIndex(Constants.COLUMN_STUDENT_NAME)));
            student.setSurveyId(Integer.parseInt(res.getString(res.getColumnIndex(Constants.COLUMN_SURVEY_STU_MAP_ID))));

            lstStudent.add(student);
            res.moveToNext();
        }
        return lstStudent;
    }

    public ArrayList<Survey> getSurveyDetails(String surveyId){
        ArrayList<Survey> lstSurvey = new ArrayList<Survey>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(Constants.QUERY_SURVEY_DETAILS + surveyId, null);
        int s = res.getCount();
        res.moveToFirst();

        Survey surevy;
        while (res.isAfterLast() == false) {
            surevy = new Survey();
            surevy.setQuestion(res.getString(res.getColumnIndex(Constants.COLUMN_QUES_TEXT)));
            surevy.setSelectedOption(res.getString(res.getColumnIndex(Constants.COLUMN_OPT_TEXT)));

            lstSurvey.add(surevy);
            res.moveToNext();
        }
        return lstSurvey;
    }

    public int getDataCount(String tableName){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);
        return numRows;
    }

    public boolean insertOptions(ArrayList<String> options){
        boolean result = false;
        String insertQuery = "";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            if (getDataCount(Constants.TABLE_NAME_OPTION) == 0) {
                for (String question : options) {
                    insertQuery = "";
                    insertQuery = String.format(Constants.INSERT_OPTION, question);
                    db.execSQL(insertQuery);
                }
            }
            return true;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    public boolean insertQuestions(ArrayList<String> questions){
        boolean result = false;
        String insertQuery = "";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_QUES);
            db.execSQL(Constants.CREATE_TABLE_QUES);
            if (getDataCount(Constants.TABLE_NAME_QUES) == 0) {
                for (String question : questions) {
                    insertQuery = "";
                    insertQuery = String.format(Constants.INSERT_QUES, question);
                    db.execSQL(insertQuery);
                }
            }
            return true;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    public Map<Integer,String> getOptionsMap(){
        Map<Integer,String> mapOptions = new HashMap<Integer,String>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME_OPTION, null);
            res.moveToFirst();

            while (res.isAfterLast() == false) {
                mapOptions.put(Integer.parseInt(res.getString(res.getColumnIndex(Constants.COLUMN_OPT_ID))), res.getString(res.getColumnIndex(Constants.COLUMN_OPT_TEXT)));
                res.moveToNext();
            }
            return mapOptions;
        }
        catch (Exception ex){
            throw ex;
        }
    }

    public Cursor getQuestionsCursor(){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME_QUES, null);
            return res;
        }
        catch (Exception ex){
            throw ex;
        }
    }

    public ArrayList<String> getOptions(){
        ArrayList<String> questions = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + Constants.TABLE_NAME_OPTION, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            questions.add(res.getString(res.getColumnIndex(Constants.COLUMN_OPT_ID)) + " : " + res.getString(res.getColumnIndex(Constants.COLUMN_OPT_TEXT)));
            res.moveToNext();
        }
        return questions;
    }

    public ArrayList<String> getQuestions(){
        ArrayList<String> questions = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + Constants.TABLE_NAME_QUES, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            questions.add(res.getString(res.getColumnIndex(Constants.COLUMN_QUES_ID)) + " : " + res.getString(res.getColumnIndex(Constants.COLUMN_QUES_TEXT)));
            res.moveToNext();
        }
        return questions;
    }
}
