package com.example.fitnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DropDownActivity extends AppCompatActivity {

    int sets, reps, oneRM;
    EditText setsInput;
    EditText repsInput;
    EditText onermInput;

    Button saveBttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_down);

        setsInput = (EditText) findViewById(R.id.setsInput);
        repsInput = (EditText) findViewById(R.id.repsInput);
        onermInput = (EditText) findViewById(R.id.rmInput);

        saveBttn = (Button)findViewById(R.id.saveBttn);

        saveBttn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sets = Integer.valueOf(setsInput.getText().toString());
                reps = Integer.valueOf(repsInput.getText().toString());
                oneRM = Integer.valueOf(onermInput.getText().toString());

                showToast("Sets entered: " + String.valueOf(sets));
                showToast("Reps entered: " + String.valueOf(reps));
                showToast("1RM entered: " + String.valueOf(oneRM));
            }

        });
    }

    private void showToast(String text){
        Toast.makeText(DropDownActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
