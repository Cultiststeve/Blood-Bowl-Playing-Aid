package com.example.bloodbowlplayingaid;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Dialogue_New_turn extends DialogFragment {

    Fragment_Dialog_New_Turn_Listener listener; //Use to deliver the positive click

    public Fragment_Dialogue_New_turn() {
        // Required empty public constructor
    }

    public interface Fragment_Dialog_New_Turn_Listener {
        public void onDialogPositiveClick(DialogFragment dialogFragment);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Use Builder class for convenient construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Move to next turn?"); //TODO specify turn number
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onDialogPositiveClick(Fragment_Dialogue_New_turn.this);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User canceled, no action?
            }
        });

        return builder.create();
    }

    //
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Verify that the host implements callback
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (Fragment_Dialog_New_Turn_Listener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getActivity().toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
