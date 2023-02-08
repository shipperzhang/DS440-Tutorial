package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    TextView information;
    TextView userToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        information = findViewById(R.id.information);
        userToLogin = findViewById(R.id.userToLogin);

        String firstname = getIntent().getStringExtra("firstname");
        String lastname = getIntent().getStringExtra("lastname");
        information.setText("Hello! " + firstname + " " + lastname + "!");
        userToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityIntent = new Intent(UserActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });
    }
}