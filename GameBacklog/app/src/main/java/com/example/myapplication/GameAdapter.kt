package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GameAdapter(private val data: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_row, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
        return 4
    }

    override fun onBindViewHolder(holder: GameAdapter.ViewHolder, position: Int) {
        val game = data[position]
        holder.bind(game)
    }

    inner class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        private val name : TextView = v.findViewById(R.id.name)
        private val completion : TextView = v.findViewById(R.id.completion)

        fun bind(game: Game) {
            name.text = game.name
            completion.text = game.completion.toString()
        }
    }

}