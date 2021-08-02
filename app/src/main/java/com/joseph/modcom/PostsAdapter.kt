package com.joseph.modcom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso

class PostsAdapter constructor(options: FirebaseRecyclerOptions<Post>):
        FirebaseRecyclerAdapter<Post, PostsAdapter.PostViewModel>(options) {

    class PostViewModel internal constructor(private var view: View) : RecyclerView.ViewHolder(view) {

        internal fun setProductName(
                post: Post,
                holder: PostViewModel
        ) {


            val ivImage: ImageView = itemView.findViewById(R.id.ivPost)
            val textTitle: MaterialTextView = itemView.findViewById(R.id.tvTitle)
            val tvDescription: MaterialTextView = itemView.findViewById(R.id.tvDescription)
            val btnReadMore: MaterialButton = itemView.findViewById(R.id.btnMore)
            apply {
                Picasso.get().load(post.image).into(ivImage)
                textTitle.text = post.title
                tvDescription.text = "${post.description}\nPosted on :${post.timestamp}"
                btnReadMore.setOnClickListener {
                    Toast.makeText(itemView.context, "This is your assignment", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewModel {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item, parent, false)
        return PostViewModel(view)
    }


    override fun onBindViewHolder(holder: PostViewModel, position: Int, model: Post) {
        holder.setProductName(model,holder)
    }
}