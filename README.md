# Instagram Viewer App for Android
This ia a read-only Viewer app for Instagram. Can handle some basic instagram viewing options without the need to sign in with an Instagram account. The focus for this project is getting a handle on layouts. 

External libraries used:
- [RoundedImageView](https://github.com/vinc3m1/RoundedImageView)
- [Android Asynchronous Http Client](http://loopj.com/android-async-http/)
- [Picasso](http://square.github.io/picasso/)

User stories:
 * [x] User can view a stream of the most popular Instagram posts form that moment as presented by Instagram API.
 * [x] User can see who posted (username + profile picture).
 * [x] User can see the picture posted as well as the caption attached by the poster.
 * [x] User can see a subset of the comments for that post, as selected by Instagram API. Note: full set of comments requires to sign in which this app does not support. 
 * [x] User can see how many like a post has.
 * [x] User can pull-to-refresh.
 * [x] User will get a default picture as the main image as well as user profile image until they load. 
 
Future development: 
 * [x] Showing a shorter list of comments and allowing to see the full subset in a different view, preferably a Fragment.
 * [x] Adding timestamps to posts.


![Video Walkthrough](InstagramViewerAppWalkthough.gif)

GIF created with [LiceCap](http://www.cockos.com/licecap/).

