package com.example.bloodbowlplayingaid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TeamSetupActivity extends AppCompatActivity {

    private static final String TAG = "BBH_TeamSetupActivity";
    public static String intentExtraGameState = "EXTRA_GAME_STATE";

    // Recycler view for list of team
    private RecyclerView recyclerViewTeam;
    private RecyclerView.Adapter mAdapterTeam;
    private RecyclerView.LayoutManager layoutManagerTeam;

//    ArrayList<String> teamList = new ArrayList<>(); // List of player strings, e.g "Player 2"
    DataGameState dataGameState;

    // List of all button Id's currently "on" team
     List<Integer> buttonIdInTeam = new ArrayList<Integer>() {};

    private ArrayList<Button> playerButtons = new ArrayList<>();
    private static final List<Integer> PLAYER_BUTTON_IDS = Arrays.asList(
            R.id.btnPlayer1,
            R.id.btnPlayer2,
            R.id.btnPlayer3,
            R.id.btnPlayer4,
            R.id.btnPlayer5,
            R.id.btnPlayer6,
            R.id.btnPlayer7,
            R.id.btnPlayer8,
            R.id.btnPlayer9,
            R.id.btnPlayer10,
            R.id.btnPlayer11,
            R.id.btnPlayer12,
            R.id.btnPlayer13,
            R.id.btnPlayer14,
            R.id.btnPlayer15,
            R.id.btnPlayer16
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_setup);

        //Get playerNameList if returning from a game
        Intent intent = getIntent();
        if (intent != null){
            dataGameState = intent.getParcelableExtra(intentExtraGameState);
            if (dataGameState != null){
                Log.d(TAG, "onCreate: Got game state data. Player list: " + dataGameState.teamList.toString());

            } else {
                //Starting new game, create game data
                Log.i(TAG, "onCreate: Starting new game, creating game state data");
                dataGameState = new DataGameState();
            }
        } else {
            Log.e(TAG, "onCreate: No intent when creating. Somethings wrong.");
        }

        //Setup recycler view
        recyclerViewTeam = findViewById(R.id.recyclerViewTeam);
        recyclerViewTeam.setHasFixedSize(true);
        layoutManagerTeam = new LinearLayoutManager(this);
        recyclerViewTeam.setLayoutManager(layoutManagerTeam); // Linear layout manager

        mAdapterTeam = new TeamListAdapter(dataGameState.teamList);
        recyclerViewTeam.setAdapter(mAdapterTeam);

        //Setup player buttons
        for (int i=0; i<=15; i++){
            final Button current_button = findViewById(PLAYER_BUTTON_IDS.get(i));
            playerButtons.add(i, current_button);
            current_button.setBackgroundColor(getResources().getColor(R.color.colorNotTeam));

            final String strPlayerName = "Player " + Integer.toString(i + 1);
            current_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (buttonIdInTeam.contains(current_button.getId())){
                        // Button already on team, remove
                        Log.d(TAG, "onClick: Player already on team, removing");
                        current_button.setBackgroundColor(getResources().getColor(R.color.colorNotTeam));
                        buttonIdInTeam.remove(buttonIdInTeam.indexOf(current_button.getId()));

                        //Remove from team list and recyclerview
                        removePlayerFromList(strPlayerName);
                    } else {
                        //TODO is there already 11 players?
                        // Add button to team
                        current_button.setBackgroundColor(getResources().getColor(R.color.colorOnTeam));
                        buttonIdInTeam.add(current_button.getId());
                        //TODO update recyclerview
                        addPlayerToList(strPlayerName);
                    }
                }
            });
            //Check the team list to see if button should already be active (if returning from a game)
            if (dataGameState.teamList.contains(strPlayerName)){
                current_button.callOnClick();
            }
        }


    }

    public void onBtnPlayGame(View view){
        //Check 11 players on field to start game
        if (dataGameState.teamList.size() >11){
            Toast.makeText(this, "Team is too large, cannot start a game", Toast.LENGTH_SHORT).show();
            return;
        } else if (dataGameState.teamList.size() < 11) {
            Toast.makeText(this, "Team is too small, cannot start a game", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, IngameActivity.class);
        intent.putExtra(intentExtraGameState, dataGameState);
        startActivity(intent);
    }


    public void addPlayerToList(String player){
        if (dataGameState.teamList.contains(player)){
            Log.w(TAG, "addPlayerToList: Team list already contains player with that name. Will not re-add");
            return;
        }
        dataGameState.teamList.add(player);
        Collections.sort(dataGameState.teamList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String s1PlayerNumText = s1.replace("Player ", "");
                String s2PlayerNumText = s2.replace("Player ", "");
//                Log.d(TAG, "compare: Val1 / val2:" + s1PlayerNumText + " : " + s2PlayerNumText);
                Integer s1_int = Integer.parseInt(s1PlayerNumText);
                Integer s2_int = Integer.parseInt(s2PlayerNumText);
//                Log.d(TAG, "compare: s1/s2: " + s1_int + " : " + s2_int);
                return (s1_int-s2_int);
//                return 0;
            }
        });
        //Sort list
        mAdapterTeam.notifyDataSetChanged(); // All data could have changed if sorted
    }

    public void removePlayerFromList(String player){
        Log.d(TAG, "removePlayerFromList: index to remove: " + dataGameState.teamList.indexOf(player));
        //teamList is data the recycler view uses
        mAdapterTeam.notifyItemRemoved(dataGameState.teamList.indexOf(player));
        dataGameState.teamList.remove(player);
    }

    public void onBtnResetTeam(View view){
        dataGameState.teamList.clear();
        mAdapterTeam.notifyDataSetChanged();

        // Make sure all buttons are off
        for (int i=0; i<buttonIdInTeam.size(); i++){
            Button button = findViewById(buttonIdInTeam.get(i));
            button.setBackgroundColor(getResources().getColor(R.color.colorNotTeam));
        }
        buttonIdInTeam = new ArrayList<Integer>() {};


    }
}
