package com.example.belarusbaseball;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TournamentAdapter  extends RecyclerView.Adapter<TournamentAdapter.ViewHolder> {
    interface OnTournamentClickListener{
        void onTournamentClick(Tournament tournament, int position);
    }

    Context mContext;
    private OnTournamentClickListener onClickListener;
    private LayoutInflater inflater;
    private List<Tournament> tournaments;
    private List<String> keys;

    TournamentAdapter(Context context, List<Tournament> tournaments, TournamentAdapter.OnTournamentClickListener onClickListener, List<String> keys) {
        mContext = context;
        this.onClickListener = onClickListener;
        this.tournaments = tournaments;
        this.inflater = LayoutInflater.from(context);
        this.keys = keys;
    }

    @NonNull
    @Override
    public TournamentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_view_tournaments, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TournamentAdapter.ViewHolder holder, int position) {
        Tournament tournament = tournaments.get(position);
        String key = keys.get(position);
        holder.nameView.setText(tournament.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onTournamentClick(tournament, position);
                Intent intent = new Intent(".TournamentDetailedActivity");
                intent.putExtra("keys", key);
                intent.putExtra("tournament_name",tournament.name);
                intent.putExtra("tournament_category",tournament.category);
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return tournaments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView;
        ViewHolder(@NonNull View view) {
            super(view);
            nameView = (TextView) view.findViewById(R.id.name_tournament);
        }
    }
}


