package com.minhtu.firebaseproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private static final String TAG = "FAILED SIGN IN";
    private EditText mEmailField;
    private EditText mPasswordField;

    private Button mButtonLogin;
    private Button mButtonSignup;

    private ProgressDialog mProgressDialog;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailField = (EditText) findViewById(R.id.activity_main_userEmail);
        mPasswordField = (EditText) findViewById(R.id.activity_main_userPassword);

        mButtonLogin = (Button) findViewById(R.id.activity_login_loginButton);
        mButtonSignup = (Button) findViewById(R.id.activity_login_registerButton);


        mAuth = FirebaseAuth.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null){
                    startActivity(new Intent(Login.this, Customer_Activity.class));
                    finish();
                }
            }
        };

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();
            }
        });

        mButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register_Activity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void startSignIn() {
        String email = mEmailField.getText().toString().trim();
        final String password = mPasswordField.getText().toString();

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Email field is empty", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Password field is empty", Toast.LENGTH_LONG).show();
            return;
        }

        mProgressDialog = new ProgressDialog(Login.this);
        mProgressDialog.setTitle(getString(R.string.signin_progress));
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mProgressDialog.dismiss();
                if(!task.isSuccessful()){
                    Log.w(TAG, "signInWithEmail: failed", task.getException());
                    Toast.makeText(Login.this, R.string.auth_failed, Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(Login.this, Customer_Activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}

