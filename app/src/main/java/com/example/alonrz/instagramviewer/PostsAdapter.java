package com.example.alonrz.instagramviewer;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by alonrz on 1/20/15.
 */
public class PostsAdapter extends ArrayAdapter<InstagramPost> {

    public PostsAdapter(Context context, ArrayList<InstagramPost> posts) {
        super(context, R.layout.item_photo, posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPost post = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }
        // Lookup view for data population
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        // Populate the data into the template view using the data object
        String formattedUserNameAndCaption = "<b>"+post.getUsername()+"</b> " +
                "<i>"+post.getCaption()+"</i>";
        tvCaption.setText(Html.fromHtml(formattedUserNameAndCaption));
        ivPhoto.setImageResource(0);//reset the image view
        Picasso.with(getContext()).load(post.getPhotoUrl()).into(ivPhoto); //ivPhoto.set...;
        // Return the completed view to render on screen
        return convertView;
    }
}