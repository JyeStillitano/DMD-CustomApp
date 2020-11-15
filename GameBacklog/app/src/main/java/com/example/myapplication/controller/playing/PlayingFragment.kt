package com.example.myapplication.controller.playing

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

class PlayingFragment : Fragment() {

    private lateinit var playingViewModel: PlayingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        playingViewModel =
            ViewModelProvider(this).get(PlayingViewModel::class.java)
        val view : View = inflater.inflate(R.layout.fragment_playing, container, false)

        // Recycler View & Data
        val list = view.findViewById<RecyclerView>(R.id.games)
        // Layout Manager
        val manager = LinearLayoutManager(context)
        list.layoutManager = manager
        // Game Adapter
        val adapter = GameAdapter(inflater)
        list.adapter = adapter

        playingViewModel.games.observe(viewLifecycleOwner, Observer { games ->
            games?.let { adapter.setGames(it) }
        })
        return view
    }

}