package com.example.bloodbowlplayingaid;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class IngameActivity extends AppCompatActivity
    implements PlayerCardFragment.OnFragmentInteractionListener,
        ControlButtonsFragment.OnFragmentInteractionListener,
        NewTurnDialogueFragment.Fragment_Dialog_New_Turn_Listener {

    private static final String TAG = "BBH_Activity_Ingame";
    ArrayList<View> player_card_views = new ArrayList<>(12);
    ArrayList<PlayerCardFragment> player_card_fragments = new ArrayList<>(12);
    ControlButtonsFragment controlButtonsFragment;

    private Integer currentTurn = 0;
    private ArrayList<String> playerNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingame);

        //Get playerNameList
        Intent intent = getIntent();
        if (intent != null){
            playerNameList = intent.getStringArrayListExtra(TeamSetupActivity.extraPlayerList);
            Log.d(TAG, "onCreate: Got player list" + playerNameList.toString());
        }

        // Set buttons to player names
        player_card_views.add(0, findViewById(R.id.player_1_card));
        player_card_views.add(1, findViewById(R.id.player_2_card));
        player_card_views.add(2, findViewById(R.id.player_3_card));
        player_card_views.add(3, findViewById(R.id.player_4_card));
        player_card_views.add(4, findViewById(R.id.player_5_card));
        player_card_views.add(5, findViewById(R.id.player_6_card));
        player_card_views.add(6, findViewById(R.id.player_7_card));
        player_card_views.add(7, findViewById(R.id.player_8_card));
        player_card_views.add(8, findViewById(R.id.player_9_card));
        player_card_views.add(9, findViewById(R.id.player_10_card));
        player_card_views.add(10, findViewById(R.id.player_11_card));


        // Recreate each fragment with its info about what position its in
        for(int i = 0; i< player_card_views.size(); i++){
            
            String player_name;
            if (i < playerNameList.size()) {
                player_name = playerNameList.get(i);
            } else {
                player_name = "Player " + Integer.toString(i);
            }

            PlayerCardFragment new_fragment = PlayerCardFragment.newInstance(i, player_name);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(player_card_views.get(i).getId(), new_fragment);
            transaction.commit();
            player_card_fragments.add(i, new_fragment); // Build an array of the fragments
        }

        // Set fragment for button draw
        View view_button_draw = findViewById(R.id.button_draw);
        controlButtonsFragment = ControlButtonsFragment.newInstance("del", "del2");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(view_button_draw.getId(), controlButtonsFragment);
        transaction.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialogFragment) {
        Log.d(TAG, "onDialogPositiveClick: New turn was selected");
        newTurn();
    }

    private void newTurn() {
        if (currentTurn >= 16){
            Toast.makeText(this, "Game is over.",Toast.LENGTH_SHORT).show();
            return;
        }
        currentTurn++;
        update_fragment_turns();
    }

    @Override
    public void reduceTurn() {
        if (currentTurn == 0){
            Toast.makeText(this, "Cant go back past turn 0.", Toast.LENGTH_SHORT).show();
            return;
        }
        currentTurn--;
        update_fragment_turns();
    }

    @Override
    public void closeIngame() {
        // Control button fragment button to close has been pressed. Do the thing
        Intent intent = new Intent(this, TeamSetupActivity.class);
        startActivity(intent);
    }

    @Override
    public void resetGame() {
        currentTurn = 0;
        update_fragment_turns();

    }

    private void update_fragment_turns(){
        controlButtonsFragment.incrementTurnCounter(currentTurn);
        for (int i=0; i<player_card_fragments.size(); i++){
            player_card_fragments.get(i).newTurn();
        }
    }
}
