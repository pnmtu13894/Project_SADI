package com.minhtu.firebaseproject;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.minhtu.firebaseproject.FirebaseHelper.FirebaseHelper;
import com.minhtu.firebaseproject.Utils.Utils;

public class Manager_Activity extends AppCompatActivity {


    private EditText mName, mPrice, mDescription, mBrand, mUrl;
    private Button mButtonSignout, mButtonSave, mButtonOrder, mButtonReport;
    private RecyclerView recyclerView;
    private FirebaseHelper helper;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user == null){
                    startActivity(new Intent(Manager_Activity.this, Login.class));
                    finish();
                }
            }
        };



        recyclerView = (RecyclerView) findViewById(R.id.mRecylcerID);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        helper = new FirebaseHelper(this, Utils.FIRE_BASE_URL, recyclerView);

        helper.showManagerData();
        helper.refreshData();

        mButtonSignout = (Button) findViewById(R.id.activity_signout);
        mButtonOrder = (Button) findViewById(R.id.activity_manager_order);
        mButtonReport = (Button) findViewById(R.id.activity_manager_report);

        mButtonSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
        mButtonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        mButtonReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Manager_Activity.this, Report_Activity.class));
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInputDialog();
            }
        });
    }

    private void displayInputDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setTitle("Save Data Into Firebase");
        dialog.setContentView(R.layout.activity_input_dialog);

        mName = (EditText) dialog.findViewById(R.id.nameEditText);
        mDescription = (EditText) dialog.findViewById(R.id.descEditText);
        mPrice = (EditText) dialog.findViewById(R.id.priceEditText);
        mBrand = (EditText) dialog.findViewById(R.id.brandEditText);
        mUrl = (EditText) dialog.findViewById(R.id.urlEditText);

        mButtonSave = (Button) dialog.findViewById(R.id.activity_save);



        mButtonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if((TextUtils.isEmpty(mName.getText().toString()) && TextUtils.isEmpty(mDescription.getText().toString())) || TextUtils.isEmpty(mUrl.getText().toString())){
                        Toast.makeText(Manager_Activity.this, "Fields are empty! Please fill them", Toast.LENGTH_LONG).show();
                    } else {
                        helper.saveShoes(mName.getText().toString(),
                                mUrl.getText().toString(),
                                Float.parseFloat(mPrice.getText().toString()),
                                mDescription.getText().toString(),
                                mBrand.getText().toString());

                        mName.setText("");
                        mDescription.setText("");
                        mPrice.setText("");
                        mBrand.setText("");
                        mUrl.setText("");
                    }
                }
        });

        dialog.show();

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void signOut(){
        mAuth.signOut();
    }

}
