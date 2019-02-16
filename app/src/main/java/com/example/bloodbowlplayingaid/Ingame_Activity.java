package com.example.bloodbowlplayingaid;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ingame_Activity extends AppCompatActivity
    implements PlayerCardFragment.OnFragmentInteractionListener{

    ArrayList<View> player_cards = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set buttons to player names
        player_cards.set(0, findViewById(R.id.player_1_card));
        player_cards.set(1, findViewById(R.id.player_2_card));

        for(int  i=0; i<=player_cards.size(); i++){
            Fragment new_fragment = new PlayerCardFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.player_1_card, new_fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            player_cards.set(i, findViewById(R.id.player_1_card));
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
