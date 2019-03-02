package com.example.bloodbowlplayingaid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.TeamViewHolder> {

    private List<String> teamDataset;

    public static class TeamViewHolder extends RecyclerView.ViewHolder {
        // Each data item is a string
        public TextView teamMemberTextView;

        // Constructor
        public TeamViewHolder(TextView view) {
            super(view);
            teamMemberTextView = view;
        }
    }

    // Constuctor that takes team member list
    public TeamListAdapter(List<String> newDataset) {
        teamDataset = newDataset;
    }

    // Create new Views
    @Override
    public TeamListAdapter.TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Create new view
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_team_member, parent, false);
        TeamViewHolder teamViewHolder = new TeamViewHolder(textView);
        return teamViewHolder;
    }

    // Replace contents of a view
    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        // Replace text of element with data at that position
        holder.teamMemberTextView.setText(teamDataset.get(position));
    }

    // Return size of dataset
    @Override
    public int getItemCount() {
        return teamDataset.size();
    }
}
