package com.doppelganger;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class GameBase extends AppCompatActivity {

    protected ImageView firstCard, secondCard;
    protected boolean isFirstCardFlipped = false;
    protected int mistakes = 0; // Track mistakes
    protected int matches = 0;  // Track matches
    protected int maxMistakes; // Max mistakes allowed
    protected int totalPairs;  // Total pairs of cards
    public long playerPerformanceTime;
    protected int coins;
    private int userCoins;
    protected int currentlevel;
    private long startTime;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    protected abstract void calculateLevelCoins();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startTime = System.currentTimeMillis();
        Intent intent = getIntent();
        userCoins = intent.getIntExtra("userCoins", 0);

        maxMistakes = getIntent().getIntExtra("MAX_MISTAKES", 3);
        totalPairs = getIntent().getIntExtra("TOTAL_PAIRS", 3);
    }

    public void endGame() {
        calculateLevelCoins();
    }


    protected void setOnClickListener(final ImageView card, final int drawableId, int currentLevel) {
        card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!isFirstCardFlipped) {
                    firstCard = card;
                    card.setImageResource(drawableId);
                    isFirstCardFlipped = true;
                } else if (firstCard != card) {
                    secondCard = card;
                    card.setImageResource(drawableId);
                    isFirstCardFlipped = false;

                    if (firstCard.getDrawable().getConstantState().equals(secondCard.getDrawable().getConstantState())) {
                        firstCard.setEnabled(false);
                        secondCard.setEnabled(false);
                        matches++;
                        if (matches == totalPairs) {
                            showMessageAndEndGame("You won!");
                            playerPerformanceTime= trackPerformance();
                            currentlevel= currentLevel;
                            saveProgress(currentlevel);
                            endGame();

                        }
                    } else {
                        mistakes++;
                        if (mistakes >= maxMistakes) {
                            showMessageAndEndGame("You lost! Too many mistakes.");
                        } else {
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.postDelayed(() -> {
                                firstCard.setImageResource(getCardStyle());
                                secondCard.setImageResource(getCardStyle());
                            }, 1000);
                        }
                    }
                }
            }
        });
    }

    private int getCardStyle() {
        switch (CardStyleActivity.flag) {
            case 1:
                return R.drawable.card1;
            case 2:
                return R.drawable.card2;
            case 3:
                return R.drawable.card3;
            default:
                return R.drawable.card;
        }
    }

    protected void saveProgress(int CLevel) {
        mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users").child(userId).child("currentLevel").setValue(CLevel)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("Firebase", "Level saved successfully.");
                    } else {
                        Log.d("Firebase", "Failed to save level.");
                    }
                });
    }

    protected void showMessageAndEndGame(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, id) -> {

                    coins = userCoins;

                    if (currentlevel == 1) {
                        coins += GameLevel1.getLevel1Coins();
                    }
                    if (currentlevel == 2) {
                        coins += GameLevel2.getlevel2Coins();
                    }
                    if (currentlevel == 3) {
                        coins += GameLevel3.getlevel3Coins();
                    }

                    Intent intent = new Intent(GameBase.this, MainActivity.class);
                    intent.putExtra("userCoins", coins);
                    startActivity(intent);
                    finish();
                });
        builder.create().show();
    }


    public long trackPerformance(){

        long performance = System.currentTimeMillis() - startTime;
        startTime=0;
        return performance;
    }
}



