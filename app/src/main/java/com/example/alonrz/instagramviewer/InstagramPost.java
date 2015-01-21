package com.example.alonrz.instagramviewer;

/**
 * Created by alonrz on 1/20/15.
 */
public class InstagramPost {
    //username, photo_url, mPhotoHeight, caption
    private String username, photoUrl, Caption;
    private int mPhotoHeight;

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
}
