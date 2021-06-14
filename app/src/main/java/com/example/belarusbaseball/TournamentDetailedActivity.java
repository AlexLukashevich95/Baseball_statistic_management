package com.example.belarusbaseball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TournamentDetailedActivity extends Activity {
    private TextView tournamentName,tournamentCategory;
    private String name,category,key;
    private int visibility = 1;
    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_tournaments);
        tournamentName = findViewById(R.id.tournamentNameView);
        tournamentCategory = findViewById(R.id.tournamentCategoryView);
        database = FirebaseDatabase.getInstance().getReference().child("Tournament");
        getIntentMain();
    }
    private void getIntentMain(){
        Intent intent = getIntent();
        if(intent != null){
            key = intent.getStringExtra("keys");
            tournamentName.setText(intent.getStringExtra("tournament_name"));
            tournamentCategory.setText(intent.getStringExtra("tournament_category"));
            name = tournamentName.getText().toString();
            category = tournamentCategory.getText().toString();
        }
    }

    public void deleteData(){
        database.child(key).setValue(null);
        Toast.makeText(getApplicationContext(), "Tournament was deleted!",
                Toast.LENGTH_SHORT).show();
    }

    public void onClickEdit(View v) {
        getIntentMain();
        Intent intent = new Intent(".TournamentAdminActivity");
        intent.putExtra("keys", key);
        intent.putExtra("tournament_name", name);
        intent.putExtra("tournament_category",category);
        intent.putExtra("tournament_visibility", visibility);
        startActivity(intent);
    }

    public void onClickDelete(View v){
        deleteData();
    }



}

