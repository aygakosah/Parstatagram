package com.example.gideon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.parse.*


class MainActivity : AppCompatActivity() {
    lateinit var rvPosts: RecyclerView
    lateinit var adapter: PostAdapter
    val posts1 = ArrayList<Post>()
//    val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPosts=findViewById(R.id.rvPosts)
        adapter = PostAdapter(posts1)
        val linearLayoutManager = LinearLayoutManager(this)
        rvPosts.layoutManager=linearLayoutManager
        rvPosts.adapter = adapter


//        bottomNavigationView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener{
//            @Override
//            fun onNavigationItemSelected(item: MenuItem) {
//                when(item.itemId){
//                    R.id.takePic -> {
//                        val intent = Intent(this, CreatePostActivity::class.java)
//                        startActivity(intent)
//                    }
//                }
//                 false;
//            }
//            true
//        })



        queryPosts()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.takePic){
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
        return super.onOptionsItemSelected(item)
    }









    fun queryPosts() {

        // Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USER)
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e != null) {
                    Log.e(CreatePostActivity.TAG, "Error puling posts")
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
        const val TAG = "MainActivity"
        val REQUEST_CODE=10
    }


}


