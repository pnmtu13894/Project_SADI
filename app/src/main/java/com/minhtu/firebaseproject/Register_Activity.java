package com.minhtu.firebaseproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.minhtu.firebaseproject.Entities.User;
import com.minhtu.firebaseproject.FirebaseHelper.FirebaseHelper;
import com.minhtu.firebaseproject.Utils.Utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;

public class Register_Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText usernameField;
    private EditText userEmailField;

    private Button registerButton;
    private Button mSigninButton;

    private FirebaseHelper firebaseHelper;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);


        usernameField = (EditText) findViewById(R.id.activity_register_userName);
        userEmailField = (EditText) findViewById(R.id.activity_register_userEmail);

        registerButton = (Button) findViewById(R.id.activity_register_registerButton);
        mSigninButton = (Button) findViewById(R.id.activity_register_signinButton);

        mAuth = FirebaseAuth.getInstance();

        mSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register_Activity.this, Login.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegister();
            }
        });


    }

    private void startRegister() {


        SecureRandom random = new SecureRandom();
        final String randomPassword = new BigInteger(32, random).toString();

        final String userName = usernameField.getText().toString().trim();
        final String userEmail = userEmailField.getText().toString().trim();

        if(TextUtils.isEmpty(userName)) {
            Toast.makeText(getApplicationContext(), "Enter user name!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(getString(R.string.register_progress));
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.show();


        mAuth.createUserWithEmailAndPassword(userEmail, randomPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    mProgressDialog.dismiss();
                    Toast.makeText(Register_Activity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                } else{
                        mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Register_Activity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            } else {
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReferenceFromUrl(Utils.FIRE_BASE_URL);

                                //Create key for user
//                                String customerID = reference.push().getKey();

                                firebaseHelper = new FirebaseHelper(Register_Activity.this, Utils.FIRE_BASE_URL);

                                HashMap<String,Object> timeJoined = new HashMap<String, Object>();
                                timeJoined.put("dateJoined", ServerValue.TIMESTAMP);

                                User user = new User(userName, userEmail, timeJoined, false, 1);

//                                reference.child("email").setValue(userEmail);
//                                reference.child("name").setValue(userName);
//                                reference.child("hasLoggedInWithPassword").setValue(false);
//                                reference.child("dateJoined").setValue(timeJoined);

                                firebaseHelper.saveUsers(mAuth.getCurrentUser().getUid() ,user);

                                Toast.makeText(Register_Activity.this, "Please check your email", Toast.LENGTH_LONG).show();
                                mProgressDialog.dismiss();
                            }
                        }
                    });

                }
            }
        });
    }

}



