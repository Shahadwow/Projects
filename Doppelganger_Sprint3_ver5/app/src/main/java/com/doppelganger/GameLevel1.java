package com.doppelganger;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GameLevel1 extends GameBase {

    ImageView imageViewcup;
    ImageView imageViewice;
    ImageView imageViewstr;
    ImageView imageViewcup2;
    ImageView imageViewice2;
    ImageView imageViewstr2;

    private static int level1Coins=0;
    DatabaseReference user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_level_1);
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        user = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

        imageViewcup = findViewById(R.id.cupcake);
        imageViewice = findViewById(R.id.icecream);
        imageViewstr = findViewById(R.id.strwaberrycake);
        imageViewcup2 = findViewById(R.id.cupcake2);
        imageViewice2 = findViewById(R.id.icecream2);
        imageViewstr2 = findViewById(R.id.strwaberrycake2);


        flipCards();
    }


    private void flipCards() {
        Handler handler = new Handler(Looper.getMainLooper());



        handler.postDelayed(() -> { // What code will run after the delay

            imageViewcup.setImageResource(R.drawable.card2);
            imageViewice.setImageResource(R.drawable.card2);
            imageViewstr.setImageResource(R.drawable.card2);
            imageViewcup2.setImageResource(R.drawable.card2);
            imageViewice2.setImageResource(R.drawable.card2);
            imageViewstr2.setImageResource(R.drawable.card2);

            if(CardStyleActivity.flag==1){
                imageViewcup.setImageResource(R.drawable.card1);
                imageViewice.setImageResource(R.drawable.card1);
                imageViewstr.setImageResource(R.drawable.card1);
                imageViewcup2.setImageResource(R.drawable.card1);
                imageViewice2.setImageResource(R.drawable.card1);
                imageViewstr2.setImageResource(R.drawable.card1);
            }
            if(CardStyleActivity.flag==2){
                imageViewcup.setImageResource(R.drawable.card2);
                imageViewice.setImageResource(R.drawable.card2);
                imageViewstr.setImageResource(R.drawable.card2);
                imageViewcup2.setImageResource(R.drawable.card2);
                imageViewice2.setImageResource(R.drawable.card2);
                imageViewstr2.setImageResource(R.drawable.card2);
            }
            if(CardStyleActivity.flag==3){
                imageViewcup.setImageResource(R.drawable.card3);
                imageViewice.setImageResource(R.drawable.card3);
                imageViewstr.setImageResource(R.drawable.card3);
                imageViewcup2.setImageResource(R.drawable.card3);
                imageViewice2.setImageResource(R.drawable.card3);
                imageViewstr2.setImageResource(R.drawable.card3);
            }


            user.child("currentLevel").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Integer currentLevel = dataSnapshot.getValue(Integer.class);
                    if (currentLevel == 3) {

                        setOnClickListener(imageViewcup, R.drawable.cupcake,3);
                        setOnClickListener(imageViewcup2, R.drawable.cupcake,3);
                        setOnClickListener(imageViewice, R.drawable.icecream,3);
                        setOnClickListener(imageViewice2, R.drawable.icecream,3);
                        setOnClickListener(imageViewstr, R.drawable.strwaberrycake,3);
                        setOnClickListener(imageViewstr2, R.drawable.strwaberrycake,3);
                    }
                    else if(currentLevel == 2){
                        setOnClickListener(imageViewcup, R.drawable.cupcake,2);
                        setOnClickListener(imageViewcup2, R.drawable.cupcake,2);
                        setOnClickListener(imageViewice, R.drawable.icecream,2);
                        setOnClickListener(imageViewice2, R.drawable.icecream,2);
                        setOnClickListener(imageViewstr, R.drawable.strwaberrycake,2);
                        setOnClickListener(imageViewstr2, R.drawable.strwaberrycake,2);
                    }
                    else{
                        setOnClickListener(imageViewcup, R.drawable.cupcake,1);
                        setOnClickListener(imageViewcup2, R.drawable.cupcake,1);
                        setOnClickListener(imageViewice, R.drawable.icecream,1);
                        setOnClickListener(imageViewice2, R.drawable.icecream,1);
                        setOnClickListener(imageViewstr, R.drawable.strwaberrycake,1);
                        setOnClickListener(imageViewstr2, R.drawable.strwaberrycake,1);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

        }, 2000);

    }

    protected void calculateLevelCoins() {
        int performance = (int) (playerPerformanceTime / 1000);
        if (performance < 8) {
            level1Coins = 10;
        } else {
            level1Coins = 5;
        }
    }

    public static int getLevel1Coins(){
        return level1Coins;
    }
}

