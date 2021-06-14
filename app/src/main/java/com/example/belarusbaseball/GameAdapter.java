package com.example.belarusbaseball;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GameAdapter  extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
    interface OnGameClickListener{
        void onGameClick(Game game, int position);
    }

    Context mContext;
    private OnGameClickListener onClickListener;
    private LayoutInflater inflater;
    private List<Game> games;
    private List<String> keys;


    GameAdapter(Context context, List<Game> games, GameAdapter.OnGameClickListener onClickListener, List<String> keys) {
        mContext = context;
        this.onClickListener = onClickListener;
        this.games = games;
        this.inflater = LayoutInflater.from(context);
        this.keys = keys;
    }


    @Override
    public GameAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_view_games, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GameAdapter.ViewHolder holder, int position) {
        Game game = games.get(position);
        String key = keys.get(position);
        holder.nameHomeView.setText(game.getNameHomeTeam());
        holder.nameGuestView.setText(game.getNameGuestTeam());
        holder.dateView.setText(game.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onGameClick(game, position);
                Intent intent = new Intent(".GameDetailedActivity");
                intent.putExtra("keys", key);
                intent.putExtra("game_home_team", game.nameHomeTeam);
                intent.putExtra("game_guest_team", game.nameGuestTeam);
                intent.putExtra("game_date", game.date);
                intent.putExtra("game_tournament", game.tournament);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameHomeView;
        final TextView nameGuestView;
        final TextView dateView;
        ViewHolder(View view) {
            super(view);
            nameHomeView = (TextView) view.findViewById(R.id.name_home_team);
            nameGuestView = (TextView) view.findViewById(R.id.name_guest_team);
            dateView = (TextView) view.findViewById(R.id.dateGame);
        }
    }
}
