package com.example.fitnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterPage extends AppCompatActivity {

    Button RegisterBttn;
    Button LoginBttn2;
    EditText userEmail, userPassword, userName;

    //Firebase setup
    FirebaseDatabase mydatabase;
    DatabaseReference users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        RegisterBttn = (Button) findViewById(R.id.registerBttn);
        LoginBttn2 = (Button) findViewById(R.id.loginBttn2);

        //Firebase connect
        mydatabase = FirebaseDatabase.getInstance();
        users = mydatabase.getReference("Users");

        userEmail = (EditText) findViewById(R.id.emailRegisterText);
        userPassword = (EditText) findViewById(R.id.passwordRegisterText);
        userName = (EditText) findViewById(R.id.nameRegisterText);

        RegisterBttn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final User user = new User(userName.getText().toString(),
                        userPassword.getText().toString(),
                        userEmail.getText().toString());

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(user.getUserName()).exists()) {
                            Toast.makeText(RegisterPage.this, "The Username already exists", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            users.child(user.getUserName()).setValue(user);
                            Toast.makeText(RegisterPage.this, "Registered successfully", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                       Toast.makeText(RegisterPage.this, "Error registering", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });


        LoginBttn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                startActivity(intent);
            }

        });


    }


}
