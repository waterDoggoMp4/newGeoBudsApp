package com.example.testing;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText login_username, login_password;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button login = (Button) findViewById(R.id.loginButton);

        login_username = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Home_Page.class);
                startActivity(intent);
            }
        });

        Button signup = (Button) findViewById(R.id.signUpButton);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Sign_Up_Page.class);
                startActivity(intent);
            }
        });
    }

    private void check_user(){
        String username = login_username.getText().toString().trim();
        String password = login_password.getText().toString().trim();

        reference = FirebaseDatabase.getInstance().getReference("users");
        Query check_user_database = reference.orderByChild("username").equalTo(username);
        check_user_database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    login_username.setError(null);
                    String passwordFromDB = snapshot.child(username).child("password").getValue(String.class);
                    if(passwordFromDB.equals(password)){
                        login_username.setError(null);
                        String emailFromDB = snapshot.child(username).child("email").getValue(String.class);
                        String usernameFromDB = snapshot.child(username).child("username").getValue(String.class);

                        Intent intent = new Intent(MainActivity.this, Home_Page.class);

                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);
                    }
                    else{
                        login_password.setError("Invalid credentials");
                        login_password.requestFocus();
                    }
                }
                else{
                    login_username.setError("User does not exist!");
                    login_username.requestFocus();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}