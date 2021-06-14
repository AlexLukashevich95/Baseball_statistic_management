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

public class PlayerDetailedActivity extends Activity {
    private TextView playerFirstName,playerLastName,playerAge;
    private String nameFirst,nameLast,age,key;
    private int visibility = 1;
    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_players);
        playerFirstName = findViewById(R.id.playerFirstNameView);
        playerLastName = findViewById(R.id.playerLastNameView);
        playerAge = findViewById(R.id.playerAgeView);
        database = FirebaseDatabase.getInstance().getReference().child("Player");
        getIntentMain();
    }
    private void getIntentMain(){
        Intent intent = getIntent();
        if(intent != null){
            key = intent.getStringExtra("keys");
            playerFirstName.setText(intent.getStringExtra("player_name_first"));
            playerLastName.setText(intent.getStringExtra("player_name_last"));
            playerAge.setText(intent.getStringExtra("player_age"));
            nameFirst = playerFirstName.getText().toString();
            nameLast = playerLastName.getText().toString();
            age = playerAge.getText().toString();
        }
    }

    public void deleteData(){
        database.child(key).setValue(null);
        Toast.makeText(getApplicationContext(), "Player was deleted!",
                Toast.LENGTH_SHORT).show();
    }

    public void onClickEdit(View v) {
        getIntentMain();
        Intent intent = new Intent(".PlayerAdminActivity");
        intent.putExtra("keys", key);
        intent.putExtra("player_name_first", nameFirst);
        intent.putExtra("player_name_last",nameLast);
        intent.putExtra("player_age", age);
        intent.putExtra("player_visibility", visibility);
        startActivity(intent);
    }

    public void onClickDelete(View v){
        deleteData();
    }

    public void onClickStatistic(View v){
        getIntentMain();
        Intent intent = new Intent(".PlayerStatisticActivity");
        intent.putExtra("player_name_first", nameFirst);
        intent.putExtra("player_name_last",nameLast);
        startActivity(intent);
    }



}

