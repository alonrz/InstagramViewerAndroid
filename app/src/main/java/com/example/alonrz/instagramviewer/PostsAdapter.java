package com.example.alonrz.instagramviewer;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;


/**
 * Created by alonrz on 1/20/15.
 */
public class PostsAdapter extends ArrayAdapter<InstagramPost> {

    public static class ViewHolder
    {
        ImageView ivPhoto;
        TextView tvProfileName;
        TextView tvLikes;
        TextView tvCaption;
        ImageView ivProfilePic;
        Transformation transformation;
        LinearLayout llComments;

    }
    public PostsAdapter(Context context, ArrayList<InstagramPost> posts) {
        super(context, R.layout.item_photo, posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPost post = getItem(position);
        ViewHolder viewHolder;
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);

            viewHolder.ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
            viewHolder.tvLikes = (TextView) convertView.findViewById(R.id.tvLikes);
            viewHolder.tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
            viewHolder.ivProfilePic = (ImageView) convertView.findViewById(R.id.ivProfilePic);
            viewHolder.tvProfileName = (TextView) convertView.findViewById(R.id.tvProfileName);
            viewHolder.llComments = (LinearLayout) convertView.findViewById(R.id.llComments);
            viewHolder.transformation = new RoundedTransformationBuilder()
                    .borderColor(Color.BLACK)
                    .borderWidthDp(2)
                    .cornerRadius(50)
                    .oval(true)
                    .build();
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        // Lookup view for data population

        // Populate the data into the template view using the data object
        String formattedUserNameAndCaption = "<b>"+post.getUsername()+"</b> " +
                "<i>"+post.getCaption()+"</i>";
        viewHolder.tvCaption.setText(Html.fromHtml(formattedUserNameAndCaption));
        viewHolder.tvProfileName.setText(post.getUsername());
        viewHolder.tvLikes.setText(Integer.toString(post.getLikes()));
        viewHolder.ivPhoto.setImageResource(0);//reset the image view

        //main picture
        Picasso.with(getContext())
                .load(post.getPhotoUrl())
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.error_icon)
                .fit()
                .into(viewHolder.ivPhoto); //ivPhoto.set...;

        //profile picture
        Picasso.with(getContext())
                .load(post.getProfilePicture())
                .transform(viewHolder.transformation)
                .placeholder(R.drawable.user_default_circle)
                .error(R.drawable.error_icon)
                .into(viewHolder.ivProfilePic);

        viewHolder.llComments.removeAllViews();
        for(int i=0; i< post.getCommentLength(); i++)
        {
            View view = viewHolder.llComments.inflate(getContext(), R.layout.item_inside_row, null);
            TextView tvComment = (TextView) view.findViewById(R.id.tvComment);
            String formattedComment = "<b>"+post.getComment(i).getUserName()+"</b>: " + post.getComment(i).getText();
            tvComment.setText(Html.fromHtml(formattedComment));
            viewHolder.llComments.addView(view);
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
