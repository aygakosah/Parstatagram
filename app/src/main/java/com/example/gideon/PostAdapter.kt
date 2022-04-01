package com.example.gideon

import android.R.attr
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parse.ParseFile
import java.io.File
import android.R.attr.thumbnail





class PostAdapter( val posts:
                     ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.feed_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post: Post = posts.get(position)
        holder.tvPostDescription.text = post.getDescription()
        holder.tvUserName.text = post.getUser()?.username
        val photofile: ParseFile? = post.getImage()
        val photoFile2: ParseFile? = post.getUser()?.getParseFile("ProfileImage")
        if (photofile != null) {
            val imageUrl: String = photofile.getUrl()
            Glide.with(holder.itemView).load(imageUrl).into(holder.ivPostImage)
        }

        if(photoFile2 != null){
            val imageUrl2: String? = photoFile2?.getUrl()
            Glide.with(holder.itemView).load(imageUrl2).into(holder.ivProfileImage)
        }

    }

    override fun getItemCount() = posts.size



    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val  ivPostImage = itemView.findViewById<ImageView>(R.id.ivPostImage)
        val  tvPostDescription = itemView.findViewById<TextView>(R.id.tvPostDescription)
        val  ivProfileImage = itemView.findViewById<ImageView>(R.id.ivProfileImage)
        val  tvUserName = itemView.findViewById<TextView>(R.id.tvUserName)
    }

}