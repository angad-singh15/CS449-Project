package com.example.fitnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class WorkoutActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button addWorkoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Spinner workoutSpinner = findViewById(R.id.spinnerCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.WorkoutType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        workoutSpinner.setAdapter(adapter);
        workoutSpinner.setOnItemSelectedListener(this);

        addWorkoutButton = (Button) findViewById(R.id.addWorkoutBttn);

        addWorkoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getItemAtPosition(position).equals("Choose Your Workout")) {
            // do nothing
        } else {
            Intent intent = new Intent(WorkoutActivity.this, DropDownActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }

}
