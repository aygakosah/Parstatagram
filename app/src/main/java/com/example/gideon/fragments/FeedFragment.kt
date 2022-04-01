package com.example.gideon.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gideon.Post
import com.example.gideon.PostAdapter
import com.example.gideon.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery


open class FeedFragment : Fragment() {
    lateinit var rvPosts: RecyclerView
    lateinit var adapter: PostAdapter
    val posts1 = ArrayList<Post>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvPosts=view.findViewById(R.id.rvPosts)
        adapter = PostAdapter(posts1)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        rvPosts.layoutManager=linearLayoutManager
        rvPosts.adapter = adapter
        queryPosts()

    }


    open fun queryPosts() {
        // Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USER)
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

    companion object {
        const val TAG = "FEED"
    }

}