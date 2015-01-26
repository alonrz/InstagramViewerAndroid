package com.example.alonrz.instagramviewer;

import java.util.ArrayList;

/**
 * Created by alonrz on 1/20/15.
 */
public class InstagramPost {
    //username, photo_url, mPhotoHeight, caption
    private String username;
    private String photoUrl;
    private String Caption = "";
    private String profilePicture;
    private int mPhotoHeight;
    private int mLikes;
    private ArrayList<InstaComment> comments;

    public InstagramPost() {
        comments = new ArrayList<>();
    }

    public void addComment(InstaComment cm)
    {
        this.comments.add(cm);
    }

    public InstaComment getComment(int position)
    {
        return this.comments.get(position);
    }

    public int getCommentLength()
    {
        return this.comments.size();
    }

    public String getProfilePicture() { return profilePicture; }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public int getLikes() {
        return mLikes;
    }

    public void setLikes(int likes) {
        this.mLikes = likes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCaption() {
        return Caption;
    }

    public void setCaption(String caption) {
        Caption = caption;
    }

    public int getPhotoHeight() {
        return mPhotoHeight;
    }

    public void setPhotoHeight(int mPhotoHeight) {
        this.mPhotoHeight = mPhotoHeight;
    }

    public static class InstaComment
    {
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        private String text;
        private String userName;

        public InstaComment(String text, String userName) {
            this.text = text;
            this.userName = userName;
        }
    }
}
