package com.example.recyclerviewwithfirebase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    EditText name,course,email,tUrl;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name=(EditText)findViewById(R.id.et_name);
        course=(EditText)findViewById(R.id.et_course);
        email=(EditText)findViewById(R.id.et_email);
        tUrl=(EditText)findViewById(R.id.et_url);

        btnAdd=(Button)findViewById(R.id.btn_add);
        btnBack=(Button)findViewById(R.id.btn_back);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                clearAll();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void insertData(){

        Map<String,Object> map=new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("course",course.getText().toString());
        map.put("email",email.getText().toString());
        map.put("tUrl",tUrl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("teachers").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this,"Data Updated Successfully",Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void clearAll(){
        name.setText("");
        course.setText("");
        email.setText("");
        tUrl.setText("");
    }
}