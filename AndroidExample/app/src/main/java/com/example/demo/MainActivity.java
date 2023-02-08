package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView gotoRegister;
    Button loginBtn;
    EditText usernameLogin;
    EditText passwordLogin;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        gotoRegister = findViewById(R.id.gotoRegister);
        loginBtn = findViewById(R.id.loginBtn);
        usernameLogin = findViewById(R.id.usernameLogin);
        passwordLogin = findViewById(R.id.passwordLogin);

        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerActivityIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerActivityIntent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameLogin.getText().toString();
                String password = passwordLogin.getText().toString();

                UserData userData = databaseHelper.check(username, password);
                if (userData == null) {
                    Toast.makeText(MainActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent userActivityIntent = new Intent(MainActivity.this, UserActivity.class);
                    userActivityIntent.putExtra("firstname", userData.getFirstName());
                    userActivityIntent.putExtra("lastname", userData.getLastName());
                    startActivity(userActivityIntent);
                }
            }
        });



    }
}