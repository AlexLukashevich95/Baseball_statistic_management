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

public class TeamAdapter  extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    interface OnTeamClickListener{
        void onTeamClick(Team team, int position);
    }

    Context mContext;
    private OnTeamClickListener onClickListener;
    private LayoutInflater inflater;
    private List<Team> teams;
    private List<String> keys;

    TeamAdapter(Context context, List<Team> teams, TeamAdapter.OnTeamClickListener onClickListener, List<String> keys) {
        mContext = context;
        this.onClickListener = onClickListener;
        this.teams = teams;
        this.inflater = LayoutInflater.from(context);
        this.keys = keys;
    }

    @NonNull
    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_view_teams, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.ViewHolder holder, int position) {
        Team team = teams.get(position);
        String key = keys.get(position);
        holder.nameView.setText(team.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onTeamClick(team, position);
                Intent intent = new Intent(".TeamDetailedActivity");
                intent.putExtra("keys", key);
                intent.putExtra("team_name",team.name);
                intent.putExtra("team_category",team.category);
                intent.putExtra("team_city",team.city);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView;
        ViewHolder(@NonNull View view) {
            super(view);
            nameView = (TextView) view.findViewById(R.id.name_team);
        }
    }
}
