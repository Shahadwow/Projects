package com.doppelganger;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private int userCoins;
    private DatabaseReference userReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        userCoins = intent.getIntExtra("userCoins", 0);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();
            userReference = FirebaseDatabase.getInstance().getReference("users").child(userId);
            userReference.child("coins").setValue(userCoins);
        }
        Button startLevelButton = findViewById(R.id.button);
        startLevelButton.setOnClickListener(v -> {
            saveUserCoinsToFirebase(userCoins);
            Intent levelIntent = new Intent(MainActivity.this, LevelSelection.class);
            levelIntent.putExtra("userCoins", userCoins);
            startActivity(levelIntent);
        });

        Button cardStyleButton = findViewById(R.id.cardStyles);
        cardStyleButton.setOnClickListener(v -> {
            saveUserCoinsToFirebase(userCoins);
            Intent styleIntent = new Intent(MainActivity.this, CardStyleActivity.class);
            styleIntent.putExtra("userCoins", userCoins);
            startActivity(styleIntent);
        });
    }

    private void saveUserCoinsToFirebase(int coins) {
        if (userReference != null) {
            userReference.child("coins").setValue(coins);
        }
    }
}
