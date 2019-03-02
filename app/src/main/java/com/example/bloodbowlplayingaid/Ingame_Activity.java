package com.example.bloodbowlplayingaid;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Ingame_Activity extends AppCompatActivity
    implements PlayerCardFragment.OnFragmentInteractionListener{

    ArrayList<View> player_card_views = new ArrayList<>(12);
    ArrayList<PlayerCardFragment> player_card_fragments = new ArrayList<>(12);


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
        player_card_views.add(11, findViewById(R.id.player_12_card));


        // Recreate each fragment with its info about what position its in
        for(int i = 0; i< player_card_views.size(); i++){
            PlayerCardFragment new_fragment = PlayerCardFragment.newInstance(i, "test");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(player_card_views.get(i).getId(), new_fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            player_card_fragments.add(i, new_fragment);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
