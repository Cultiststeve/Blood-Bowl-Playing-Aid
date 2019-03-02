package com.example.bloodbowlplayingaid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class TeamSetupActivity extends AppCompatActivity {

    // Recycler view for list of team
    private RecyclerView recyclerViewTeam;
    private RecyclerView.Adapter mAdapterTeam;
    private RecyclerView.LayoutManager layoutManagerTeam;

    String[] teamList = {"Player 1", "Player 2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_setup);

        recyclerViewTeam = findViewById(R.id.recylerViewTeam);
        recyclerViewTeam.setHasFixedSize(true);
        layoutManagerTeam = new LinearLayoutManager(this);
        recyclerViewTeam.setLayoutManager(layoutManagerTeam); // Linear layout manager

        mAdapterTeam = new TeamListAdapter(teamList);
        recyclerViewTeam.setAdapter(mAdapterTeam);


    }

    public void onBtnPlayPress(View view){
        Intent intent = new Intent(this, IngameActivity.class);
        startActivity(intent);
    }
}
