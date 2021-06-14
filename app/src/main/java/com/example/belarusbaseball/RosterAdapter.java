package com.example.belarusbaseball;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RosterAdapter  extends RecyclerView.Adapter<RosterAdapter.ViewHolder> {

    Context mContext;
    private LayoutInflater inflater;
    private List<Roster> rosters;
    private List<String> keys;

    RosterAdapter(Context context, List<Roster> rosters,List<String> keys) {
        mContext = context;
        this.rosters = rosters;
        this.inflater = LayoutInflater.from(context);
        this.keys = keys;
    }

    @NonNull
    @Override
    public RosterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_view_roster, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RosterAdapter.ViewHolder holder, int position) {
        Roster roster = rosters.get(position);
        holder.nameLastView.setText(roster.getNameLast());
        holder.nameFirstView.setText(roster.getNameFirst());
        holder.position.setText(roster.getPosition());
        holder.batPosition.setText(roster.getBatPosition());
    }


    @Override
    public int getItemCount() {
        return rosters.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameLastView;
        final TextView nameFirstView;
        final TextView position;
        final TextView batPosition;
        ViewHolder(View view) {
            super(view);
            nameLastView = (TextView) view.findViewById(R.id.name_last_player);
            nameFirstView = (TextView) view.findViewById(R.id.name_first_player);
            position = (TextView) view.findViewById(R.id.player_position_view);
            batPosition = (TextView) view.findViewById(R.id.player_batting_position_view);
        }
    }
}
