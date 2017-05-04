package com.minhtu.firebaseproject.FirebaseHelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minhtu.firebaseproject.Adapter.ProductAdapter;
import com.minhtu.firebaseproject.Entities.Shoes;

import java.util.ArrayList;

/**
 * Created by minhtu on 5/1/17.
 */

public class FirebaseHelper {

    private Context c;
    private String DB_URL;
    private RecyclerView rv;

    private DatabaseReference db;
    private ArrayList<Shoes> shoesList = new ArrayList<>();

    private ProductAdapter adapter;


    public FirebaseHelper(Context c, String DB_URL, RecyclerView rv) {
        this.c = c;
        this.DB_URL = DB_URL;
        this.rv = rv;


        db = FirebaseDatabase.getInstance().getReferenceFromUrl(DB_URL);

    }

    public void saveOnline(String name, String url, float price, String description, String brand){

        String shoesID = db.push().getKey();

        Shoes shoesList = new Shoes();
        shoesList.setShoesID(shoesID);
        shoesList.setName(name);
        shoesList.setPrice(price);
        shoesList.setDescription(description);
        shoesList.setBrand(brand);
        shoesList.setImageUrl(url);

//        String productID = db.push().getKey();

        db.child("shoes").child(shoesID).setValue(shoesList);
    }

    public void showData(){
        db.child("shoes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void refreshData(){

        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                showData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                showData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showData(DataSnapshot dataSnapshot){
        shoesList.clear();

        for(DataSnapshot ds : dataSnapshot.getChildren()){
            Shoes shoes = ds.getValue(Shoes.class);
            shoesList.add(shoes);
        }

        if(shoesList.size() > 0){
            adapter = new ProductAdapter(c, shoesList);
            rv.setAdapter(adapter);
        } else{
            Toast.makeText(c, "No data to show", Toast.LENGTH_SHORT).show();
        }
    }


//    public void deleteData(String shoesID){
//        db.child("shoes").child(shoesID).removeValue();
//    }
}
