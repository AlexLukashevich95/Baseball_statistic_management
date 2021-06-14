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

public class GameDetailedActivity extends Activity {
    private TextView gameTeamHome,gameTeamGuest, gameTournament, gameDate;
    private String teamHome,teamGuest,date, tournament, key;
    private int visibility = 1;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_games);
        gameTeamHome = findViewById(R.id.gameHomeTeamView);
        gameTeamGuest = findViewById(R.id.gameGuestTeamView);
        gameDate = findViewById(R.id.gameDateView);
        gameTournament = findViewById(R.id.gameTournamentView);
        database = FirebaseDatabase.getInstance().getReference().child("Game");
        getIntentMain();
    }

    private void getIntentMain() {
        Intent intent = getIntent();
        if (intent != null) {
            key = intent.getStringExtra("keys");
            gameTeamHome.setText(intent.getStringExtra("game_home_team"));
            gameTeamGuest.setText(intent.getStringExtra("game_guest_team"));
            gameDate.setText(intent.getStringExtra("game_date"));
            gameTournament.setText(intent.getStringExtra("game_tournament"));
            teamHome = gameTeamHome.getText().toString();
            teamGuest = gameTeamGuest.getText().toString();
            date = gameDate.getText().toString();
            tournament = gameTournament.getText().toString();
        }
    }

    public void deleteData() {
        database.child(key).setValue(null);
        Toast.makeText(getApplicationContext(), "Game was deleted!",
                Toast.LENGTH_SHORT).show();
    }

    public void onClickEdit(View v) {
        getIntentMain();
        Intent intent = new Intent(".GameAdminActivity");
        intent.putExtra("keys", key);
        intent.putExtra("game_home_team", teamHome);
        intent.putExtra("game_guest_team", teamGuest);
        intent.putExtra("game_date", date);
        intent.putExtra("game_visibility", visibility);
        startActivity(intent);
    }

    public void onClickDelete(View v) {
        deleteData();
    }

    public void onClickPlay(View v){
        Intent intent = new Intent(".RosterActivity");
        intent.putExtra("key", key);
        intent.putExtra("date",date);
        intent.putExtra("game_home_team", teamHome);
        intent.putExtra("game_guest_team", teamGuest);
        startActivity(intent);
    }


}
