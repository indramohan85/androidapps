package com.survey.school.cleanindiamision;

public class School {
    private String schoolId;
    private String schoolName;

    public School(String schoolId, String schoolName) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof School){
            School s = (School ) obj;
            if(s.getSchoolName().equals(schoolName) && s.getSchoolId() == schoolId ) return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return schoolName;
    }
}
