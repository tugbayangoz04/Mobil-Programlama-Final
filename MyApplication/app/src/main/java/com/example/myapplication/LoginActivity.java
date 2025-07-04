package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText editEmail, editPassword;
    Button btnLogin;
    TextView textMessage, textSignupLink;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("GAMEZONE");
        }

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);
        textMessage = findViewById(R.id.textSignupLink);
        textSignupLink = findViewById(R.id.textSignupLink);

        dbHelper = new DBHelper(this);

        btnLogin.setOnClickListener(v -> {
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                textMessage.setText("Lütfen tüm alanları doldurun.");
                return;
            }

            String usermail = dbHelper.loginUser(email, password);

            if (usermail != null) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("usermail", usermail);
                startActivity(intent);
                finish();
            } else {
                textMessage.setText("Giriş başarısız. Bilgileri kontrol et.");
            }
        });

        textSignupLink.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }
}