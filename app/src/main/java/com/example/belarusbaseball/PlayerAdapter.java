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

public class PlayerAdapter  extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    interface OnPlayerClickListener{
        void onPlayerClick(Player player, int position);
    }

    Context mContext;
    private OnPlayerClickListener onClickListener;
    private LayoutInflater inflater;
    private List<Player> players;
    private List<String> keys;
    private String activity,team,keyTeam,teamHome,teamGuest;

    PlayerAdapter(Context context, List<Player> players, PlayerAdapter.OnPlayerClickListener onClickListener, List<String> keys, String activity,String team,String keyTeam,String teamHome,String teamGuest) {
        mContext = context;
        this.onClickListener = onClickListener;
        this.players = players;
        this.inflater = LayoutInflater.from(context);
        this.keys = keys;
        this.activity = activity;
        this.team = team;
        this.keyTeam = keyTeam;
        this.teamHome = teamHome;
        this.teamGuest=teamGuest;
    }

    @NonNull
    @Override
    public PlayerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_view_players, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PlayerAdapter.ViewHolder holder, int position) {
        Player player = players.get(position);
        String key = keys.get(position);
        holder.nameLastView.setText(player.getNameLast());
        holder.nameFirstView.setText(player.getNameFirst());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onPlayerClick(player, position);
                if(activity == "players"){
                    Intent intent = new Intent(".PlayerDetailedActivity");
                    intent.putExtra("keys", key);
                    intent.putExtra("player_name_first", player.nameFirst);
                    intent.putExtra("player_name_last",player.nameLast);
                    intent.putExtra("player_age", player.age);
                    mContext.startActivity(intent);
                }
                if(activity=="existingPlayers"){
                    Intent intent = new Intent(".RosterAddNewPlayerActivity");
                    intent.putExtra("key", keyTeam);
                    intent.putExtra("player_name_first", player.nameFirst);
                    intent.putExtra("player_name_last",player.nameLast);
                    intent.putExtra("player_age", player.age);
                    intent.putExtra("team", team);
                    intent.putExtra("game_home_team", teamHome);
                    intent.putExtra("game_guest_team", teamGuest);
                    mContext.startActivity(intent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return players.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameLastView;
        final TextView nameFirstView;
        ViewHolder(View view) {
            super(view);
            nameLastView = (TextView) view.findViewById(R.id.name_last_player);
            nameFirstView = (TextView) view.findViewById(R.id.name_first_player);
        }
    }
}

