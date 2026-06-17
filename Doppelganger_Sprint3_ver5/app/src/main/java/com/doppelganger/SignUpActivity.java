package com.doppelganger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private EditText et_fullName, et_email, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        FirebaseAuth.getInstance().signOut();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        et_fullName = findViewById(R.id.FullName);
        et_email = findViewById(R.id.email);
        et_password = findViewById(R.id.Password);
    }

    public void registerDB(View view) {
        String fullName = et_fullName.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if (fullName.isEmpty()) {
            et_fullName.setError("Full Name is required");
            et_fullName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            et_email.setError("Email is required");
            et_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError("Please provide a valid email");
            et_email.requestFocus();
            return;
        }
        if (password.isEmpty() || password.length() < 8) {
            et_password.setError("Password must be at least 8 characters");
            et_password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(SignUpActivity.this, "Sign-up successful!", Toast.LENGTH_SHORT).show();

                        String userId = user.getUid();
                        User newUser = new User(email, 0,0);

                        mDatabase.child("users").child(userId).setValue(newUser)
                                .addOnSuccessListener(aVoid -> {
                                    Log.d("RealtimeDatabase", "User data added successfully.");
                                })
                                .addOnFailureListener(e -> {
                                    Log.e("RealtimeDatabase", "Error adding user data: " + e.getMessage());
                                });

                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        intent.putExtra("userCoins", 0);  // Pass coins to MainActivity
                        startActivity(intent);
                        finish();
                    } else {
                        String errorMessage = task.getException().getMessage();
                        Log.e("SignUpError", errorMessage);

                        if (errorMessage.contains("email address is already in use")) {
                            Toast.makeText(SignUpActivity.this, "This email is already in use.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUpActivity.this, "Sign-up failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void LogInButton(View view) {
        Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
        startActivity(intent);
    }
}


