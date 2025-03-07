package com.doppelganger;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LevelSelection extends AppCompatActivity {

    private int userCoins;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelselection);

        Intent intent = getIntent();
        userCoins = intent.getIntExtra("userCoins", 0);

        Button level1Button = findViewById(R.id.level1Button);
        level1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start GameLevel1 Activity
                Intent intent = new Intent(LevelSelection.this, GameLevel1.class);
                intent.putExtra("MAX_MISTAKES", 3);
                intent.putExtra("TOTAL_PAIRS", 3);
                intent.putExtra("userCoins", userCoins);
                startActivity(intent);
            }
        });

        Button level2Button = findViewById(R.id.level2Button);
        level2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LevelSelection.this, GameLevel2.class);
                intent.putExtra("MAX_MISTAKES", 4);
                intent.putExtra("TOTAL_PAIRS", 6);
                intent.putExtra("userCoins", userCoins);

                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                String userId = user.getUid();

                mDatabase.child("users").child(userId).child("currentLevel").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Integer currentLevel = dataSnapshot.getValue(Integer.class);
                        if (currentLevel <1) {
                            Toast.makeText(LevelSelection.this, "You must complete level 1 first", Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(LevelSelection.this, "Failed to check level: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        Button level3Button = findViewById(R.id.level3Button);
        level3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start GameLevel1 Activity
                Intent intent = new Intent(LevelSelection.this, GameLevel3.class);
                intent.putExtra("MAX_MISTAKES", 9);
                intent.putExtra("TOTAL_PAIRS", 12);
                intent.putExtra("userCoins", userCoins);

                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                String userId = user.getUid();

                mDatabase.child("users").child(userId).child("currentLevel").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Integer currentLevel = dataSnapshot.getValue(Integer.class);
                        if (currentLevel <2) {
                            Toast.makeText(LevelSelection.this, "You must complete level 2 first", Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(LevelSelection.this, "Failed to check level: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}

