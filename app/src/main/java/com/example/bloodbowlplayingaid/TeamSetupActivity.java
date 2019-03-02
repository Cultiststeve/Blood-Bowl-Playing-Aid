package com.example.bloodbowlplayingaid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TeamSetupActivity extends AppCompatActivity {

    // Recycler view for list of team
    private RecyclerView recyclerViewTeam;
    private RecyclerView.Adapter mAdapterTeam;
    private RecyclerView.LayoutManager layoutManagerTeam;

    private EditText editTextPlayerInput;
    ArrayList<String> teamList = new ArrayList<String>();
    public static String extraPlayerList = "EXTRA_PLAYER_LIST";

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

        //Set enter listener for edit text
        editTextPlayerInput = findViewById(R.id.edtTxtPlayerInput);
        editTextPlayerInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Act same as button press
                    onBtnAddMember(v);

                    // Show keyboard again
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(v.getContext().INPUT_METHOD_SERVICE);
                    imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);

                    return false;
                }
                return false;
            }
        });

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
