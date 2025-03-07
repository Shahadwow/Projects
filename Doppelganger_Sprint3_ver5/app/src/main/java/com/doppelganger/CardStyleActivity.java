package com.doppelganger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;



public class CardStyleActivity extends AppCompatActivity {


    private ImageView cardStyle1, cardStyle2, cardStyle3;
    private Button saveSelectionButton, adminEditButton;
    public static int flag;
    private boolean style1Bought= false;
    private boolean style3Bought= false;
    private int userCoins;
    FirebaseUser user;
    private DatabaseReference mDatabase;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_style);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        preferences = getSharedPreferences("CardStyles_" + user.getUid(), MODE_PRIVATE);

        Intent intent1 = getIntent();
        userCoins = intent1.getIntExtra("userCoins", 0);

        cardStyle1 = findViewById(R.id.cardStyle1);
        cardStyle2 = findViewById(R.id.cardStyle2);
        cardStyle3 = findViewById(R.id.cardStyle3);

        TextView coinsWallet = findViewById(R.id.coins);
        coinsWallet.setText(String.valueOf(userCoins));
        user = FirebaseAuth.getInstance().getCurrentUser();

        Button button = findViewById(R.id.Return);
        button.setOnClickListener(v -> {
            // Start LevelSelectionActivity
            Intent intent2 = new Intent(CardStyleActivity.this, MainActivity.class);
            intent2.putExtra("userCoins", userCoins);
            intent2.putExtra("sourceActivity", 2);
            startActivity(intent2);
        });
    }

    public void cardStyle1(View view) {
        TextView coinsWallet = findViewById(R.id.coins);

        style1Bought = preferences.getBoolean("style1Bought", false);

        if (style1Bought) {
            Toast.makeText(this, "This card Style is now set.", Toast.LENGTH_SHORT).show();
            flag = 1;
            saveFlagToPreferences();
            return;
        }

        if (userCoins < 15) {
            Toast.makeText(this, "You don't have enough coins.", Toast.LENGTH_SHORT).show();
        } else {
            style1Bought = true;
            userCoins -= 15;
            coinsWallet.setText(String.valueOf(userCoins));

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("style1Bought", true);
            editor.apply();

            updateUserCoins(user.getUid(), userCoins);

            Toast.makeText(this, "Purchased!", Toast.LENGTH_SHORT).show();
            flag = 1;
            saveFlagToPreferences();
        }
    }



    public void cardStyle2(View view) {
        flag = 2;
        Toast.makeText(this, "This card Style is now set.", Toast.LENGTH_SHORT).show();
    }

    public void cardStyle3(View view) {
        TextView coinsWallet = findViewById(R.id.coins);

        style3Bought = preferences.getBoolean("style3Bought", false);

        if (style3Bought) {
            Toast.makeText(this, "This card Style is now set.", Toast.LENGTH_SHORT).show();
            flag = 3;
            saveFlagToPreferences();
            return;
        }

        if (userCoins < 25) {
            Toast.makeText(this, "You don't have enough coins.", Toast.LENGTH_SHORT).show();
        } else {
            userCoins -= 25;
            style3Bought = true;
            coinsWallet.setText(String.valueOf(userCoins));

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("style3Bought", true);
            editor.apply();

            updateUserCoins(user.getUid(), userCoins);

            Toast.makeText(this, "Purchased!", Toast.LENGTH_SHORT).show();
            flag = 3;
            saveFlagToPreferences();
        }
    }

    private void saveFlagToPreferences() {
        if (user != null) {
            SharedPreferences preferences = getSharedPreferences("CardStyles_" + user.getUid(), MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("selectedStyleFlag", flag);
            editor.apply();
        }
    }


    private void updateUserCoins(String userId, int updatedCoins) {
        // Reference to the "users" node in the database
        mDatabase.child("users").child(userId).child("coins")
                .setValue(updatedCoins)  // Set the updated coins value
                .addOnSuccessListener(aVoid -> {

                })
                .addOnFailureListener(e -> {

                });
    }
}
