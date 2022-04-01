package com.example.gideon.fragments

import android.util.Log
import com.example.gideon.Post
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class ProfileFragment: FeedFragment() {
    override fun queryPosts(){
        // Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USER)
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser())
        query.addDescendingOrder("createdAt")
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e != null) {
                    Log.e(TAG, "Error puling posts")
                } else {
                    if (posts != null) {
                        posts1.addAll(posts)
                        adapter.notifyDataSetChanged()
                        for (post in posts) {

                            Log.i(
                                TAG,
                                "Post: " + post.getDescription() + "username: " + post.getUser()
                            )

                        }
                    }
                }
            }

        })

    }
}