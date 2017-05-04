package com.minhtu.firebaseproject.Picasso;

import android.content.Context;
import android.widget.ImageView;

import com.minhtu.firebaseproject.R;
import com.squareup.picasso.Picasso;

/**
 * Created by minhtu on 5/3/17.
 */

public class PicassoClient {


    public static void downloadImage(Context c, String url, ImageView img){
        if(url != null && url.length()>0){
            Picasso.with(c).load(url).placeholder(R.drawable.background_screen_two).into(img);
        } else{
            Picasso.with(c).load(R.drawable.background_screen_two).into(img);
        }
    }
}
