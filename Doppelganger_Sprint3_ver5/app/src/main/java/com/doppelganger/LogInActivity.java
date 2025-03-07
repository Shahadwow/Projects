package com.doppelganger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseUser;


public class LogInActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogIn;
    private SharedPreferences sharedPreferences;

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;


    private static final String PREFS_NAME = "LoginPrefs";
    private static final String KEY_EMAIL = "saved_email";
    private static final String KEY_PASSWORD = "saved_password";

    private static final String ADMIN_EMAIL = "admin@gmail.com";
    private static final String ADMIN_PASSWORD = "1111";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        etEmail = findViewById(R.id.email_Login);
        etPassword = findViewById(R.id.password_Login);
        btnLogIn = findViewById(R.id.btnLogIn);

        mAuth = FirebaseAuth.getInstance();

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        loadSavedCredentials();

        btnLogIn.setOnClickListener(v -> handleLogin());
    }

    private void handleLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }

        if (email.equals(ADMIN_EMAIL) && password.equals(ADMIN_PASSWORD)) {
            Toast.makeText(LogInActivity.this, "Welcome Admin!", Toast.LENGTH_LONG).show();
            saveCredentials(email, password);
            Intent intent = new Intent(LogInActivity.this, CardStyleActivity.class);
            startActivity(intent);
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            Toast.makeText(LogInActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();

                            User userObj = new User(user.getEmail(), 0, 0);

                            userObj.retrieveUserData(user.getUid(), new User.OnUserDataRetrievedListener() {
                                @Override
                                public void onUserDataRetrieved(int coins, int currentLevel) {
                                    userObj.setCoins(coins);
                                    userObj.setCurrentLevel(currentLevel);

                                    Log.d("UserData", "Coins: " + coins + ", Current Level: " + currentLevel);

                                    runOnUiThread(() -> {
                                        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                                        intent.putExtra("userCoins", userObj.getCoins());
                                        intent.putExtra("userLevel", userObj.getCurrentLevel());
                                        startActivity(intent);
                                        finish();
                                    });
                                }
                            });
                        }
                    } else {
                        Log.e("LoginError", "Error: " + task.getException().getMessage());
                        Toast.makeText(LogInActivity.this, "Invalid email or password!", Toast.LENGTH_SHORT).show();
                    }
                });
    }





    private void saveCredentials(String email, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    private void loadSavedCredentials() {
        String savedEmail = sharedPreferences.getString(KEY_EMAIL, "");
        String savedPassword = sharedPreferences.getString(KEY_PASSWORD, "");

        if (!savedEmail.isEmpty() && !savedPassword.isEmpty()) {
            etEmail.setText(savedEmail);
            etPassword.setText(savedPassword);
        }
    }

    public void signUpButton(View view) {
        Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
        startActivity(intent);
    }


}


