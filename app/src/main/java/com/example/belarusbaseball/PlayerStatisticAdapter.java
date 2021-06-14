package com.example.belarusbaseball;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayerStatisticAdapter  extends RecyclerView.Adapter<PlayerStatisticAdapter.ViewHolder> {

    Context mContext;
    private LayoutInflater inflater;
    private List<PlayerStatistic> players;

    PlayerStatisticAdapter(Context context, List<PlayerStatistic> players) {
        mContext = context;
        this.players = players;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PlayerStatisticAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_view_players_statistic, parent, false);
        return new ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PlayerStatisticAdapter.ViewHolder holder, int position) {
        PlayerStatistic playerStatistic = players.get(position);
        holder.date.setText(playerStatistic.getDate());
        holder.gameCount.setText(Integer.toString(playerStatistic.getG()));
        holder.ab.setText(Integer.toString(playerStatistic.getAB()));
        holder.h.setText(Integer.toString(playerStatistic.getH()));
        holder.rbi.setText(Integer.toString(playerStatistic.getR()));
        holder.bb.setText(Integer.toString(playerStatistic.getBB()));
        holder.so.setText(Integer.toString(playerStatistic.getSO()));
        holder.avg.setText(Double.toString(playerStatistic.getAVG()));
    }


    @Override
    public int getItemCount() {
        return players.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView date;
        final TextView gameCount;
        final TextView ab;
        final TextView h;
        final TextView rbi;
        final TextView bb;
        final TextView so;
        final TextView avg;
        ViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date);
            gameCount = (TextView) view.findViewById(R.id.gamesPlayed);
            ab = (TextView) view.findViewById(R.id.atBats);
            h = (TextView) view.findViewById(R.id.hits);
            rbi = (TextView) view.findViewById(R.id.runs);
            bb = (TextView) view.findViewById(R.id.baseOnBalls);
            so = (TextView) view.findViewById(R.id.strikeOuts);
            avg = (TextView) view.findViewById(R.id.average);
        }
    }
}
