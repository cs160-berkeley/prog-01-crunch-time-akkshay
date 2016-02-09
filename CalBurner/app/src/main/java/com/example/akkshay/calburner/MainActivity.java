package com.example.akkshay.calburner;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class MainActivity extends ActionBarActivity {
    private RadioGroup radioGroup;
    TextView caloriesLabel;
    TextView equivalents;
    EditText repsTextField;
    EditText exerciseTextField;
    float numCalories;
    boolean minutes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    if (checkedId == R.id.minutesRadioButton) {
                        minutes = true;
                    } else {
                        minutes = false;
                    }
                }

            }
        });

        caloriesLabel = (TextView) findViewById(R.id.caloriesLabel);
        repsTextField = (EditText) findViewById(R.id.repsTextField);
        exerciseTextField = (EditText) findViewById(R.id.exerciseTextField);
        equivalents = (TextView) findViewById(R.id.equivalentsTextView);


        Button calcButton = (Button) findViewById(R.id.calcButton);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float reps = 0;
                if (repsTextField.getText().toString().equals("") == false) {
                    reps = Float.parseFloat(repsTextField.getText().toString());
                    if (exerciseTextField.getText().toString().equals("Pushup")) {
                        numCalories = (100*reps)/350;
                        float situps = (numCalories*350)/200;
                        float jj = (numCalories*350)/10;
                        float joggs = (numCalories*350)/12;
                        equivalents.setText("To burn the same amount of calories, you can do " + situps + " situps, " + jj + " minutes of jumping jacks, or " + joggs + " minutes of jogging.");
                    } else if (exerciseTextField.getText().toString().equals("Situp")) {
                        numCalories = (200*reps)/350;
                        float pushups = (numCalories*350)/100;
                        float jj = (numCalories*350)/10;
                        float joggs = (numCalories*350)/12;
                        equivalents.setText("To burn the same amount of calories, you can do " + pushups + " pushups, " + jj + " minutes of jumping jacks, or " + joggs + " minutes of jogging.");
                    } else if (exerciseTextField.getText().toString().equals("Jumping Jacks")) {
                        numCalories = (10*reps)/350;
                        float situps = (numCalories*350)/200;
                        float pushups = (numCalories*350)/100;
                        float joggs = (numCalories*350)/12;
                        equivalents.setText("To burn the same amount of calories, you can do " + situps + " situps, " + pushups + " pushups, or " + joggs + " minutes of jogging.");
                    } else if (exerciseTextField.getText().toString().equals("Jogging")) {
                        numCalories = (12*reps)/350;
                        float situps = (numCalories*350)/200;
                        float jj = (numCalories*350)/10;
                        float pushups = (numCalories*350)/100;
                        equivalents.setText("To burn the same amount of calories, you can do " + situps + " situps, " + jj + " minutes of jumping jacks, or " + pushups + " pushups.");
                    }
                    caloriesLabel.setText(Float.toString(numCalories) + " Cal");
                }


            }
        });



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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
