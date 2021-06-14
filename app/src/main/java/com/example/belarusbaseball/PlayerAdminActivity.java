package com.example.belarusbaseball;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PlayerAdminActivity extends AppCompatActivity {
    private DatabaseReference database;
    private EditText playerFirstName,playerLastName,playerAge;
    private String key;
    private int visibility;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_player);
        playerFirstName = findViewById(R.id.playerFirstNameEdit);
        playerLastName = findViewById(R.id.playerLastNameEdit);
        playerAge = findViewById(R.id.playerAgeEdit);
        database = FirebaseDatabase.getInstance().getReference().child("Player");
        getIntentMain();
    }

    private void getIntentMain(){
        Bundle arguments = getIntent().getExtras();
        Intent intent = getIntent();
        if(arguments != null){
            key = intent.getStringExtra("keys");
            playerFirstName.setText(intent.getStringExtra("player_name_first"));
            playerLastName.setText(intent.getStringExtra("player_name_last"));
            playerAge.setText(intent.getStringExtra("player_age"));
            visibility = arguments.getInt("player_visibility");
        }
    }

    public void saveData(){
        String firstName = playerFirstName.getText().toString();
        String lastName = playerLastName.getText().toString();
        String age = playerAge.getText().toString();
        Player newPlayer = new Player(lastName, firstName, age);
        database.child(lastName+" "+firstName).setValue(newPlayer);
        Toast.makeText(getApplicationContext(), "Player was successfully added to db!",
                Toast.LENGTH_SHORT).show();
    }

    public void saveEditData(){
        String firstName = playerFirstName.getText().toString();
        String lastName = playerLastName.getText().toString();
        String age = playerAge.getText().toString();
        Player newPlayer = new Player(firstName, lastName, age);
        database.child(lastName+" "+firstName).setValue(newPlayer);
        Toast.makeText(getApplicationContext(), "Player was successfully edit!",
                Toast.LENGTH_SHORT).show();
    }

    public void onClickSave(View v) {
        if(visibility==1)saveEditData();
        else saveData();
    }
}
