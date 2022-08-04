package com.example.recyclerviewwithfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin,btnGoogle,btnFacebook;
    TextView tvForgetPass,tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin=findViewById(R.id.Btn_LoginIn);
        btnFacebook=findViewById(R.id.google_signup);
        btnFacebook=findViewById(R.id.facebook_signUp);
        tvSignUp=findViewById(R.id.tv_Signup);
        tvForgetPass=findViewById(R.id.tv_forgetpass);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
               // finish();
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();

            }
        });

    }
}