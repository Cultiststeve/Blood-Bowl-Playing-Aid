package com.example.bloodbowlplayingaid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayerCardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayerCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerCardFragment extends Fragment {
    private static final String TAG = "BBH_PlayerCardFragment";
    private static final String ARG_CARD_POSITION = "CARD_POSITION";
    private static final String ARG_PLAYER_NAME = "PLAYER_NAME";

    private Button btnPlayer;
    private Button btnCasualty;

    private Integer cardPostion;
    private String playerName;
    private boolean casualty = false;
    public Boolean hasPlayed = false;

    private OnFragmentInteractionListener mListener;


    public PlayerCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param card_number Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayerCardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayerCardFragment newInstance(Integer card_number, String param2) {
        Log.d(TAG, "newInstance() called with: cardPostion = [" + card_number + "], param2 = [" + param2 + "]");

        PlayerCardFragment fragment = new PlayerCardFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CARD_POSITION, card_number);
        args.putString(ARG_PLAYER_NAME, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Log.d(TAG, "onCreate: Setting card number to " + getArguments().getInt(ARG_CARD_POSITION));
            cardPostion = getArguments().getInt(ARG_CARD_POSITION);
            playerName = getArguments().getString(ARG_PLAYER_NAME);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called with: inflater = [" + inflater + "], container = [" + container + "], savedInstanceState = [" + savedInstanceState + "]");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_player_card, container, false);

        btnPlayer = (Button) rootView.findViewById(R.id.btnPlayerCard); // Get button, store in fragment variable
        btnCasualty = (Button) rootView.findViewById(R.id.btnCasualty);

        if (cardPostion != null){
            Integer display_num  = cardPostion +1;
            btnPlayer.setText(playerName);
            Log.d(TAG, "onCreateView: cardnum = " + cardPostion.toString());
        } else {
            Log.d(TAG, "onCreateView: Card num is null");
        }

        return rootView;
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart() called");
        super.onStart();

        btnPlayer.setOnClickListener(new View.OnClickListener() { // Set button behaviour
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick() called with: v = [" + v + "]");
                if (hasPlayed){
                    Toast.makeText(getContext(), "Player has already played", Toast.LENGTH_SHORT).show();
                } else {
                    hasPlayed = true;
                    btnPlayer.setBackgroundColor(getResources().getColor(R.color.colorPlayed));
                }
            }
        });

        btnPlayer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                hasPlayed = false;
                btnPlayer.setBackgroundColor(getResources().getColor(R.color.colorNotPlayed));
                return true;
            }
        });

        btnCasualty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Casualty clicked, currently " + casualty);
                if (casualty){
                    casualty = false;
                    if (hasPlayed){
                        btnPlayer.setBackgroundColor(getResources().getColor(R.color.colorPlayed));
                    } else {
                        btnPlayer.setBackgroundColor(getResources().getColor(R.color.colorNotPlayed));
                    }
                } else {
                    casualty = true;
                    btnPlayer.setBackgroundColor(getResources().getColor(R.color.colorPlayerCasuallty));
                }
                btnPlayer.setEnabled(!casualty); //Not clickable if casualty
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void onBtnCasualty(View view){

    }

    public void newTurn(){
        //Reset color
        hasPlayed = false;
        if (!casualty){ // Only reset color if not a casualty
            btnPlayer.setBackgroundColor(getResources().getColor(R.color.colorNotPlayed));
        }
    }

    public void resetCard(){
        hasPlayed = false;
        casualty = false;
        btnPlayer.setBackgroundColor(getResources().getColor(R.color.colorNotPlayed));
    }
}
