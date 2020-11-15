package com.example.myapplication.controller.completed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.GameAdapter
import com.example.myapplication.R

class CompletedFragment : Fragment() {

    private lateinit var completedViewModel: CompletedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Log.i("GalleryFragment", "onCreateView entered")

        completedViewModel =
            ViewModelProvider(this).get(CompletedViewModel::class.java)
        val view: View = inflater.inflate(R.layout.fragment_completed, container, false)

        // Recycler View & Data
        val list = view.findViewById<RecyclerView>(R.id.games)
        // Layout Manager
        val manager = LinearLayoutManager(context)
        list.layoutManager = manager
        // Game Adapter
        val adapter = GameAdapter(inflater)
        list.adapter = adapter

        completedViewModel.games.observe(viewLifecycleOwner, Observer { games ->
            games?.let { adapter.setGames(it) }
        })

        return view
    }
}