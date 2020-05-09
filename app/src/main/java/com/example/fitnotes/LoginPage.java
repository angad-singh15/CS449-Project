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

public class LoginPage extends AppCompatActivity {

    Button LoginBttn;
    EditText userName, userPassword;

    //Firebase setup
    FirebaseDatabase mydatabase;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        LoginBttn = (Button) findViewById(R.id.loginButton);
        userName = (EditText) findViewById(R.id.nameLoginText);
        userPassword = (EditText) findViewById(R.id.passLoginText);

        //Firebase connect
        mydatabase = FirebaseDatabase.getInstance();
        users = mydatabase.getReference("Users");

        LoginBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Info", "Bttn pressed");
                Login(userName.getText().toString(), userPassword.getText().toString());
            }
        });

    }

    private void Login(final String username, final String password) {

        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(username).exists()){
                    if(!username.isEmpty()){

                        User login = dataSnapshot.child(username).getValue(User.class);

                        if(login.getPassword().equals(password)){
                            Toast.makeText(LoginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            navigateUpTo(new Intent(getBaseContext(), MainActivity.class));
                        }
                        else {
                            Toast.makeText(LoginPage.this, "Wrong Password Entered", Toast.LENGTH_SHORT).show();
                        }
                    }

                    else{
                        Toast.makeText(LoginPage.this, "Username cannot be blank", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(LoginPage.this, "The Username does not exist", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
