package com.example.fitnotes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecordActivity extends AppCompatActivity {

    ListView recordView;
    ArrayList <String> arrayList;
    ArrayAdapter <String> arrayAdapter;

    //Firebase setup
    FirebaseDatabase mydatabase;
    DatabaseReference recordsRef;

    Record record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        record = new Record();

        recordView = (ListView) findViewById(R.id.listviewRecords);
        mydatabase = FirebaseDatabase.getInstance();
        recordsRef = mydatabase.getReference("Record");
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.record_info,R.id.recordInfo, arrayList );

        //query data from the database and display it as a record
        recordsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    record = ds.getValue(Record.class);
                    arrayList.add("Date: " + record.getDate() + "\n"
                            + "Workout: " + record.getChosenWorkout() + "\n"
                            + "Sets: "  + record.getSets().toString() + "\n"
                            +  "Reps: " + record.getReps().toString() + "\n"
                            +  "1RM: " +  record.getOneRm().toString());

                }
                recordView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
