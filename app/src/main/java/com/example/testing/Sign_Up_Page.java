package com.example.testing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_Up_Page extends AppCompatActivity {

    public Button create;
    private EditText signup_email, signup_username, signup_password;
    private FirebaseDatabase database;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_page);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        create = (Button) findViewById(R.id.loginButton);

        signup_email = findViewById(R.id.signup_email);
        signup_username = findViewById(R.id.signup_username);
        signup_password = findViewById(R.id.signup_password);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String email = signup_email.getText().toString();
                String username = signup_username.getText().toString();
                String password = signup_password.getText().toString();

                User helper_class = new User(email, username, password);
                reference.child(username).setValue(helper_class);

                Toast.makeText(Sign_Up_Page.this, "You have signed up successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Sign_Up_Page.this, Home_Page.class);
                startActivity(intent);
            }
        });
    }
}