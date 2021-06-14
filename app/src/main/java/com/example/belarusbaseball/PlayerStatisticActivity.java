package com.example.belarusbaseball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatisticActivity extends AppCompatActivity {

    List<PlayerStatistic> statistics = new ArrayList<>();
    private String nameFirst, nameLast;
    private TextView playerName;
    List<String> keys = new ArrayList<>();



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_player);
        playerName = findViewById(R.id.playerLastName);
        Intent intent = getIntent();
        nameFirst = intent.getStringExtra("player_name_first");
        nameLast = intent.getStringExtra("player_name_last");
        playerName.setText(nameFirst+"  "+nameLast);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_players_roster);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Player").child(nameLast+" "+nameFirst);
        PlayerStatisticAdapter adapter = new PlayerStatisticAdapter(this, statistics);
        database.child("Player Statistic").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (statistics.size() > 0) statistics.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    //keys.add(ds.getKey());
                    PlayerStatistic playerStatistic = ds.getValue(PlayerStatistic.class);
                    statistics.add(playerStatistic);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        recyclerView.setAdapter(adapter);
    }

}
