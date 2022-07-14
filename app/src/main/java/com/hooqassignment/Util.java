package com.hooqassignment;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class Util {
    public static void loadImage(ImageView img, String url){
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(img.getContext());
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(circularProgressDrawable);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();
        requestOptions.fitCenter();

        Glide.with(img.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(url)
                .into(img);

    }

    public static void setSpanString(String string1, String string2, TextView textView) {
        SpannableStringBuilder builder=new SpannableStringBuilder();
        SpannableString txtSpannable= new SpannableString(string1);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        txtSpannable.setSpan(boldSpan, 0, string1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(txtSpannable);
        builder.append(string2);
        textView.setText(builder, TextView.BufferType.SPANNABLE);
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }

    public static String getImagePath(String imageName){
        return Constants.IMAGE_BASE_URL + Constants.IMAGE_SIZE + imageName;
    }

    public static String getBackdropImagePath(String imageName){
        return Constants.IMAGE_BASE_URL + Constants.BACKTROP_IMAGE_SIZE + imageName;
    }

}
