package com.example.recyclerviewwithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    TextView tvForgetPass,tvSignUp;
    EditText et_Email,et_Pass;
    ImageView IVGoogle,IVFaceBook,IVTwitter,IVLinkedIn;

    //Email pattern validation
    final String email_Pattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnLogin=(Button) findViewById(R.id.Btn_LoginIn);
        IVGoogle=(ImageView)findViewById(R.id.google_signup);
        IVFaceBook=(ImageView)findViewById(R.id.facebook_signUp);
        IVTwitter=(ImageView)findViewById(R.id.twitter_signIn);
        IVLinkedIn=(ImageView)findViewById(R.id.linkedIn_signIn);

        tvSignUp=findViewById(R.id.tv_Signup);
        tvForgetPass=findViewById(R.id.tv_forgetpass);

        et_Email=findViewById(R.id.etEmail);
        et_Pass=findViewById(R.id.etPass);

        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perforLogin();
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//               // finish();
            }
        });
        IVGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, GoogleSignInActivity.class));
//               // finish();
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

    private void perforLogin() {
        String email=et_Email.getText().toString();
        String password=et_Pass.getText().toString();

        if(!email.matches(email_Pattern)){
            et_Email.setError("Please Enter Correct Email");
        }
        else if(password.isEmpty()||password.length()<6)
        {
            et_Pass.setError("Please enter Your Password");
        }
        else {
            progressDialog.setMessage("Wait for the Login");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        senUserToNewActivity();
                        Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(
                                LoginActivity.this,"Login Fail",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private void senUserToNewActivity() {
        Intent intent =new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}