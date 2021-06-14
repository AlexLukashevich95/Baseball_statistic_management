package com.example.belarusbaseball;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RosterAddExistingPlayerActivity extends AppCompatActivity {

    DatabaseReference database;
    List<Player> players = new ArrayList<>();
    List<String> keys = new ArrayList<>();
    String activity = "existingPlayers";
    String team,key,teamHome,teamGuest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster_add_existing_player);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_players);
        database = FirebaseDatabase.getInstance().getReference().child("Player");
        getIntentMain();

        PlayerAdapter.OnPlayerClickListener playerClickListener = new PlayerAdapter.OnPlayerClickListener() {
            @Override
            public void onPlayerClick(Player player, int position) {

                Toast.makeText(getApplicationContext(), "Player " + player.getNameFirst()+" "+player.getNameLast() + " was chosen",
                        Toast.LENGTH_SHORT).show();
            }
        };
        PlayerAdapter adapter = new PlayerAdapter(this, players,playerClickListener,keys,activity,team,key,teamHome,teamGuest);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(players.size()>0)players.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    keys.add(ds.getKey());
                    Player player =ds.getValue(Player.class);
                    assert player != null;
                    players.add(player);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        recyclerView.setAdapter(adapter);

    }

    private void getIntentMain() {
        Intent intent = getIntent();
        if (intent != null) {
            team = intent.getStringExtra("team");
            key = intent.getStringExtra("key");
            teamHome = intent.getStringExtra("game_home_team");
            teamGuest = intent.getStringExtra("game_guest_team");
        }
    }

    public void OnClickAdd(View view) {
        Intent intent = new Intent(".RosterAddNewPlayerActivity");
        intent.putExtra("team", team);
        intent.putExtra("key", key);
        intent.putExtra("game_home_team", teamHome);
        intent.putExtra("game_guest_team", teamGuest);
        startActivity(intent);
    }
}
