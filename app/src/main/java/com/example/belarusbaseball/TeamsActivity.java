package com.example.belarusbaseball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TeamsActivity extends AppCompatActivity {

    DatabaseReference database;
    List<Team> teams = new ArrayList<>();
    FloatingActionButton btnAdd;
    List<String> keys = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_teams);
        database = FirebaseDatabase.getInstance().getReference().child("Team");

        TeamAdapter.OnTeamClickListener teamClickListener = new TeamAdapter.OnTeamClickListener() {
            @Override
            public void onTeamClick(Team team, int position) {

                Toast.makeText(getApplicationContext(), "Team" + team.getName() + " was chosen",
                        Toast.LENGTH_SHORT).show();
            }
        };
        TeamAdapter adapter = new TeamAdapter(this, teams,teamClickListener,keys);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(teams.size()>0)teams.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    keys.add(ds.getKey());
                    Team team =ds.getValue(Team.class);
                    assert team != null;
                    teams.add(team);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        recyclerView.setAdapter(adapter);

        btnAdd = (FloatingActionButton) findViewById(R.id.addTeamsButton);
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".TeamAdminActivity");
                        startActivity(intent);
                    }
                }
        );

    }
}