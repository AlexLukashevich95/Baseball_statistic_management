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

public class TournamentsActivity extends AppCompatActivity {

    DatabaseReference database;
    List<Tournament> tournaments = new ArrayList<>();
    FloatingActionButton btnAdd;
    List<String> keys = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournaments);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_tournaments);
        database = FirebaseDatabase.getInstance().getReference().child("Tournament");

        TournamentAdapter.OnTournamentClickListener tournamentClickListener = new TournamentAdapter.OnTournamentClickListener() {
            @Override
            public void onTournamentClick(Tournament tournament, int position) {

                Toast.makeText(getApplicationContext(), "Tournament" + tournament.getName() + " was chosen",
                        Toast.LENGTH_SHORT).show();
            }
        };
        TournamentAdapter adapter = new TournamentAdapter(this, tournaments,tournamentClickListener,keys);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(tournaments.size()>0)tournaments.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    keys.add(ds.getKey());
                    Tournament tournament =ds.getValue(Tournament.class);
                    assert tournament != null;
                    tournaments.add(tournament);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        recyclerView.setAdapter(adapter);

        btnAdd = (FloatingActionButton) findViewById(R.id.addTournamentsButton);
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".TournamentAdminActivity");
                        startActivity(intent);
                    }
                }
        );

    }
}
