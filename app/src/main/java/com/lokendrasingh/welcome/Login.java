package com.lokendrasingh.welcome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText txtemail, txtpassword;
    Button btnlogin;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");
        txtemail= findViewById(R.id.text_email);
        txtpassword= findViewById(R.id.text_password);
        btnlogin= findViewById(R.id.button_login);
        firebaseAuth= FirebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= txtemail.getText().toString().trim();
                String password= txtpassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(Login.this,"Enter Password", Toast.LENGTH_SHORT).show();
                            return;
                }
                if(password.length()<6)
                {
                    Toast.makeText(Login.this, "Password is too short",Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                } else {
                                    Toast.makeText(Login.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });
    }

    public void btnSignup(View view) {
        startActivity(new Intent(getApplicationContext(), Signup.class));
    }
}