package com.minhtu.firebaseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minhtu.firebaseproject.Entities.Shoes;

import java.util.HashMap;
import java.util.Map;

public class Update_Shoes_Activity extends AppCompatActivity {

    private TextView mName, mPrice, mDescription, mBrand, mImageUrl;
    private Button mUpdate, mDelete;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_shoes);

        mName = (TextView) findViewById(R.id.activity_update_nameEditText);
        mPrice = (TextView) findViewById(R.id.activity_update_priceEditText);
        mDescription = (TextView) findViewById(R.id.activity_update_descEditText);
        mBrand = (TextView) findViewById(R.id.activity_update_brandEditText);
        mImageUrl = (TextView) findViewById(R.id.activity_update_urlEditText);

        mUpdate = (Button) findViewById(R.id.activity_update);
        mDelete = (Button) findViewById(R.id.activity_delete);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("shoes");

        Intent intent = getIntent();

        final String shoesID = intent.getExtras().getString("KEY_SHOES_ID");
        String name = intent.getExtras().getString("KEY_NAME");
        String price = intent.getExtras().getString("KEY_PRICE");
        String description = intent.getExtras().getString("KEY_DESCRIPTION");
        String brand = intent.getExtras().getString("KEY_BRAND");
        String imageUrl = intent.getExtras().getString("KEY_IMAGE_URL");

        mName.setText(name);
        mPrice.setText(price);
        mDescription.setText(description);
        mBrand.setText(brand);
        mImageUrl.setText(imageUrl);

        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Shoes shoes = new Shoes();

                shoes.setName(mName.getText().toString());
                shoes.setPrice(Float.parseFloat(mPrice.getText().toString()));
                shoes.setDescription(mDescription.getText().toString());
                shoes.setBrand(mBrand.getText().toString());
                shoes.setImageUrl(mImageUrl.getText().toString());
                shoes.setShoesID(shoesID);


                if(shoesID != null) {
//                    shoes.setShoesID(mDatabase.push().getKey());
                    Map<String, Object> shoesMap = new HashMap<String, Object>();
                    shoesMap.put("brand", mBrand.getText().toString());
                    shoesMap.put("description", mDescription.getText().toString());
                    shoesMap.put("imageUrl", mImageUrl.getText().toString());
                    shoesMap.put("name", mName.getText().toString());
                    shoesMap.put("price",Float.parseFloat(mPrice.getText().toString()));
                    shoesMap.put("shoesID", shoesID);
                    mDatabase.child(shoesID).updateChildren(shoesMap);

                    backToCustomerActivity();
                }
            }
        });

        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(shoesID != null){
                    mDatabase.child(shoesID).removeValue();
                    Toast.makeText(Update_Shoes_Activity.this, "Successfully Delete Item!!!", Toast.LENGTH_SHORT).show();
                    backToCustomerActivity();
                } else {
                    Toast.makeText(Update_Shoes_Activity.this, "Cannot Delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void backToCustomerActivity(){
        startActivity(new Intent(Update_Shoes_Activity.this, Manager_Activity.class));
    }


}
