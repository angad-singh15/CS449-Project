package com.example.fitnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterPage extends AppCompatActivity {

    Button RegisterBttn;
    Button LoginBttn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        RegisterBttn = (Button) findViewById(R.id.registerBttn);
        LoginBttn2 = (Button) findViewById(R.id.loginBttn2);

        LoginBttn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                startActivity(intent);
            }

        });

    }


}
