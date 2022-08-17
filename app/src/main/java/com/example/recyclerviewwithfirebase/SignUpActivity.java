package com.example.recyclerviewwithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    EditText et_Name,et_Pass,et_ConfirmPass,et_Email;
    Button btn_SignUp;
    TextView tv_SignIn;
    //Email pattern validation
    final String email_Pattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_Name=(EditText)findViewById(R.id.etName);
        et_Pass=(EditText)findViewById(R.id.etPass);
        et_ConfirmPass=(EditText)findViewById(R.id.etConfirmPass);
        et_Email=(EditText)findViewById(R.id.etEmail);

        btn_SignUp=(Button)findViewById(R.id.btnSignUp);

        tv_SignIn=(TextView) findViewById(R.id.tvSignIn);
        progressDialog=new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PerforAuth();
            }
        });

        tv_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });
    }
    public void PerforAuth(){
        String name=et_Name.getText().toString();
        String email=et_Email.getText().toString();
        String password=et_Pass.getText().toString();
        String confirmPass=et_ConfirmPass.getText().toString();

        if(!email.matches(email_Pattern)){
            et_Email.setError("Please Enter Correct Email");
        }
        else if(name.isEmpty())
        {
            et_Name.setError("Please Enter Your Name");
        }
        else if(password.isEmpty()||password.length()<6)
        {
            et_Pass.setError("Please enter Your Password");
        }
        else if(!password.equals(confirmPass))
        {
            et_ConfirmPass.setError("Pass does not matches");
        }
        else
        {
            progressDialog.setMessage("Wait for the Registration");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(SignUpActivity.this,"Registration Successfull",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(SignUpActivity.this,""+task.getException(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
         Intent intent =new Intent(SignUpActivity.this, MainActivity.class);
         intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
         startActivity(intent);

    }
}