package com.example.alonrz.instagramviewer;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class StreamActivity extends ActionBarActivity {

    public final static String CLIENT_ID = "a84f39c11e27438e9e34aec687afec1f";
    private ArrayList<InstagramPost> photos;
    private PostsAdapter adapter;

    private SwipeRefreshLayout swipeContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);

        photos = new ArrayList<>();
        //setting pull-to-refresh
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchPopularPhotosAsync();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //1. get data
        fetchPopularPhotosAsync();

        //2. Build Adapter
         adapter = new PostsAdapter(this, photos);
        //3. find listview
        ListView lvPosts = (ListView)findViewById(R.id.lvPosts);
        //4. bind both
        lvPosts.setAdapter(adapter);
    }

    private void fetchPopularPhotosAsync() {
//        photos = new ArrayList<>(); //init array of photos

        //https://api.instagram.com/v1/media/popular?client_id=<CLIENT_ID>
        String popularURL = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;
        //Define client
        AsyncHttpClient client = new AsyncHttpClient();
        //fetch data
        client.get(popularURL, new JsonHttpResponseHandler() {
            //define success and failure getting the JSON

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //fired with a successful response
                //data → [x] → images → standard_resolution → url
                //data → [x] → images → standard_resolution → height
                //data → [x] → caption → text
                //data → [x] → user → username
                //data → [x] → user → profile_picture
                //data → [x] → likes → count
                //Comments:
                //data → [x] → comments → data(array) → text
                //data → [x] → comments → data(array) → from → username
                JSONArray photosJSON = null;
//                Log.i("DEBUG", response.toString());
                try {
                    photos.clear();
                    photosJSON = response.getJSONArray("data");
                    for (int i = 0; i < photosJSON.length(); i++)
                    {
                        JSONObject photoJSON = photosJSON.getJSONObject(i);
                        InstagramPost photo = new InstagramPost();
                        if(photoJSON.getJSONObject("user")  !=null) {
                            photo.setUsername(photoJSON.getJSONObject("user").getString("username"));
                            photo.setProfilePicture(photoJSON.getJSONObject("user").getString("profile_picture"));
                        }
                        if(!photoJSON.isNull("caption"))
                            photo.setCaption(photoJSON.getJSONObject("caption").getString("text"));
                        if(!photoJSON.isNull("images"))
                            photo.setPhotoHeight(photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getInt("height"));
                        if(photoJSON.getJSONObject("images") != null)
                            photo.setPhotoUrl(photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url"));
                        if(!photoJSON.isNull("likes"))
                            photo.setLikes(photoJSON.getJSONObject("likes").getInt("count"));
                        if(!photoJSON.isNull("comments"))
                        {
                            JSONArray comments = photoJSON.getJSONObject("comments").getJSONArray("data");
                            JSONObject comment;
                            for (int j=0; j<comments.length(); j++)
                            {
                                comment = comments.getJSONObject(j);
                                String commentUsername;
                                String commentText;
                                if(comment!=null) {
                                    commentText = (comment.getString("text"));
                                    commentUsername = comment.getJSONObject("from").getString("username");
                                    photo.addComment(new InstagramPost.InstaComment(commentText, commentUsername));
                                }
                            }
                        }

                        photos.add(photo);
                    }
                    adapter.notifyDataSetChanged();
                    swipeContainer.setRefreshing(false);

                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stream, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
