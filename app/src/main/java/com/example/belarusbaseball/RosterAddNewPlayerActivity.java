package com.example.belarusbaseball;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RosterAddNewPlayerActivity extends AppCompatActivity {
    private DatabaseReference database;
    private EditText playerFirstName,playerLastName,playerAge,playerPosition,playerPositionBatting;
    private String team,teamHome,teamGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster_add_new_player);
        playerFirstName = findViewById(R.id.playerFirstNameEdit);
        playerLastName = findViewById(R.id.playerLastNameEdit);
        playerAge = findViewById(R.id.playerAgeEdit);
        playerPosition = findViewById(R.id.playerPositionEdit);
        playerPositionBatting = findViewById(R.id.playerPositionBattingEdit);
        getIntentMain();
        database = FirebaseDatabase.getInstance().getReference().child("Game").child(teamHome +" VS "+teamGuest);

    }

    private void getIntentMain() {
        Bundle arguments = getIntent().getExtras();
        Intent intent = getIntent();
        if(arguments != null){
            team = intent.getStringExtra("team");
            teamHome = intent.getStringExtra("game_home_team");
            teamGuest = intent.getStringExtra("game_guest_team");
            playerFirstName.setText(intent.getStringExtra("player_name_first"));
            playerLastName.setText(intent.getStringExtra("player_name_last"));
            playerAge.setText(intent.getStringExtra("player_age"));
        }
    }

    public void saveData(){
        DatabaseReference dbPlayer;
        dbPlayer = FirebaseDatabase.getInstance().getReference().child("Player");
        String firstName = playerFirstName.getText().toString();
        String lastName = playerLastName.getText().toString();
        String age = playerAge.getText().toString();
        String position = playerPosition.getText().toString();
        String positionBat = playerPositionBatting.getText().toString();
        Roster newPlayer = new Roster(lastName, firstName, age, position, positionBat);
        Player player = new Player(lastName,firstName,age);
        database.child("Roster " + team).push().setValue(newPlayer);
        dbPlayer.child(lastName+" "+firstName).setValue(player);
        Toast.makeText(getApplicationContext(), "Player was successfully added to db!",
                Toast.LENGTH_SHORT).show();
    }
    public void onClickSave(View v) {
        saveData();
    }
}