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

public class GameLevel2 extends GameBase {

    private static int level2Coins=0;
    DatabaseReference user;

    ImageView imageViewcup;
    ImageView imageViewice;
    ImageView imageViewstr;
    ImageView imageViewapple;
    ImageView imageViewavocado;
    ImageView imageViewcherry;
    ImageView imageViewapple2;
    ImageView imageViewavocado2;
    ImageView imageViewcherry2;
    ImageView imageViewcup2;
    ImageView imageViewice2;
    ImageView imageViewstr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_level_2);
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        user = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

        imageViewcup = findViewById(R.id.cupcake);
        imageViewice = findViewById(R.id.icecream);
        imageViewstr = findViewById(R.id.strwaberrycake);
        imageViewapple = findViewById(R.id.apple);
        imageViewavocado = findViewById(R.id.avocado);
        imageViewcherry = findViewById(R.id.cherry);
        imageViewapple2 = findViewById(R.id.apple2);
        imageViewavocado2 = findViewById(R.id.avocado2);
        imageViewcherry2 = findViewById(R.id.cherry2);
        imageViewcup2 = findViewById(R.id.cupcake2);
        imageViewice2 = findViewById(R.id.icecream2);
        imageViewstr2 = findViewById(R.id.strwaberrycake2);

        imageViewcup.setImageResource(R.drawable.card2);
        imageViewice.setImageResource(R.drawable.card2);
        imageViewstr.setImageResource(R.drawable.card2);
        imageViewcup2.setImageResource(R.drawable.card2);
        imageViewice2.setImageResource(R.drawable.card2);
        imageViewstr2.setImageResource(R.drawable.card2);
        imageViewavocado.setImageResource(R.drawable.card2);
        imageViewapple.setImageResource(R.drawable.card2);
        imageViewcherry.setImageResource(R.drawable.card2);
        imageViewavocado2.setImageResource(R.drawable.card2);
        imageViewapple2.setImageResource(R.drawable.card2);
        imageViewcherry2.setImageResource(R.drawable.card2);

        flipCards();
    }

    private void flipCards() {

        Handler handler = new Handler(Looper.getMainLooper());

        imageViewcup.setImageResource(R.drawable.cupcake);
        imageViewice.setImageResource(R.drawable.icecream);
        imageViewstr.setImageResource(R.drawable.strwaberrycake);
        imageViewcup2.setImageResource(R.drawable.cupcake);
        imageViewice2.setImageResource(R.drawable.icecream);
        imageViewstr2.setImageResource(R.drawable.strwaberrycake);
        imageViewavocado.setImageResource(R.drawable.avocado);
        imageViewapple.setImageResource(R.drawable.apple);
        imageViewcherry.setImageResource(R.drawable.cherry);
        imageViewavocado2.setImageResource(R.drawable.avocado);
        imageViewapple2.setImageResource(R.drawable.apple);
        imageViewcherry2.setImageResource(R.drawable.cherry);

        handler.postDelayed(() -> { // What code will run after the delay

            if(CardStyleActivity.flag==1){
                imageViewcup.setImageResource(R.drawable.card1);
                imageViewice.setImageResource(R.drawable.card1);
                imageViewstr.setImageResource(R.drawable.card1);
                imageViewcup2.setImageResource(R.drawable.card1);
                imageViewice2.setImageResource(R.drawable.card1);
                imageViewstr2.setImageResource(R.drawable.card1);
                imageViewavocado.setImageResource(R.drawable.card1);
                imageViewapple.setImageResource(R.drawable.card1);
                imageViewcherry.setImageResource(R.drawable.card1);
                imageViewavocado2.setImageResource(R.drawable.card1);
                imageViewapple2.setImageResource(R.drawable.card1);
                imageViewcherry2.setImageResource(R.drawable.card1);
            }
            if(CardStyleActivity.flag==2){
                imageViewcup.setImageResource(R.drawable.card2);
                imageViewice.setImageResource(R.drawable.card2);
                imageViewstr.setImageResource(R.drawable.card2);
                imageViewcup2.setImageResource(R.drawable.card2);
                imageViewice2.setImageResource(R.drawable.card2);
                imageViewstr2.setImageResource(R.drawable.card2);
                imageViewavocado.setImageResource(R.drawable.card2);
                imageViewapple.setImageResource(R.drawable.card2);
                imageViewcherry.setImageResource(R.drawable.card2);
                imageViewavocado2.setImageResource(R.drawable.card2);
                imageViewapple2.setImageResource(R.drawable.card2);
                imageViewcherry2.setImageResource(R.drawable.card2);
            }
            if(CardStyleActivity.flag==3){
                imageViewcup.setImageResource(R.drawable.card3);
                imageViewice.setImageResource(R.drawable.card3);
                imageViewstr.setImageResource(R.drawable.card3);
                imageViewcup2.setImageResource(R.drawable.card3);
                imageViewice2.setImageResource(R.drawable.card3);
                imageViewstr2.setImageResource(R.drawable.card3);
                imageViewavocado.setImageResource(R.drawable.card3);
                imageViewapple.setImageResource(R.drawable.card3);
                imageViewcherry.setImageResource(R.drawable.card3);
                imageViewavocado2.setImageResource(R.drawable.card3);
                imageViewapple2.setImageResource(R.drawable.card3);
                imageViewcherry2.setImageResource(R.drawable.card3);
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
                        setOnClickListener(imageViewavocado, R.drawable.avocado,3);
                        setOnClickListener(imageViewavocado2, R.drawable.avocado,3);
                        setOnClickListener(imageViewcherry, R.drawable.cherry,3);
                        setOnClickListener(imageViewcherry2, R.drawable.cherry,3);
                        setOnClickListener(imageViewapple, R.drawable.apple,3);
                        setOnClickListener(imageViewapple2, R.drawable.apple,3);
                    }
                    else{
                        setOnClickListener(imageViewcup, R.drawable.cupcake,2);
                        setOnClickListener(imageViewcup2, R.drawable.cupcake,2);
                        setOnClickListener(imageViewice, R.drawable.icecream,2);
                        setOnClickListener(imageViewice2, R.drawable.icecream,2);
                        setOnClickListener(imageViewstr, R.drawable.strwaberrycake,2);
                        setOnClickListener(imageViewstr2, R.drawable.strwaberrycake,2);
                        setOnClickListener(imageViewavocado, R.drawable.avocado,2);
                        setOnClickListener(imageViewavocado2, R.drawable.avocado,2);
                        setOnClickListener(imageViewcherry, R.drawable.cherry,2);
                        setOnClickListener(imageViewcherry2, R.drawable.cherry,2);
                        setOnClickListener(imageViewapple, R.drawable.apple,2);
                        setOnClickListener(imageViewapple2, R.drawable.apple,2);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });


        }, 4000);
    }

    protected void calculateLevelCoins() {
        int performance = (int) (playerPerformanceTime / 1000);

        if (performance < 15) {
            level2Coins = 10;
        } else {
            level2Coins = 5;
        }
    }
    public static int getlevel2Coins(){
        return level2Coins;
    }
}
