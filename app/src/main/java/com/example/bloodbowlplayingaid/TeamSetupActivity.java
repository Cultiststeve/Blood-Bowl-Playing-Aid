package com.example.bloodbowlplayingaid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeamSetupActivity extends AppCompatActivity {

    private static final String TAG = "BBH_TeamSetupActivity";
    public static String extraPlayerList = "EXTRA_PLAYER_LIST";

    // Recycler view for list of team
    private RecyclerView recyclerViewTeam;
    private RecyclerView.Adapter mAdapterTeam;
    private RecyclerView.LayoutManager layoutManagerTeam;

    private EditText editTextPlayerInput;
    ArrayList<String> teamList = new ArrayList<>();

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

        recyclerViewTeam = findViewById(R.id.recyclerViewTeam);
        recyclerViewTeam.setHasFixedSize(true);
        layoutManagerTeam = new LinearLayoutManager(this);
        recyclerViewTeam.setLayoutManager(layoutManagerTeam); // Linear layout manager

        mAdapterTeam = new TeamListAdapter(teamList);
        recyclerViewTeam.setAdapter(mAdapterTeam);

        //Setup player buttons
        for (int i=0; i<=15; i++){

            final Button current_button = findViewById(PLAYER_BUTTON_IDS.get(i));
            playerButtons.add(i, current_button);
            current_button.setBackgroundColor(getResources().getColor(R.color.colorNotTeam));

            current_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (buttonIdInTeam.contains(current_button.getId())){
                        // Button already on team, remove
                        current_button.setBackgroundColor(getResources().getColor(R.color.colorNotTeam));
                        buttonIdInTeam.remove(buttonIdInTeam.indexOf(current_button.getId()));
                    } else {
                        //TODO is there already 11 players?
                        // Add button to team
                        current_button.setBackgroundColor(getResources().getColor(R.color.colorOnTeam));
                        buttonIdInTeam.add(current_button.getId());
                        //TODO update recyclerview

                    }
                }
            });
        }

    }

    public void onBtnPlayGame(View view){
        Intent intent = new Intent(this, IngameActivity.class);
        intent.putStringArrayListExtra(extraPlayerList, teamList);
        startActivity(intent);
    }

    public void onBtnAddMember(View view) {
        String textbox = editTextPlayerInput.getText().toString();
        if (textbox.equals("")){
            return; // No player number to add
        }
        if (Integer.parseInt(textbox) > 16){
            Toast.makeText(getApplicationContext(), "Can not have players with number over 16.", Toast.LENGTH_SHORT).show();
            return; // Dont add player over 16
        }
        String new_player = "Player " + editTextPlayerInput.getText();
        addPlayerToList(new_player);

    }

    public void addPlayerToList(String player){
        teamList.add(player);
        mAdapterTeam.notifyItemInserted(teamList.size());

        //Clear the edit text for next entry
        editTextPlayerInput.setText("");
    }
}
