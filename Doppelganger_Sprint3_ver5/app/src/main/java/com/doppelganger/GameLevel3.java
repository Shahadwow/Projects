package com.doppelganger;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

public class GameLevel3 extends GameBase {

    private static int level3Coins=0;

    ImageView imageViewcup;
    ImageView imageViewice;
    ImageView imageViewstr;
    ImageView imageViewcup2;
    ImageView imageViewice2;
    ImageView imageViewstr2;
    ImageView img7;
    ImageView img8;
    ImageView img9;
    ImageView img10;
    ImageView img11;
    ImageView img12;
    ImageView img13;
    ImageView img14;
    ImageView img15;
    ImageView img16;
    ImageView img17;
    ImageView img18;
    ImageView img19;
    ImageView img20;
    ImageView img21;
    ImageView img22;
    ImageView img23;
    ImageView img24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_level_3);

        imageViewcup = findViewById(R.id.cupcake);
        imageViewice = findViewById(R.id.icecream);
        imageViewstr = findViewById(R.id.strwaberrycake);
        imageViewcup2 = findViewById(R.id.cupcake2);
        imageViewice2 = findViewById(R.id.icecream2);
        imageViewstr2 = findViewById(R.id.strwaberrycake2);
        img7 = findViewById(R.id.cherry);
        img8 = findViewById(R.id.cherry2);
        img9 = findViewById(R.id.apple);
        img10 = findViewById(R.id.apple2);
        img11 = findViewById(R.id.birthdaycake);
        img12 = findViewById(R.id.birthdaycake2);
        img13 = findViewById(R.id.avocado);
        img14 = findViewById(R.id.avocado2);
        img15 = findViewById(R.id.donut);
        img16 = findViewById(R.id.donut2);
        img17 = findViewById(R.id.pink_cake);
        img18 = findViewById(R.id.pink_cake2);
        img19 = findViewById(R.id.berry);
        img20 = findViewById(R.id.berry2);
        img21 = findViewById(R.id.choclate_cake);
        img22 = findViewById(R.id.choclate_cake2);
        img23 = findViewById(R.id.birthday_cupcake);
        img24 = findViewById(R.id.birthday_cupcake2);

        imageViewcup.setImageResource(R.drawable.card2);
        imageViewice.setImageResource(R.drawable.card2);
        imageViewstr.setImageResource(R.drawable.card2);
        imageViewcup2.setImageResource(R.drawable.card2);
        imageViewice2.setImageResource(R.drawable.card2);
        imageViewstr2.setImageResource(R.drawable.card2);
        img7.setImageResource(R.drawable.card2);
        img8.setImageResource(R.drawable.card2);
        img9.setImageResource(R.drawable.card2);
        img10.setImageResource(R.drawable.card2);
        img11.setImageResource(R.drawable.card2);
        img12.setImageResource(R.drawable.card2);
        img13.setImageResource(R.drawable.card2);
        img14.setImageResource(R.drawable.card2);
        img15.setImageResource(R.drawable.card2);
        img16.setImageResource(R.drawable.card2);
        img17.setImageResource(R.drawable.card2);
        img18.setImageResource(R.drawable.card2);
        img19.setImageResource(R.drawable.card2);
        img20.setImageResource(R.drawable.card2);
        img21.setImageResource(R.drawable.card2);
        img22.setImageResource(R.drawable.card2);
        img23.setImageResource(R.drawable.card2);
        img24.setImageResource(R.drawable.card2);

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
        img7.setImageResource(R.drawable.cherry);
        img8.setImageResource(R.drawable.cherry);
        img9.setImageResource(R.drawable.apple);
        img10.setImageResource(R.drawable.apple);
        img11.setImageResource(R.drawable.birthdaycake);
        img12.setImageResource(R.drawable.birthdaycake);
        img13.setImageResource(R.drawable.avocado);
        img14.setImageResource(R.drawable.avocado);
        img15.setImageResource(R.drawable.donut);
        img16.setImageResource(R.drawable.donut);
        img17.setImageResource(R.drawable.pink_cake);
        img18.setImageResource(R.drawable.pink_cake);
        img19.setImageResource(R.drawable.berry);
        img20.setImageResource(R.drawable.berry);
        img21.setImageResource(R.drawable.choclate_cake);
        img22.setImageResource(R.drawable.choclate_cake);
        img23.setImageResource(R.drawable.birthday_cupcake);
        img24.setImageResource(R.drawable.birthday_cupcake);

        handler.postDelayed(() -> {

            if(CardStyleActivity.flag==1){
                imageViewcup.setImageResource(R.drawable.card1);
                imageViewice.setImageResource(R.drawable.card1);
                imageViewstr.setImageResource(R.drawable.card1);
                imageViewcup2.setImageResource(R.drawable.card1);
                imageViewice2.setImageResource(R.drawable.card1);
                imageViewstr2.setImageResource(R.drawable.card1);
                img7.setImageResource(R.drawable.card1);
                img8.setImageResource(R.drawable.card1);
                img9.setImageResource(R.drawable.card1);
                img10.setImageResource(R.drawable.card1);
                img11.setImageResource(R.drawable.card1);
                img12.setImageResource(R.drawable.card1);
                img13.setImageResource(R.drawable.card1);
                img14.setImageResource(R.drawable.card1);
                img15.setImageResource(R.drawable.card1);
                img16.setImageResource(R.drawable.card1);
                img17.setImageResource(R.drawable.card1);
                img18.setImageResource(R.drawable.card1);
                img19.setImageResource(R.drawable.card1);
                img20.setImageResource(R.drawable.card1);
                img21.setImageResource(R.drawable.card1);
                img22.setImageResource(R.drawable.card1);
                img23.setImageResource(R.drawable.card1);
                img24.setImageResource(R.drawable.card1);
            }

            if(CardStyleActivity.flag==2){
                imageViewcup.setImageResource(R.drawable.card2);
                imageViewice.setImageResource(R.drawable.card2);
                imageViewstr.setImageResource(R.drawable.card2);
                imageViewcup2.setImageResource(R.drawable.card2);
                imageViewice2.setImageResource(R.drawable.card2);
                imageViewstr2.setImageResource(R.drawable.card2);
                img7.setImageResource(R.drawable.card2);
                img8.setImageResource(R.drawable.card2);
                img9.setImageResource(R.drawable.card2);
                img10.setImageResource(R.drawable.card2);
                img11.setImageResource(R.drawable.card2);
                img12.setImageResource(R.drawable.card2);
                img13.setImageResource(R.drawable.card2);
                img14.setImageResource(R.drawable.card2);
                img15.setImageResource(R.drawable.card2);
                img16.setImageResource(R.drawable.card2);
                img17.setImageResource(R.drawable.card2);
                img18.setImageResource(R.drawable.card2);
                img19.setImageResource(R.drawable.card2);
                img20.setImageResource(R.drawable.card2);
                img21.setImageResource(R.drawable.card2);
                img22.setImageResource(R.drawable.card2);
                img23.setImageResource(R.drawable.card2);
                img24.setImageResource(R.drawable.card2);
            }

            if(CardStyleActivity.flag==3){
                imageViewcup.setImageResource(R.drawable.card3);
                imageViewice.setImageResource(R.drawable.card3);
                imageViewstr.setImageResource(R.drawable.card3);
                imageViewcup2.setImageResource(R.drawable.card3);
                imageViewice2.setImageResource(R.drawable.card3);
                imageViewstr2.setImageResource(R.drawable.card3);
                img7.setImageResource(R.drawable.card3);
                img8.setImageResource(R.drawable.card3);
                img9.setImageResource(R.drawable.card3);
                img10.setImageResource(R.drawable.card3);
                img11.setImageResource(R.drawable.card3);
                img12.setImageResource(R.drawable.card3);
                img13.setImageResource(R.drawable.card3);
                img14.setImageResource(R.drawable.card3);
                img15.setImageResource(R.drawable.card3);
                img16.setImageResource(R.drawable.card3);
                img17.setImageResource(R.drawable.card3);
                img18.setImageResource(R.drawable.card3);
                img19.setImageResource(R.drawable.card3);
                img20.setImageResource(R.drawable.card3);
                img21.setImageResource(R.drawable.card3);
                img22.setImageResource(R.drawable.card3);
                img23.setImageResource(R.drawable.card3);
                img24.setImageResource(R.drawable.card3);
            }



            setOnClickListener(imageViewcup, R.drawable.cupcake,3);
            setOnClickListener(imageViewcup2, R.drawable.cupcake,3);
            setOnClickListener(imageViewice, R.drawable.icecream,3);
            setOnClickListener(imageViewice2, R.drawable.icecream,3);
            setOnClickListener(imageViewstr, R.drawable.strwaberrycake,3);
            setOnClickListener(imageViewstr2, R.drawable.strwaberrycake,3);
            setOnClickListener(img7, R.drawable.cherry,3);
            setOnClickListener(img8, R.drawable.cherry,3);
            setOnClickListener(img9, R.drawable.apple,3);
            setOnClickListener(img10, R.drawable.apple,3);
            setOnClickListener(img11, R.drawable.birthdaycake,3);
            setOnClickListener(img12, R.drawable.birthdaycake,3);
            setOnClickListener(img13, R.drawable.avocado,3);
            setOnClickListener(img14, R.drawable.avocado,3);
            setOnClickListener(img15, R.drawable.donut,3);
            setOnClickListener(img16, R.drawable.donut,3);
            setOnClickListener(img17, R.drawable.pink_cake,3);
            setOnClickListener(img18, R.drawable.pink_cake,3);
            setOnClickListener(img19, R.drawable.berry,3);
            setOnClickListener(img20, R.drawable.berry,3);
            setOnClickListener(img21, R.drawable.choclate_cake,3);
            setOnClickListener(img22, R.drawable.choclate_cake,3);
            setOnClickListener(img23, R.drawable.birthday_cupcake,3);
            setOnClickListener(img24, R.drawable.birthday_cupcake,3);
        }, 10000);
    }

    protected void calculateLevelCoins() {
        int performance = (int) (playerPerformanceTime / 1000);

        if (performance < 30) {
            level3Coins = 10;
        } else {
            level3Coins = 5;
        }
    }

    public static int getlevel3Coins(){
        return level3Coins;
    }
}
