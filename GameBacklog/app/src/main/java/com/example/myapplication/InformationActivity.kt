package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.controller.playing.PlayingViewModel
import com.example.myapplication.database.Game
import com.example.myapplication.database.Platform
import com.google.android.material.floatingactionbutton.FloatingActionButton

class InformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        val game = intent.getParcelableExtra<Game>("EXPAND_GAME")
        val name = findViewById<TextView>(R.id.name)
        val description = findViewById<EditText>(R.id.description)
        val platform = findViewById<Spinner>(R.id.platform)
        val completion = findViewById<EditText>(R.id.completion)

        // If Game is successfully parsed fill in data. Else close activity.
        if (game != null) {
            name.text = game.name
            description.setText(game.desc)
            platform.setSelection(game.platform.ordinal)
            completion.setText(game.completion.toString())
        } else {
            finish()
        }

        // If data is empty, fills in placeholder to allow safe delete in DB.
        // Creates toast to notify user then closes activity.
        findViewById<FloatingActionButton>(R.id.deleteFab).setOnClickListener {
            if (!checkInput()) {
                description.setText("None provided.")
                completion.setText(0.toString())
            }
            val deleteGame = Game(name.text.toString(),
                Platform.valueOf(platform.selectedItem.toString()),
                description.text.toString(),
                completion.text.toString().toFloat())
            ViewModelProvider(this).get(PlayingViewModel::class.java).delete(deleteGame)
            Toast.makeText(applicationContext, deleteGame.name + " deleted!", Toast.LENGTH_LONG).show()
            finish()
        }

        // Checks data is valid, then updates game in DB.
        // Toast to notify user, then closes activity.
        findViewById<FloatingActionButton>(R.id.saveFab).setOnClickListener {
            if (checkInput()) {
                val updatedGame = Game(name.text.toString(), Platform.valueOf(platform.selectedItem.toString()), description.text.toString(), completion.text.toString().toFloat())
                ViewModelProvider(this).get(PlayingViewModel::class.java).update(updatedGame)
                Toast.makeText(applicationContext, "Game Updated!", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    // Checks edit text fields aren't left empty.
    // Checks completion percentage isn't greater than 100.0%,
    // already can't input below 0.0 % due to edit text's input type.
    private fun checkInput(): Boolean {
        var result = true
        val description = findViewById<EditText>(R.id.description)
        val completion = findViewById<EditText>(R.id.completion)

        if (description.text.isEmpty()) {
            description.error = "Please fill in the description."
            result = false
        }
        if (completion.text.isNullOrEmpty()) {
            Log.i("Error", "Completion string empty.")
            completion.error = "Please fill in the completion percentage."
            result = false
        } else {
            if (completion.text.toString().toFloat() > 100.0 ) {
                completion.error = "Percentage cannot be greater than 100.0 %."
                result = false
            }
        }

        return result
    }
}