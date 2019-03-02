package com.example.bloodbowlplayingaid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TeamSetupActivity extends AppCompatActivity {

    // Recycler view for list of team
    private RecyclerView recyclerViewTeam;
    private RecyclerView.Adapter mAdapterTeam;
    private RecyclerView.LayoutManager layoutManagerTeam;

    List<String> teamList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_setup);

        teamList.add("Test string 1");
        teamList.add("2");

        recyclerViewTeam = findViewById(R.id.recylerViewTeam);
        recyclerViewTeam.setHasFixedSize(true);
        layoutManagerTeam = new LinearLayoutManager(this);
        recyclerViewTeam.setLayoutManager(layoutManagerTeam); // Linear layout manager

        mAdapterTeam = new TeamListAdapter(teamList);
        recyclerViewTeam.setAdapter(mAdapterTeam);
    }

    public void onBtnPlayGame(View view){
        Intent intent = new Intent(this, IngameActivity.class);
        startActivity(intent);
    }

    public void onBtnAddMember(View view) {
        EditText editTextPlayerInput = findViewById(R.id.edtTxtPlayerInput);
        String new_player = "Player " + editTextPlayerInput.getText();
        teamList.add(new_player);
        mAdapterTeam.notifyItemInserted(teamList.size());

        //Clear the edit text for next entry
        editTextPlayerInput.setText("");
    }
}
