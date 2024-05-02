package com.example.testing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class Post_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_post_page);

        Button post = (Button) findViewById(R.id.Post);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //code for making a post goes here
                Toast.makeText(Post_Page.this,"Posted!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Post_Page.this, Home_Page.class);
                startActivity(intent);
            }
        });

        ImageButton home = (ImageButton) findViewById(R.id.HomeButton);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Post_Page.this, Home_Page.class);
                startActivity(intent);
            }
        });

        ImageButton chat = (ImageButton) findViewById(R.id.ChatButton);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Post_Page.this, Chat_Page.class);
                startActivity(intent);
            }
        });
    }
}