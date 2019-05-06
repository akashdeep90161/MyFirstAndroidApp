package com.example.captainamerica.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener{


    private Button btnSubmit;
    private EditText firstName;
    private EditText lastName;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private List<String> listCheckBox=new ArrayList<>();
    String martialStatusData="";
    String courses="";
    String[] gender={"Male","Female","Other"};
    String genderData;
  //  TextView txtView1;
  //  TextView txtView2;

    //defining AwesomeValidation object
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initializing awesomevalidation object
        /*
         * The library provides 3 types of validation
         * BASIC
         * COLORATION
         * UNDERLABEL
         * */
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.firstNameEditText, "[^.]+", R.string.empatyNameError);
        awesomeValidation.addValidation(this, R.id.firstNameEditText, "[a-zA-Z]*", R.string.validNameError);
        btnSubmit=(Button) findViewById(R.id.submit);

        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,gender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

       spin.setOnItemSelectedListener(this);


           btnSubmit.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   firstName = (EditText) findViewById(R.id.firstNameEditText);
                   String firstNameData = firstName.getText().toString();
                   Intent intent = new Intent(MainActivity.this, data_access.class);
                   intent.putExtra("firstName", firstNameData);

                   lastName = (EditText) findViewById(R.id.lastNameEditText);
                   String lastNameData = lastName.getText().toString();
                   intent.putExtra("lastName", lastNameData);
                   intent.putExtra("gender", genderData);

                   radioGroup = (RadioGroup) findViewById(R.id.martialStatusRadio);
                   int selectedRadioId = radioGroup.getCheckedRadioButtonId();

                   radioButton = (RadioButton) findViewById(selectedRadioId);
                   if(radioButton!=null) {
                       martialStatusData = radioButton.getText().toString();
                   }
                   intent.putExtra("martialStatus", martialStatusData);

                  intent.putExtra("Courses",courses);

                   if(awesomeValidation.validate() && courses.length()!=0)
                   {
                       startActivity(intent);
                   }
                   if (v == btnSubmit) {
                       submitForm();
                   }
               }
           });




    }
    private void submitForm() {
        //first validate the form then move ahead
        //if this becomes true that means validation is successfull
        if (awesomeValidation.validate() && courses.length()!=0) {
            Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();

            //process the data further
        }
        else if(courses.length()==0) {
            Toast.makeText(this, "please select at least one course", Toast.LENGTH_LONG).show();

        }
    }


    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        genderData=arg0.getItemAtPosition(position).toString();
        Log.d("akash",""+genderData);
    }
    @Override
    public void onClick(View view) {
        if (view == btnSubmit) {
            submitForm();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        CheckBox checkBox;

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_java:
                if (checked)
                listCheckBox.add(((CheckBox) findViewById(R.id.checkbox_java)).getText().toString());
                else
                listCheckBox.remove(((CheckBox) findViewById(R.id.checkbox_java)).getText().toString());
                break;
            case R.id.checkbox_Python:
                if (checked)
                    listCheckBox.add(((CheckBox) findViewById(R.id.checkbox_Python)).getText().toString());
                else
                    listCheckBox.remove(((CheckBox) findViewById(R.id.checkbox_Python)).getText().toString());
                break;
            case R.id.checkbox_PHP:
                if (checked)
                    listCheckBox.add(((CheckBox) findViewById(R.id.checkbox_PHP)).getText().toString());
                else
                    listCheckBox.remove(((CheckBox) findViewById(R.id.checkbox_PHP)).getText().toString());
                break;
            case R.id.checkbox_MYSQL:
                if (checked)
                    listCheckBox.add(((CheckBox) findViewById(R.id.checkbox_MYSQL)).getText().toString());
                else
                    listCheckBox.remove(((CheckBox) findViewById(R.id.checkbox_MYSQL)).getText().toString());
                break;
            // TODO: Veggie sandwich
        }
        if(listCheckBox.size()==0)
        {

        }
        String str="";
        for(String s:listCheckBox)
        {
            str+=s +", ";
        }
        if(str.length()!=0)
        {
            str = str.substring(0, str.length() - 2);
        }
        courses=str;
    }

}
