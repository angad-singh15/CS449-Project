package com.example.fitnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button addWorkoutButton;
    EditText addWorkoutText;
    public static String chosenWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        //stores the data for drop down menu
        final String[] WorkoutTypes = new String[]{
                "Choose Your Workout",
                "Bench Press",
                "Deadlifts",
                "Squats",
                "Shoulder Press",
                "Bend Over Rows"
        };

        final Spinner workoutSpinner = findViewById(R.id.spinnerCategory);

        final List<String> workoutList = new ArrayList<>(Arrays.asList(WorkoutTypes));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, workoutList);

        //final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.WorkoutType, android.R.layout.simple_spinner_item);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        workoutSpinner.setAdapter(spinnerArrayAdapter);
        workoutSpinner.setOnItemSelectedListener(this);

        addWorkoutButton = (Button) findViewById(R.id.addWorkoutBttn);
        addWorkoutText = (EditText) findViewById(R.id.addWorkoutText);

        addWorkoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                workoutList.add(addWorkoutText.getText().toString());
                spinnerArrayAdapter.notifyDataSetChanged();

            }

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //skip the first item
        if (parent.getItemAtPosition(position).equals("Choose Your Workout")) {
            // do nothing
        }
        else {
            //get the workout chosen by the user
            chosenWorkout = parent.getItemAtPosition(position).toString();
            Intent intent = new Intent(WorkoutActivity.this, DropDownActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }

}
