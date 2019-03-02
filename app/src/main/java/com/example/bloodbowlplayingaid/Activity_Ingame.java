package com.example.bloodbowlplayingaid;

import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class Activity_Ingame extends AppCompatActivity
    implements Fragment_Player_Card.OnFragmentInteractionListener,
        FragmentExtraButtons.OnFragmentInteractionListener,
        Fragment_Dialogue_New_turn.Fragment_Dialog_New_Turn_Listener {

    private static final String TAG = "BBH_Activity_Ingame";
    ArrayList<View> player_card_views = new ArrayList<>(12);
    ArrayList<Fragment_Player_Card> player_card_fragments = new ArrayList<>(12);
    FragmentExtraButtons fragmentExtraButtons;

    private Integer current_turn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            Fragment_Player_Card new_fragment = Fragment_Player_Card.newInstance(i, "test");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(player_card_views.get(i).getId(), new_fragment);
            transaction.commit();
            player_card_fragments.add(i, new_fragment); // Build an array of the fragments
        }

        // Set fragment for button draw
        View view_button_draw = findViewById(R.id.button_draw);
        fragmentExtraButtons = FragmentExtraButtons.newInstance("del", "del2");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(view_button_draw.getId(), fragmentExtraButtons);
        transaction.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialogFragment) {
        Log.d(TAG, "onDialogPositiveClick: New turn was selected");
        current_turn++;
        fragmentExtraButtons.incrementTurnCounter(current_turn);

    }
}
