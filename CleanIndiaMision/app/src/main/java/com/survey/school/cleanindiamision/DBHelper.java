package com.survey.school.cleanindiamision;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, 1);
    }

    String[] _questions = {
            "How will you rate the overall sanitation of the vidyaLaya ?",
            "Rate your school on waste disposable mechanism adopted in your school ?",
            "Rate your toilets on parameters like daily cleanliness ,proper functioning flush, availability of sanitary pads, hand wash, accessible toilets for differently able ?",
            "Is the food items served in canteen fresh and healthy. Is packaged food served in canteen bears fssai license. Rate your school on the parameters asked above.",
            "Is your school a  green campus . Rate your vidyalaya on this parameter ?"
    };

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE_QUES);
        db.execSQL(Constants.CREATE_TABLE_SCHOOL);
        db.execSQL(Constants.CREATE_TABLE_SURVEY);
        db.execSQL(Constants.CREATE_TABLE_SURVEY_DETAILS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_QUES);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_SCHOOL);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_SURVEY);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_SURVEY_DETAILS);
        onCreate(db);
    }

    public String dropAndCreateTables() {
        String result = "";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_QUES);
            db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_SCHOOL);
            db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_SURVEY);
            db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_SURVEY_DETAILS);

            db.execSQL(Constants.CREATE_TABLE_QUES);
            db.execSQL(Constants.CREATE_TABLE_SCHOOL);
            db.execSQL(Constants.CREATE_TABLE_SURVEY);
            db.execSQL(Constants.CREATE_TABLE_SURVEY_DETAILS);
            result = "Data cleaned successfully !!";
        } catch (Exception ex) {
            result = "Error while cleaning data : " + ex.getMessage();
        }
        return result;
    }

    private int getLastInsertedId(String tableName, String columnName) {
        int id = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + columnName + " FROM " + tableName + " ORDER BY " + columnName + " DESC LIMIT 1";
        Cursor res = db.rawQuery(query, null);
        if (res != null && res.moveToFirst()) {
            res.moveToFirst();
            id = Integer.parseInt(res.getString(res.getColumnIndex(columnName)));
        }
        return id;
    }

    public String insertSurvey(SurveyDetail surveyDetail) throws Exception{
        String result = "";
        SQLiteDatabase db = this.getWritableDatabase();
        String insertSurvey, insertSurveyDetails = "";
        int surveyId = 0;
        List<QuestionDetail> lstQuestion = new ArrayList<QuestionDetail>();
        try{
            //'%d','%s','%.2f'
            insertSurvey = String.format(Constants.INSERT_SURVEY, surveyDetail.getSchoolId(), surveyDetail.getComment(), surveyDetail.getRating());
            lstQuestion = surveyDetail.getLstQuestion();
            try{
                db.execSQL(insertSurvey);
            }catch (Exception exSurvey){
                throw new Exception("Error while saving survey data : " + exSurvey.getMessage());
            }
            surveyId = getLastInsertedId(Constants.TABLE_NAME_SURVEY, Constants.COLUMN_SURVEY_ID);
            if(surveyId > 0){
                try {
                    for (QuestionDetail queDetail : lstQuestion) {
                        insertSurveyDetails = "";
                        //'%d','%d','%.2f'
                        insertSurveyDetails = String.format(Constants.INSERT_SURVEY_DETAILS, surveyId, queDetail.getQuestionId(), queDetail.getRating());
                        db.execSQL(insertSurveyDetails);
                    }
                }catch (Exception exSurveyDetails){
                    db.close();
                    result = "Error while saving survey details data !!";
                    throw new Exception("Error while saving survey details data : " + exSurveyDetails.getMessage());
                }
            }else{
                db.close();
                result = "Error while saving survey data !!";
                throw new Exception("Error while saving survey data");
            }
            db.close();
            result = "Survey saved successfully !!";
            return result;
        }catch (Exception ex){
            db.close();
            result = ex.getMessage();
        }
        return result;
    }

    public ArrayList<SurveyDetail> getSurveyDetails(){
        ArrayList<SurveyDetail> lstSurvey = new ArrayList<SurveyDetail>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(Constants.QUERY_SURVEY, null);
        res.moveToFirst();

        SurveyDetail surevy;
        while (res.isAfterLast() == false) {
            surevy = new SurveyDetail();
            surevy.setComment(res.getString(res.getColumnIndex(Constants.COLUMN_SURVEY_COMMENT)));
            surevy.setSchoolName(res.getString(res.getColumnIndex(Constants.COLUMN_SCHOOL_NAME)));
            surevy.setRating(Float.parseFloat(res.getString(res.getColumnIndex(Constants.COLUMN_SURVEY_RATING))));

            lstSurvey.add(surevy);
            res.moveToNext();
        }
        return lstSurvey;
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

    public ArrayList<School> getSchoolsWithId(){
        ArrayList<School> lstSchool = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + Constants.TABLE_NAME_SCHOOL, null );
        res.moveToFirst();
        School school;
        while(res.isAfterLast() == false){
            school = new School(res.getString(res.getColumnIndex(Constants.COLUMN_SCHOOL_ID)), res.getString(res.getColumnIndex(Constants.COLUMN_SCHOOL_NAME)));
            lstSchool.add(school);
            res.moveToNext();
        }
        return lstSchool;
    }

    public ArrayList<String> getSchools(){
        ArrayList<String> schools = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + Constants.TABLE_NAME_SCHOOL, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            schools.add(res.getString(res.getColumnIndex(Constants.COLUMN_SCHOOL_ID)) + " : " + res.getString(res.getColumnIndex(Constants.COLUMN_SCHOOL_NAME)));
            res.moveToNext();
        }
        return schools;
    }

    public int getDataCount(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);
        return numRows;
    }

    public boolean insertSchool(String[] schools) {
        boolean result = false;
        String insertQuery = "";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_SCHOOL);
            db.execSQL(Constants.CREATE_TABLE_SCHOOL);
            if (getDataCount(Constants.TABLE_NAME_SCHOOL) == 0) {
                for (String school : schools) {
                    insertQuery = "";
                    insertQuery = String.format(Constants.INSERT_SCHOOL, school);
                    db.execSQL(insertQuery);
                }
            }
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean insertQuestions() {
        boolean result = false;
        String insertQuery = "";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_QUES);
            db.execSQL(Constants.CREATE_TABLE_QUES);
            if (getDataCount(Constants.TABLE_NAME_QUES) == 0) {
                for (String question : _questions) {
                    insertQuery = "";
                    insertQuery = String.format(Constants.INSERT_QUES, question);
                    db.execSQL(insertQuery);
                }
            }
            return true;
        } catch (Exception ex) {
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
}
