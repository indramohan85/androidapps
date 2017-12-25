package com.survey.school.studentsurvey;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Student> studentList;

    public StudentListAdapter(Context context, ArrayList<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_student_list,null);
        }

        TextView tvName = (TextView)convertView.findViewById(R.id.tvStudentName);
        TextView tvClass = (TextView)convertView.findViewById(R.id.tvClass);
        TextView tvAge = (TextView)convertView.findViewById(R.id.tvAge);
        TextView tvFather = (TextView)convertView.findViewById(R.id.tvFather);

        tvName.setText("Name : " + studentList.get(position).getStudentName());
        tvClass.setText("Class : " + studentList.get(position).getStudentClass());
        tvAge.setText("Age : " + Integer.toString(studentList.get(position).getAge()));
        tvFather.setText("Father's name : " + studentList.get(position).getFatherName());

        convertView.setTag(studentList.get(position).getSurveyId());

        return convertView;
    }
}
