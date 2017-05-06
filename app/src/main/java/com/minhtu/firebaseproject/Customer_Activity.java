package com.minhtu.firebaseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.minhtu.firebaseproject.FirebaseHelper.FirebaseHelper;
import com.minhtu.firebaseproject.Utils.Utils;

public class Customer_Activity extends AppCompatActivity {

//    private EditText mName, mPrice, mDescription, mBrand, mUrl;
    private Button mButtonSignout, mShoppingCartButton;
    private RecyclerView recyclerView;
    private FirebaseHelper helper;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if(user == null){
                    startActivity(new Intent(Customer_Activity.this, Login.class));
                    finish();
                }
            }
        };

        recyclerView = (RecyclerView) findViewById(R.id.mCustomerRecylcerID);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mButtonSignout = (Button) findViewById(R.id.activity_customer_signout);
        mShoppingCartButton = (Button) findViewById(R.id.activity_shoppingCart);

        helper = new FirebaseHelper(this, Utils.FIRE_BASE_URL, recyclerView);

        helper.showCustomerData();
        helper.refreshData();

        mButtonSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
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
