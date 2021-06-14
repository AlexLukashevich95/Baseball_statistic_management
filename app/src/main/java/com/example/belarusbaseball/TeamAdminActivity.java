package com.example.belarusbaseball;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class TeamAdminActivity extends AppCompatActivity {
    private DatabaseReference database;
    private EditText edName, edCategory, edCity;
    private String key;
    private int visibility;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_team);
        edName = findViewById(R.id.teamNameEdit);
        edCategory = findViewById(R.id.teamCategoryEdit);
        edCity = findViewById(R.id.teamCityEdit);
        database = FirebaseDatabase.getInstance().getReference().child("Team");
        getIntentMain();
    }

    private void getIntentMain(){
        Bundle arguments = getIntent().getExtras();
        Intent intent = getIntent();
        if(arguments != null){
            key = intent.getStringExtra("keys");
            edName.setText(intent.getStringExtra("team_name"));
            edCategory.setText(intent.getStringExtra("team_category"));
            edCity.setText(intent.getStringExtra("team_city"));
            visibility = arguments.getInt("team_visibility");
        }
    }

    public void saveData(){
        String name = edName.getText().toString();
        String category = edCategory.getText().toString();
        String city = edCity.getText().toString();
        Team newTeam = new Team(name, category, city);
        database.push().setValue(newTeam);
        Toast.makeText(getApplicationContext(), "Team was successfully added to db!",
                Toast.LENGTH_SHORT).show();
    }

    public void saveEditData(){
        String name = edName.getText().toString();
        String category = edCategory.getText().toString();
        String city = edCity.getText().toString();
        Team newTeam = new Team(name, category, city);
        database.child(key).setValue(newTeam);
        Toast.makeText(getApplicationContext(), "Team was successfully edit!",
                Toast.LENGTH_SHORT).show();
    }

    public void onClickSave(View v) {
        if(visibility==1)saveEditData();
        else saveData();
    }
}
