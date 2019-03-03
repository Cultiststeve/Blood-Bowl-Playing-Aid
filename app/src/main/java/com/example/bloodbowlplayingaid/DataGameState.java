package com.example.bloodbowlplayingaid;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class DataGameState implements Parcelable {

    private int currentTurn ;
    private int currentRerolls ;
    private int currentTouchdowns;
    public List<String> teamList = null; //List of strings of player names

    DataGameState(){
        //Emopty constructor when creating new game data
        currentTurn = 0;
        currentRerolls = 0;
        currentTouchdowns = 0;
        teamList = new ArrayList<>();
    }

    //Constructor
    private DataGameState(Parcel in) {
        currentTurn = in.readInt();
        currentRerolls = in.readInt();
        currentTouchdowns = in.readInt();
        teamList = in.createStringArrayList();
    }

    public static final Creator<DataGameState> CREATOR = new Creator<DataGameState>() {
        @Override
        public DataGameState createFromParcel(Parcel in) {
            return new DataGameState(in);
        }

        @Override
        public DataGameState[] newArray(int size) {
            return new DataGameState[size];
        }
    };

    public int getCurrentTouchdowns() {
        return currentTouchdowns;
    }

    public void setCurrentTouchdowns(int currentTouchdowns) {
        this.currentTouchdowns = currentTouchdowns;
    }

    public int getCurrentRerolls() {
        return currentRerolls;
    }

    public void setCurrentRerolls(int currentRerolls) {
        this.currentRerolls = currentRerolls;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public void incrementTurn(){
        this.currentTurn++;
    }

    public void decrementTurn(){
        this.currentTurn--;
    }

    public void incrementRerolls(){
        this.currentRerolls++;
    }

    public void decrementRerolls(){
        this.currentRerolls--;
    }

    public void incrementTouchdowns(){
        this.currentTouchdowns++;
    }

    public void decrementTouchdowns(){
        this.currentTouchdowns--;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(currentTurn);
        dest.writeInt(currentRerolls);
        dest.writeInt(currentTouchdowns);
        dest.writeStringList(teamList);

    }
}
