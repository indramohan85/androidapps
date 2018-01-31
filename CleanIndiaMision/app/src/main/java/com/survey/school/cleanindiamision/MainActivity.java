package com.survey.school.cleanindiamision;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edUser, edPwd;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            btnLogin = (Button) findViewById(R.id.btnLogin);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doLogin();
                }
            });
        }catch (Exception ex){
            Utility.showMessage("Error launching activity", "" + ex.getStackTrace(), this);
        }
    }

    private void doLogin() {
        edUser = (EditText) findViewById(R.id.edUserName);
        edPwd = (EditText) findViewById(R.id.edPassword);
        String user = edUser.getText().toString();
        String pwd = edPwd.getText().toString();
        if(!edUser.getText().toString().equals("") && edPwd.getText().toString().equals("1111")){
            Intent intent = new Intent(MainActivity.this,SelectSchoolActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_admin) {
            Intent adminIntent = new Intent(MainActivity.this,AdminActivity.class);
            startActivity(adminIntent);
        }

        if(id == R.id.action_show_survey){
            Intent showSurveyIntent = new Intent(MainActivity.this,ShowSurveyActivity.class);
            startActivity(showSurveyIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
