package com.example.belarusbaseball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GamesActivity extends AppCompatActivity {

    DatabaseReference database;
    List<Game> games = new ArrayList<>();
    FloatingActionButton btnAdd;
    List<String> keys = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_games);
        database = FirebaseDatabase.getInstance().getReference().child("Game");

        GameAdapter.OnGameClickListener gameClickListener = new GameAdapter.OnGameClickListener() {
            @Override
            public void onGameClick(Game game, int position) {

                Toast.makeText(getApplicationContext(), "Game" + game.getNameHomeTeam() + "vs" + game.getNameGuestTeam() + " was chosen",
                        Toast.LENGTH_SHORT).show();
            }
        };
        GameAdapter adapter = new GameAdapter(this, games,gameClickListener,keys);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(games.size()>0)games.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    keys.add(ds.getKey());
                    Game game =ds.getValue(Game.class);
                    assert game != null;
                    games.add(game);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        recyclerView.setAdapter(adapter);

        btnAdd = (FloatingActionButton) findViewById(R.id.addGamesButton);
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".GameAdminActivity");
                        startActivity(intent);
                    }
                }
        );

    }
}
