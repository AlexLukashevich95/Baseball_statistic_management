package com.example.belarusbaseball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TeamDetailedActivity extends Activity {
    private TextView teamName,teamCategory,teamCity;
    private String name,category,city,key;
    private int visibility = 1;
    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_teams);
        teamName = findViewById(R.id.teamNameView);
        teamCategory = findViewById(R.id.teamCategoryView);
        teamCity = findViewById(R.id.teamCityView);
        database = FirebaseDatabase.getInstance().getReference().child("Team");
        getIntentMain();
    }
    private void getIntentMain(){
        Intent intent = getIntent();
        if(intent != null){
            key = intent.getStringExtra("keys");
            teamName.setText(intent.getStringExtra("team_name"));
            teamCategory.setText(intent.getStringExtra("team_category"));
            teamCity.setText(intent.getStringExtra("team_city"));
            name = teamName.getText().toString();
            category = teamCategory.getText().toString();
            city = teamCity.getText().toString();
        }
    }

    public void deleteData(){
        database.child(key).setValue(null);
        Toast.makeText(getApplicationContext(), "Team was deleted!",
                Toast.LENGTH_SHORT).show();
    }

    public void onClickEdit(View v) {
        getIntentMain();
        Intent intent = new Intent(".TeamAdminActivity");
        intent.putExtra("keys", key);
        intent.putExtra("team_name", name);
        intent.putExtra("team_category",category);
        intent.putExtra("team_city", city);
        intent.putExtra("team_visibility", visibility);
        startActivity(intent);
    }

    public void onClickDelete(View v){
        deleteData();
    }



}
