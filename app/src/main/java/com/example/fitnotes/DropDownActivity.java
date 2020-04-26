package com.example.fitnotes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class DropDownActivity extends AppCompatActivity {

    int sets, reps, oneRM;
    EditText setsInput;
    EditText repsInput;
    EditText onermInput;

    Button saveBttn;
    Record record;

    //Firebase setup
    FirebaseDatabase mydatabase;
    DatabaseReference recordsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_down);

        setsInput = (EditText) findViewById(R.id.setsInput);
        repsInput = (EditText) findViewById(R.id.repsInput);
        onermInput = (EditText) findViewById(R.id.rmInput);

        saveBttn = (Button)findViewById(R.id.saveBttn);

        //Firebase connect
        mydatabase = FirebaseDatabase.getInstance();
        recordsRef = mydatabase.getReference("Record");

        record = new Record();

        saveBttn.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v){
                sets = Integer.valueOf(setsInput.getText().toString());
                reps = Integer.valueOf(repsInput.getText().toString());
                oneRM = Integer.valueOf(onermInput.getText().toString());

                showToast("Sets entered: " + String.valueOf(sets));
                showToast("Reps entered: " + String.valueOf(reps));
                showToast("1RM entered: " + String.valueOf(oneRM));

                sets = Integer.parseInt(setsInput.getText().toString().trim());
                reps = Integer.parseInt(repsInput.getText().toString().trim());
                oneRM = Integer.parseInt(onermInput.getText().toString().trim());

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String strDate = dateFormat.format(date).toString();

                record.setSets(sets);
                record.setReps(reps);
                record.setOneRm(oneRM);
                record.setDate(strDate);

                recordsRef.push().setValue(record);
            }

        });
    }

    private void showToast(String text){
        Toast.makeText(DropDownActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
