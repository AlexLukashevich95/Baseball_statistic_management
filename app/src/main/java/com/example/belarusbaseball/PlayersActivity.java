package com.example.belarusbaseball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PlayersActivity extends AppCompatActivity {

    DatabaseReference database;
    List<Player> players = new ArrayList<>();
    FloatingActionButton btnAdd;
    List<String> keys = new ArrayList<>();
    String activity = "players";
    String team = null;
    String key = null;
    String teamHome = null;
    String teamGuest = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_players);
        database = FirebaseDatabase.getInstance().getReference().child("Player");

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


        btnAdd = (FloatingActionButton) findViewById(R.id.addPlayersButton);
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".PlayerAdminActivity");
                        startActivity(intent);
                    }
                }
        );

    }
}
