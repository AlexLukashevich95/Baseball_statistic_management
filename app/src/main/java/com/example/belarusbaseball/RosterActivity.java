package com.example.belarusbaseball;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

public class RosterActivity extends AppCompatActivity {
    private String teamHome,teamGuest,date;
    private String teamH = "Home";
    private String teamG = "Guest";
    private TextView teamN;
    DatabaseReference database;
    DatabaseReference database1;
    List<Roster> rosters = new ArrayList<>();
    List<String> keys = new ArrayList<>();
    FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rosters);
        getIntentMain();
        teamN = findViewById(R.id.teamNameChosen);
        teamN.setText(teamHome);
        btnAdd = (FloatingActionButton) findViewById(R.id.addRosterPlayersButton);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_players_roster);
        database = FirebaseDatabase.getInstance().getReference().child("Game").child(teamHome +" VS "+teamGuest).child("Roster Home");
        RosterAdapter adapter = new RosterAdapter(this, rosters,keys);
        database.orderByChild("batPosition").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(rosters.size()>0)rosters.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    keys.add(ds.getKey());
                    Roster roster =ds.getValue(Roster.class);
                    assert roster != null;
                    rosters.add(roster);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".RosterAddExistingPlayerActivity");
                        intent.putExtra("team", teamH);
                        intent.putExtra("game_home_team", teamHome);
                        intent.putExtra("game_guest_team", teamGuest);
                        startActivity(intent);
                    }
                }
        );

    }

    private void getIntentMain(){
        Intent intent = getIntent();
        if (intent != null) {
            teamHome = intent.getStringExtra("game_home_team");
            teamGuest = intent.getStringExtra("game_guest_team");
            date = intent.getStringExtra("date");
        }
    }


    public void OnClickHome(View view) {
        teamN = findViewById(R.id.teamNameChosen);
        teamN.setText(teamHome);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_players_roster);
        database = FirebaseDatabase.getInstance().getReference().child("Game").child(teamHome +" VS "+teamGuest).child("Roster Home");
        RosterAdapter adapter = new RosterAdapter(this, rosters,keys);
        database.orderByChild("batPosition").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(rosters.size()>0)rosters.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    keys.add(ds.getKey());
                    Roster roster =ds.getValue(Roster.class);
                    assert roster != null;
                    rosters.add(roster);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".RosterAddExistingPlayerActivity");
                        intent.putExtra("team", teamH);
                        intent.putExtra("date",date);
                        intent.putExtra("game_home_team", teamHome);
                        intent.putExtra("game_guest_team", teamGuest);
                        startActivity(intent);
                    }
                }
        );
    }

    public void OnClickDone(View view) {
        Intent intent = new Intent(".GameManagingStatistic");
        intent.putExtra("game_home_team", teamHome);
        intent.putExtra("game_guest_team", teamGuest);
        intent.putExtra("game_date",date);
        startActivity(intent);
    }

    public void OnClickGuest(View view) {
        teamN = findViewById(R.id.teamNameChosen);
        teamN.setText(teamGuest);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_players_roster);
        database1 = FirebaseDatabase.getInstance().getReference().child("Game").child(teamHome +" VS "+teamGuest).child("Roster Guest");
        RosterAdapter adapter = new RosterAdapter(this, rosters,keys);
        database1.orderByChild("batPosition").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(rosters.size()>0)rosters.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    keys.add(ds.getKey());
                    Roster roster =ds.getValue(Roster.class);
                    assert roster != null;
                    rosters.add(roster);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".RosterAddExistingPlayerActivity");
                        intent.putExtra("team", teamG);
                        intent.putExtra("game_home_team", teamHome);
                        intent.putExtra("game_guest_team", teamGuest);
                        startActivity(intent);
                    }
                }
        );
    }
}

