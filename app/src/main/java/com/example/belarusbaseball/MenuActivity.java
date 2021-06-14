package com.example.belarusbaseball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button btnTeams;
    private Button btnPlayers;
    private Button btnTournaments;
    private Button btnGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        addListenerOnButton();
    }

    public void addListenerOnButton(){
        btnTeams = (Button) findViewById(R.id.buttonTeams);
        btnPlayers = (Button) findViewById(R.id.buttonPlayers);
        btnTournaments = (Button) findViewById(R.id.buttonTournaments);
        btnGames = (Button) findViewById(R.id.buttonGames);
        btnTeams.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".TeamsActivity");
                        startActivity(intent);
                    }
                }
        );
        btnPlayers.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".PlayersActivity");
                        startActivity(intent);
                    }
                }
        );
        btnTournaments.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".TournamentsActivity");
                        startActivity(intent);
                    }
                }
        );
        btnGames.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".GamesActivity");
                        startActivity(intent);
                    }
                }
        );
    }


}