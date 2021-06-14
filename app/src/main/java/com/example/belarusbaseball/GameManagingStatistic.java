package com.example.belarusbaseball;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GameManagingStatistic extends AppCompatActivity {

    private int scoreHome = 0;
    private int scoreGuest = 0;
    private int inning = 1;
    private int inningPart = 1;
    private int hitsH = 0;
    private int hitsG = 0;
    private final int gameCount = 1;
    private double avg;
    private int outs = 0;
    private int strikes = 0;
    private int balls = 0;
    private int basesLoaded = 0;
    private int positionHome = 0;
    private int positionGuest = 0;


    private TextView gameTeamHome,gameTeamGuest,gameScoreHome,gameScoreGuest,gameInning,outsInGame,strikesInGame,ballsInGame,hitsHome,hitsGuest;
    private String teamHome;
    private String teamGuest;
    private String date;
    List<Roster> rosterHome = new ArrayList<>();
    List<Roster> rosterGuest = new ArrayList<>();
    List<String> keysRosterHome = new ArrayList<>();
    List<String> keysRosterGuest = new ArrayList<>();
    List<PlayerStatistic> playerStatisticsHome =new ArrayList<>();
    List<PlayerStatistic> playerStatisticsGuest =new ArrayList<>();
    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_managing);
        gameTeamHome = findViewById(R.id.gameHomeTeam);
        gameTeamGuest = findViewById(R.id.gameGuestTeam);
        gameScoreHome = findViewById(R.id.gameScoreHome);
        gameScoreGuest = findViewById(R.id.gameScoreGuest);
        gameInning = findViewById(R.id.gameInning);
        outsInGame = findViewById(R.id.outs);
        strikesInGame = findViewById(R.id.strikes);
        ballsInGame = findViewById(R.id.balls);
        hitsHome = findViewById(R.id.hits_home);
        hitsGuest = findViewById(R.id.hits_guest);
        database = FirebaseDatabase.getInstance().getReference().child("Game").child(teamHome+" VS "+teamGuest);
        getIntentMain();
        readRoster();
    }

    @SuppressLint("SetTextI18n")
    private void getIntentMain() {
        Intent intent = getIntent();
        if (intent != null) {
            gameTeamHome.setText(intent.getStringExtra("game_home_team"));
            gameTeamGuest.setText(intent.getStringExtra("game_guest_team"));
            date = intent.getStringExtra("game_date");
            outsInGame.setText(outs+" outs");
            strikesInGame.setText(strikes + " strikes");
            ballsInGame.setText(balls + " balls");
            hitsHome.setText(hitsH + " hits");
            hitsGuest.setText(hitsG + " hits");
            gameInning.setText(inning+"  inning");
            teamHome = gameTeamHome.getText().toString();
            teamGuest = gameTeamGuest.getText().toString();
        }
    }
    private void readRoster(){
        int ab = 0;
        int runPlayer = 0;
        int bb = 0;
        int h = 0;
        int strikeOut = 0;
        avg = 0.000;
        database = FirebaseDatabase.getInstance().getReference().child("Game").child(teamHome+" VS "+teamGuest);
        database.child("Roster Home").orderByChild("batPosition").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(rosterHome.size()>0)rosterHome.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    keysRosterHome.add(ds.getKey());
                    Roster roster =ds.getValue(Roster.class);
                    assert roster != null;
                    rosterHome.add(roster);
                    PlayerStatistic playerStatistic = new PlayerStatistic(roster.getNameFirst(),
                            roster.getNameLast(),
                            date,
                            gameCount,
                            ab,
                            runPlayer,
                            h,
                            bb,
                            strikeOut,
                            avg);
                    playerStatisticsHome.add(playerStatistic);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        database.child("Roster Guest").orderByChild("batPosition").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(rosterGuest.size()>0)rosterGuest.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    keysRosterGuest.add(ds.getKey());
                    Roster roster =ds.getValue(Roster.class);
                    assert roster != null;
                    rosterGuest.add(roster);
                    PlayerStatistic playerStatistic = new PlayerStatistic(roster.getNameFirst(),
                            roster.getNameLast(),
                            date,
                            gameCount,
                            ab,
                            runPlayer,
                            h,
                            bb,
                            strikeOut,
                            avg);
                    playerStatisticsGuest.add(playerStatistic);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
    private void calculateAvg(PlayerStatistic playerStatistic){
        int ab = playerStatistic.getAB();
        int h = playerStatistic.getH();
        avg  = (double) h / (double) ab;
        playerStatistic.setAVG(avg);
    }

    private void strikeOutPlay(){
        if(inningPart%2==0) {
            String key = keysRosterHome.get(positionHome);
            PlayerStatistic playerStatistic = playerStatisticsHome.get(positionHome);
            int ab = playerStatistic.getAB();
            ab++;
            int strikeOut=playerStatistic.getSO();
            strikeOut++;
            playerStatistic.setAB(ab);
            playerStatistic.setSO(strikeOut);
            setStatisticData(key,inningPart);
        }
        else if(inningPart%2==1){
            String key = keysRosterGuest.get(positionGuest);
            PlayerStatistic playerStatistic = playerStatisticsGuest.get(positionGuest);
            int ab = playerStatistic.getAB();
            ab++;
            int strikeOut=playerStatistic.getSO();
            strikeOut++;
            playerStatistic.setAB(ab);
            playerStatistic.setSO(strikeOut);
            setStatisticData(key,inningPart);
        }
        outPlay();
    }


    @SuppressLint("SetTextI18n")
    private void outPlay(){
        if(inningPart%2==0) {
            String key = keysRosterHome.get(positionHome);
            PlayerStatistic playerStatistic = playerStatisticsHome.get(positionHome);
            int ab = playerStatistic.getAB();
            ab++;
            playerStatistic.setAB(ab);
            gameInning.setText(inning + "  inning");
            setStatisticData(key,inningPart);
            positionHome++;
        }
        else if(inningPart%2==1){
            String key = keysRosterGuest.get(positionGuest);
            PlayerStatistic playerStatistic = playerStatisticsGuest.get(positionGuest);
            int ab = playerStatistic.getAB();
            ab++;
            playerStatistic.setAB(ab);
            setStatisticData(key,inningPart);
            positionGuest++;
        }
        if(positionHome==9)positionHome=0;
    }

    private void bbPlay(){
        if(inningPart%2==0) {
            String key = keysRosterHome.get(positionHome);
            PlayerStatistic playerStatistic = playerStatisticsHome.get(positionHome);
            int bb = playerStatistic.getBB();
            bb++;
            playerStatistic.setBB(bb);
            setStatisticData(key, inningPart);
            positionHome++;
        }
        else if(inningPart%2==1) {
            String key = keysRosterGuest.get(positionGuest);
            PlayerStatistic playerStatistic = playerStatisticsGuest.get(positionGuest);
            int bb = playerStatistic.getBB();
            bb++;
            playerStatistic.setBB(bb);
            setStatisticData(key, inningPart);
            positionGuest++;
        }
        if(positionHome==9)positionHome=0;
    }



    @SuppressLint("SetTextI18n")
    private void hitPlayHome(){
        String key = keysRosterHome.get(positionHome);
        PlayerStatistic playerStatistic = playerStatisticsHome.get(positionHome);
        int runPlayer = playerStatistic.getR();
        int h = playerStatistic.getH();
        int ab = playerStatistic.getAB();
        avg = 0.000;
        hitsH++;
        hitsHome.setText(hitsH + " hits");
        balls=0;
        ballsInGame.setText(balls + " balls");
        strikes = 0;
        strikesInGame.setText(strikes + " strikes");
        h++;
        playerStatistic.setH(h);
        ab++;
        playerStatistic.setAB(ab);
        if(basesLoaded==3){
            runPlayer++;
            playerStatistic.setR(runPlayer);
        }
        setStatisticData(key,inningPart);
        positionHome++;
        if(positionHome==9)positionHome=0;
    }

    @SuppressLint("SetTextI18n")
    private void hitPlayGuest(){
        String key = keysRosterGuest.get(positionGuest);
        PlayerStatistic playerStatistic = playerStatisticsGuest.get(positionGuest);
        int runPlayer = playerStatistic.getR();
        int h=playerStatistic.getH();
        int ab = playerStatistic.getAB();
        avg = 0.000;
        hitsG++;
        hitsGuest.setText(hitsG + " hits");
        balls = 0;
        ballsInGame.setText(balls + " balls");
        strikes = 0;
        strikesInGame.setText(strikes + " strikes");
        h++;
        playerStatistic.setH(h);
        ab++;
        playerStatistic.setAB(ab);
        if(basesLoaded==3){
            runPlayer++;
            playerStatistic.setR(runPlayer);
        }
        setStatisticData(key,inningPart);
        positionGuest++;
        if(positionGuest==9)positionGuest=0;
    }

    private void setStatisticData(String key, int inningPart){
        if(inningPart%2==1) {
            PlayerStatistic playerStatistic = playerStatisticsGuest.get(positionGuest);
            calculateAvg(playerStatistic);
            database.child("Roster Guest").child(key).child("Player Statistic").setValue(playerStatistic);
        }
        else if(inningPart%2==0) {
            PlayerStatistic playerStatistic = playerStatisticsHome.get(positionHome);
            calculateAvg(playerStatistic);
            database.child("Roster Home").child(key).child("Player Statistic").setValue(playerStatistic);
        }
    }


    public void onClickRedo(View view) {
    }

    public void onClickUndo(View view) {
    }

    public void onClickHit(View view) {
        if(inningPart%2==0) {
            if (basesLoaded == 0) {
                basesLoaded=1;
                hitPlayHome();
            } else if (basesLoaded == 1) {
                basesLoaded = 2;
                hitPlayHome();
            } else if (basesLoaded == 2) {
                basesLoaded = 3;
                hitPlayHome();
            } else if (basesLoaded == 3) {
                hitPlayHome();
                scoreHome++;
                gameScoreHome.setText(String.valueOf(scoreHome));
            }
        }
        else if(inningPart%2==1){
            if (basesLoaded == 0) {
                basesLoaded=1;
                hitPlayGuest();
            } else if (basesLoaded == 1) {
                basesLoaded = 2;
                hitPlayGuest();
            } else if (basesLoaded == 2) {
                basesLoaded = 3;
                hitPlayGuest();
            } else if (basesLoaded == 3) {
                hitPlayGuest();
                scoreGuest++;
                gameScoreGuest.setText(String.valueOf(scoreGuest));
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void onClickOut(View view) {
        if(inning<10){
            if(outs<2){
                outs++;
                balls=0;
                ballsInGame.setText(balls + " balls");
                strikes = 0;
                strikesInGame.setText(strikes + " strikes");
                outsInGame.setText(outs+" outs");
                outPlay();

            }
            else if(outs==2){
                outs=0;
                basesLoaded=0;
                balls=0;
                ballsInGame.setText(balls + " balls");
                strikes = 0;
                strikesInGame.setText(strikes + " strikes");
                outsInGame.setText(outs+" outs");
                outPlay();
                if(inningPart%2==0) {
                    inningPart++;
                    inning++;
                    gameInning.setText(inning + "  inning");
                }
                else if(inningPart%2==1){
                    inningPart++;
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void onClickBall(View view) {
        if(balls<3) {
            balls++;
            ballsInGame.setText(balls + " balls");
        }
        else if(balls==3){
                if (basesLoaded == 0) {
                    bbPlay();
                    basesLoaded = 1;
                }
                else if (basesLoaded == 1) {
                    bbPlay();
                    basesLoaded = 2;
                }
                else if (basesLoaded == 2) {
                    bbPlay();
                    basesLoaded = 3;
                }
                else if (basesLoaded == 3) {
                    bbPlay();
                    scoreHome++;
                    gameScoreHome.setText(String.valueOf(scoreHome));
                    if(inningPart%2==0){bbPlay();}
                    else if(inningPart%2==1) {
                        bbPlay();
                        scoreHome++;
                        gameScoreHome.setText(String.valueOf(scoreHome));
                }
            }

            balls=0;
            ballsInGame.setText(balls + " balls");
            strikes = 0;
            strikesInGame.setText(strikes + " strikes");
        }
    }

    @SuppressLint("SetTextI18n")
    public void onClickStrike(View view) {
        if (inning < 10) {
            if (outs < 2) {
                if (strikes < 2) {
                    strikes++;
                    strikesInGame.setText(strikes + " strikes");
                } else if (strikes == 2) {
                    outs++;
                    strikes = 0;
                    balls=0;
                    ballsInGame.setText(balls + " balls");
                    outsInGame.setText(outs+" outs");
                    strikesInGame.setText(strikes + " strikes");
                    strikeOutPlay();
                }
            } else if (outs == 2) {
                if (strikes < 2) {
                    strikes++;
                    strikesInGame.setText(strikes + " strikes");
                } else if (strikes == 2) {
                    strikes = 0;
                    outs = 0;
                    basesLoaded=0;
                    balls=0;
                    ballsInGame.setText(balls + " balls");
                    outsInGame.setText(outs+" outs");
                    strikesInGame.setText(strikes + " strikes");
                    strikeOutPlay();
                    if (inningPart % 2 == 0) {
                        inningPart++;
                        inning++;
                        gameInning.setText(inning+"  inning");}
                } else if (inningPart % 2 == 1) {inningPart++;}
            }
        }
    }
    public void onClickSave(View view) {
        DatabaseReference databaseNewPlayer = FirebaseDatabase.getInstance().getReference().child("Player");
        for(int i=0;i<playerStatisticsGuest.size()-1;i++){
            PlayerStatistic playerStatistic = playerStatisticsGuest.get(i);
            Roster roster = rosterGuest.get(i);
            databaseNewPlayer.child(roster.getNameLast() + " " + roster.getNameFirst()).child("Player Statistic").push().setValue(playerStatistic);
        }
        for(int j=0;j<playerStatisticsHome.size()-1;j++) {
            PlayerStatistic playerStatistic = playerStatisticsHome.get(j);
            Roster roster = rosterHome.get(j);
            databaseNewPlayer.child(roster.getNameLast() + " " + roster.getNameFirst()).child("Player Statistic").push().setValue(playerStatistic);
        }
    }
}
