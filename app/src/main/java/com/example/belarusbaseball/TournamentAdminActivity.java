package com.example.belarusbaseball;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TournamentAdminActivity extends AppCompatActivity {
    private DatabaseReference database;
    private EditText edName, edCategory;
    private String key;
    private int visibility;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tournament);
        edName = findViewById(R.id.tournamentNameEdit);
        edCategory = findViewById(R.id.tournamentCategoryEdit);
        database = FirebaseDatabase.getInstance().getReference().child("Tournament");
        getIntentMain();
    }

    private void getIntentMain(){
        Bundle arguments = getIntent().getExtras();
        Intent intent = getIntent();
        if(arguments != null){
            key = intent.getStringExtra("keys");
            edName.setText(intent.getStringExtra("tournament_name"));
            edCategory.setText(intent.getStringExtra("tournament_category"));
            visibility = arguments.getInt("tournament_visibility");
        }
    }

    public void saveData(){
        String name = edName.getText().toString();
        String category = edCategory.getText().toString();
        Tournament newTournament = new Tournament(name, category);
        database.push().setValue(newTournament);
        Toast.makeText(getApplicationContext(), "Tournament was successfully added to db!",
                Toast.LENGTH_SHORT).show();
    }

    public void saveEditData(){
        String name = edName.getText().toString();
        String category = edCategory.getText().toString();
        Tournament newTournament = new Tournament(name, category);
        database.child(key).setValue(newTournament);
        Toast.makeText(getApplicationContext(), "Tournament was successfully edit!",
                Toast.LENGTH_SHORT).show();
    }

    public void onClickSave(View v) {
        if(visibility==1)saveEditData();
        else saveData();
    }
}

