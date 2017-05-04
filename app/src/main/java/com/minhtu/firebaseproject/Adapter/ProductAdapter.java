package com.minhtu.firebaseproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.minhtu.firebaseproject.Entities.Shoes;
import com.minhtu.firebaseproject.Picasso.PicassoClient;
import com.minhtu.firebaseproject.R;
import com.minhtu.firebaseproject.Update_Shoes_Activity;

import java.util.ArrayList;

/**
 * Created by minhtu on 5/1/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<Holder>{

    private Context c;
    private ArrayList<Shoes> shoesList;


    public ProductAdapter(Context c, ArrayList<Shoes> shoesList) {
        this.c = c;
        this.shoesList = shoesList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_model, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        final Shoes shoes = shoesList.get(position);

        holder.nameTxt.setText(shoes.getName());
        holder.priceTxt.setText("$ " + String.format("%.0f", shoes.getPrice()));
        holder.descTxt.setText(shoes.getDescription());

        PicassoClient.downloadImage(c, shoesList.get(position).getImageUrl(), holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(c, shoes.getShoesID(), Toast.LENGTH_SHORT).show();
                openUpdateActivity(shoes.getShoesID(), shoes.getName(), shoes.getPrice(), shoes.getDescription(), shoes.getBrand(), shoes.getImageUrl());
            }
        });
    }

    private void openUpdateActivity(String shoesID, String name, float price, String description, String brand, String imageUrl) {

        Intent intent = new Intent(c, Update_Shoes_Activity.class);
        intent.putExtra("KEY_SHOES_ID", shoesID);
        intent.putExtra("KEY_NAME", name);
        intent.putExtra("KEY_PRICE", String.format("%.0f", price));
        intent.putExtra("KEY_DESCRIPTION", description);
        intent.putExtra("KEY_BRAND", brand);
        intent.putExtra("KEY_IMAGE_URL", imageUrl);

        c.startActivity(intent);

    }


    @Override
    public int getItemCount() {
        return shoesList.size();
    }


}
