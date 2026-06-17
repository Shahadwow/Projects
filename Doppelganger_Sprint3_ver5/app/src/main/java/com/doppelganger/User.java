package com.doppelganger;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User {
    public String email;
    public int coins;
    public int currentLevel;
    private DatabaseReference database;

    public User(String email, int coins, int currentLevel) {
        this.email = email;
        this.coins = coins;
        this.currentLevel = currentLevel;
        database = FirebaseDatabase.getInstance().getReference();
    }

    public void retrieveUserData(String userId, OnUserDataRetrievedListener listener) {
        database.child("users").child(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            Long retrievedCoins = dataSnapshot.child("coins").getValue(Long.class);
                            Long retrievedLevel = dataSnapshot.child("currentLevel").getValue(Long.class);

                            if (retrievedCoins != null && retrievedLevel != null) {
                                setCoins(retrievedCoins.intValue());
                                setCurrentLevel(retrievedLevel.intValue());
                                listener.onUserDataRetrieved(retrievedCoins.intValue(), retrievedLevel.intValue());
                            }
                        } else {
                            Log.e("RealtimeDatabase", "No such user found.");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("RealtimeDatabase", "Error getting data: " + databaseError.getMessage());
                    }
                });
    }

    public int getCoins() {

        return coins;
    }

    public void setCoins(int coins) {

        this.coins = coins;
    }

    public int getCurrentLevel() {

        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {

        this.currentLevel = currentLevel;
    }

    public interface OnUserDataRetrievedListener {
        void onUserDataRetrieved(int coins, int currentLevel);
    }
}

