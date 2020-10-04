package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.database.Game

class GameAdapter internal constructor(layoutInflater: LayoutInflater) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    private var games = emptyList<Game>()
    private val inflater: LayoutInflater = layoutInflater

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.name)
        val completionView: TextView = view.findViewById(R.id.completion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.ViewHolder {
        val view = inflater.inflate(R.layout.layout_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameAdapter.ViewHolder, position: Int) {
        val game = games[position]
        holder.nameView.text = game.name
        holder.completionView.text = inflater.context.getString(R.string.percent, game.completion.toString())
    }

    override fun getItemCount() = games.size

    internal fun setGames(games: List<Game>) {
        this.games = games
        notifyDataSetChanged()
    }
}