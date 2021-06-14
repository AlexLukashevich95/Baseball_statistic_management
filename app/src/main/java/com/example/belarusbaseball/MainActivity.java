package com.example.belarusbaseball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLogIn;
    private Button btnAsGuest;
    private EditText editEmail;
    private EditText editPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogIn = (Button) findViewById(R.id.buttonLogIn);
        btnAsGuest = (Button) findViewById(R.id.buttonAsGuest);
        btnAsGuest.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);
        editEmail = (EditText) findViewById(R.id.editTextEmailAddress);
        editPassword = (EditText) findViewById(R.id.editTextPassword);
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonLogIn:
                userLogIn();
                break;
            case R.id.buttonAsGuest:
                Intent intent = new Intent(".MenuActivity");
                startActivity(intent);
                break;
        }
    }

    private void userLogIn() {
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        if(email.isEmpty()){
            editEmail.setError("Email is required!");
            editEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Please enter a valid email!");
            editEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editPassword.setError("Password is required!");
            editPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            editPassword.setError("Minimum password length is 6 characters");
            editPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(".MenuActivity");
                    startActivity(intent);
                    Toast.makeText(MainActivity.this,"You successfully logged in system!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Failed to log in! Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}