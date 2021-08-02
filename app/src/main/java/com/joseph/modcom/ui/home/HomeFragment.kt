package com.joseph.modcom.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.joseph.modcom.Post
import com.joseph.modcom.PostsAdapter
import com.joseph.modcom.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recyclerPosts: RecyclerView
    private var mAdapter: PostsAdapter? = null
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it

        val query= FirebaseDatabase.getInstance().reference.child("MODCOM").child("POSTS")
        val options = FirebaseRecyclerOptions.Builder<Post>().setQuery(query, Post::class.java).build()
        mAdapter = PostsAdapter(options)
        recyclerPosts = root.findViewById(R.id.recyclerposts)
        recyclerPosts.apply {
            layoutManager = GridLayoutManager(activity,2)
            adapter = mAdapter
        }
//        })
        return root
    }



    override fun onStart() {
        super.onStart()
        mAdapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        if (mAdapter != null) {
            mAdapter!!.stopListening()
        }
    }
}