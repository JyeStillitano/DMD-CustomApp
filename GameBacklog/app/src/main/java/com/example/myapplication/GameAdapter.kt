package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.database.Game

// For binding the Game entity object with a layout_row in the recycler view.
class GameAdapter internal constructor(layoutInflater: LayoutInflater) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    private var games = emptyList<Game>()
    private val inflater: LayoutInflater = layoutInflater

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.name)
        val completionView: TextView = view.findViewById(R.id.completion)
        val completionProgress: ProgressBar = view.findViewById(R.id.completionBar)

        init {
            // Start activity InformationActivity onClick - for each Game
            view.setOnClickListener {
                val position: Int = adapterPosition
                val intent = Intent(view.context, InformationActivity::class.java)
                intent.putExtra("EXPAND_GAME", games[position])
                view.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.ViewHolder {
        val view = inflater.inflate(R.layout.layout_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameAdapter.ViewHolder, position: Int) {
        val game = games[position]
        holder.nameView.text = game.name
        holder.completionProgress.progress = game.completion.toInt()
        val tempPercent = game.completion.toString() + "%"
        holder.completionView.text = tempPercent
    }

    override fun getItemCount() = games.size

    internal fun setGames(games: List<Game>) {
        this.games = games
        notifyDataSetChanged()
    }
}
