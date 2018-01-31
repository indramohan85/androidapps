package com.survey.school.cleanindiamision;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter{
    private ArrayList<SurveyDetail> lstSurvey;
    private Context context;

    public CustomAdapter(ArrayList<SurveyDetail> lstSurvey, Context context) {
        this.lstSurvey = lstSurvey;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lstSurvey.size();
    }

    @Override
    public Object getItem(int position) {
        return lstSurvey.get(position);
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
            convertView = inflater.inflate(R.layout.item_survey_list,null);
        }
        TextView schoolName = (TextView)convertView.findViewById(R.id.tvSchoolName);
        TextView tvComment = (TextView)convertView.findViewById(R.id.tvComment);
        TextView tvRating = (TextView)convertView.findViewById(R.id.tvRating);

        schoolName.setText("School : " + lstSurvey.get(position).getSchoolName());
        tvRating.setText("Rating : " + Float.toString(lstSurvey.get(position).getRating()));
        tvComment.setText("Comment : " + lstSurvey.get(position).getComment());
        return convertView;
    }
}
