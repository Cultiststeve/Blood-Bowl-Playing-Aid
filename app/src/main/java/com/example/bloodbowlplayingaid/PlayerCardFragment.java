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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayerCardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayerCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerCardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "BBH_PlayerCardFragment";
    private static final String ARG_PARAM1 = "Button Number";
    private static final String ARG_PARAM2 = "param2";

    private Button card_button;

    // TODO: Rename and change types of parameters
    private Integer card_number;
    private String mParam2;

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
        Log.d(TAG, "newInstance() called with: card_number = [" + card_number + "], param2 = [" + param2 + "]");

        PlayerCardFragment fragment = new PlayerCardFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, card_number);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            card_number = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called with: inflater = [" + inflater + "], container = [" + container + "], savedInstanceState = [" + savedInstanceState + "]");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.player_card_fragment, container, false);

        card_button = rootView.findViewById(R.id.card_button); // Get button, store in fragment variable



        return rootView;
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart() called");
        super.onStart();

        card_button.setOnClickListener(new View.OnClickListener() { // Set button behaviour
            @Override
            public void onClick(View v) {
                //TODO set button behaviour
                card_button.setText("pressed");
            }
        });

        if (card_number != null){
            Log.d(TAG, "OnStart: Setting button text to " + card_number.toString());
            card_button.setText(card_number.toString());
        } else{
            Log.d(TAG, "onStart: No card number");
            card_button.setText("Error occured");
        }
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
}
