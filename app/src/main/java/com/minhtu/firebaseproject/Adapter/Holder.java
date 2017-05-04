package com.minhtu.firebaseproject.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.minhtu.firebaseproject.R;

/**
 * Created by minhtu on 5/3/17.
 */

public class Holder extends RecyclerView.ViewHolder {

    TextView nameTxt;
    TextView descTxt;
    TextView priceTxt;
    ImageView img;

    public Holder(View itemView) {
        super(itemView);

        nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);
        descTxt = (TextView) itemView.findViewById(R.id.descTxt);
        priceTxt = (TextView) itemView.findViewById(R.id.priceTxt);
        img = (ImageView) itemView.findViewById(R.id.shoesImage);
    }
}
