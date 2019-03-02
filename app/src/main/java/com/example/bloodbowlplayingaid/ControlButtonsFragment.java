package com.example.bloodbowlplayingaid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ControlButtonsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ControlButtonsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlButtonsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "BBH_Button_Draw";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ControlButtonsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ControlButtonsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ControlButtonsFragment newInstance(String param1, String param2) {
        ControlButtonsFragment fragment = new ControlButtonsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment__button__draw, container, false);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart() called");
        super.onStart();

        Button turn_button = getView().findViewById(R.id.btn_Turn_Count);

        turn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new NewTurnDialogueFragment();
                dialogFragment.show(getActivity().getSupportFragmentManager(), "new_turn");
            }
        });

        turn_button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mListener.reduceTurn();
                return false;
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
        void reduceTurn();
    }


    public void incrementTurnCounter(Integer new_turn){
        Integer new_turn_display;
        if (new_turn > 8){
            //Second Half
            TextView textView = getView().findViewById(R.id.txt_Game_Half);
            textView.setText("Second Half");
            new_turn_display = new_turn % 8; // If second half, display turn 1-8
        } else {
            new_turn_display = new_turn;
        }
        Button turn_button = getView().findViewById(R.id.btn_Turn_Count);
        turn_button.setText(new_turn_display.toString());
    }
}
