package com.lokendrasingh.welcome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    EditText txtemail, txtpassword, txtconfirmpassword, name,father;
    RadioButton gender_male, gender_female;
    Button btnRegister;
    String Gender = "";
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebasedatabase;      // initiliazing FirebaseDatabase but we can remove it
     DatabaseReference databaseReference;       // initiliazing DatabaseReference
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("Signup");
        txtemail= findViewById(R.id.email);
        txtpassword= findViewById(R.id.password);
        txtconfirmpassword= findViewById(R.id.confirmpassword);
        btnRegister= findViewById(R.id.register);
        progressBar= findViewById(R.id.progressbar);
        name= findViewById(R.id.name);
        father= findViewById(R.id.father);
        gender_female= findViewById(R.id.female);
        gender_male= findViewById(R.id.male);
        databaseReference= FirebaseDatabase.getInstance(). getReference("Student");

        firebaseAuth= FirebaseAuth.getInstance();       // we get the object of firebaseAuth
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String FullName         = name.getText().toString();
                String fatherName       = father.getText().toString();
                String email            = txtemail.getText().toString().trim();     // trim function will cancel the spaces
                String password         = txtpassword.getText().toString().trim();
                String confirmpassword  = txtconfirmpassword.getText().toString().trim();

                if(gender_male.isChecked())
                {
                    Gender= "Male";
                }

                if(gender_female.isChecked())
                {
                    Gender= "Female";
                }

                if(TextUtils.isEmpty(FullName))                            // TextUtils is used for validation of TextView or EditText views
                {
                    Toast.makeText(Signup.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(fatherName))                            // TextUtils is used for validation of TextView or EditText views
                {
                    Toast.makeText(Signup.this, "Enter father's name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(email))                            // TextUtils is used for validation of TextView or EditText views
                {
                    Toast.makeText(Signup.this, "enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(Signup.this, "enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmpassword))
                {
                    Toast.makeText(Signup.this, "confirm password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6)
                {
                    Toast.makeText(Signup.this, "Password is too short", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.VISIBLE);
                if(password.equals(confirmpassword))
                {
                    // we write here code for authentication for password validation

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        student information= new student(
                                                FullName,
                                                fatherName,
                                                email,
                                                Gender

                                        );

                                        //passing path and object in database
                                        FirebaseDatabase.getInstance().getReference("Student").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                Toast.makeText(Signup.this, "Registration complete",Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                            }
                                        });




                                    } else {
                                Toast.makeText(Signup.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });
                }
            }
            });
    }
}