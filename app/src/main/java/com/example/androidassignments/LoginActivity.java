package com.example.androidassignments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private static final String ACTIVITY_NAME = "LoginActivity";
    private static final String PREFS_NAME = "AssignmentPrefs";
    private static final String EMAIL_KEY = "DefaultEmail";

    private EditText emailField;
    private EditText passwordField;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME, "In function: onCreate");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailField = findViewById(R.id.login_name);
        passwordField = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);

        // Read the last saved email; the second value is used the first time.
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        emailField.setText(prefs.getString(EMAIL_KEY, "email@domain.com"));

        loginButton.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString();

            if (!AppLogic.isValidEmail(email)) {
                Toast.makeText(LoginActivity.this,
                        getString(R.string.error_email_invalid), Toast.LENGTH_SHORT).show();
                return;
            }
            if (AppLogic.isPasswordEmpty(password)) {
                Toast.makeText(LoginActivity.this,
                        getString(R.string.error_password_empty), Toast.LENGTH_SHORT).show();
                return;
            }

            // Save the email, then go to MainActivity.
            SharedPreferences.Editor editor =
                    getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
            editor.putString(EMAIL_KEY, email);
            editor.apply();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In function: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In function: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In function: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In function: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In function: onDestroy");
    }
}