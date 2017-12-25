package com.survey.school.studentsurvey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SurveyListAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Survey> surveyList;

    public SurveyListAdapter(Context context, ArrayList<Survey> surveyList) {
        this.context = context;
        this.surveyList = surveyList;
    }

    @Override
    public int getCount() {
        return surveyList.size();
    }

    @Override
    public Object getItem(int position) {
        return surveyList.get(position);
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
        TextView tvQuestionText = (TextView)convertView.findViewById(R.id.tvQuestion);
        TextView tvOptionText = (TextView)convertView.findViewById(R.id.tvSelectedOption);

        tvQuestionText.setText("Question : " + surveyList.get(position).getQuestion());
        tvOptionText.setText("Answer : " + surveyList.get(position).getSelectedOption());
        return convertView;
    }
}
