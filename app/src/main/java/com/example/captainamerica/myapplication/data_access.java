package com.example.captainamerica.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class data_access extends AppCompatActivity {

  /*  private TextView firstName;
    private TextView lastName;
    private TextView gender;
    private TextView martialStatus; */

    private String firstNameData;
    private String lastNameData;
    private String genderData;
    private String martialStatusData;
    String courseData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_access);
        Bundle bundle = getIntent().getExtras();
        firstNameData = bundle.getString("firstName");
        lastNameData = bundle.getString("lastName");
        genderData = bundle.getString("gender");
        martialStatusData = bundle.getString("martialStatus");


      courseData=bundle.getString("Courses");

        ListView listView=(ListView) findViewById(R.id.listView);
   /*     TextView firstName=(TextView) findViewById(R.id.firstNameOutput);
        TextView lastName=(TextView) findViewById(R.id.lastNameOutput);
        TextView gender=(TextView) findViewById(R.id.genderOutput);
        TextView martialStatus=(TextView) findViewById(R.id.martialStatusOutput);

        firstName.setText(firstNameData);
        lastName.setText(lastNameData);
        gender.setText(genderData);
        martialStatus.setText(martialStatusData);*/
   String[] arr={"firstName: "+firstNameData,"lastName: "+lastNameData,"Gender: "+genderData,"Martial Status: "+martialStatusData,"SelectedCourses: "+courseData};
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arr);

        listView.setAdapter(arrayAdapter);

    }
}
