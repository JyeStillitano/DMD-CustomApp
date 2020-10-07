package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.controller.home.HomeViewModel
import com.example.myapplication.database.Game
import com.example.myapplication.database.Platform
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NewGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)

        val name = findViewById<EditText>(R.id.name)
        val description = findViewById<EditText>(R.id.description)
        val completion = findViewById<EditText>(R.id.completion)
        val platform = findViewById<Spinner>(R.id.platform)

        val save = findViewById<FloatingActionButton>(R.id.saveFab)
        save.setOnClickListener {
            if (checkInput()) {
                // Get fields from EditText inputs.
                val newGame = Game(
                    name.text.toString(),
                    Platform.valueOf(platform.selectedItem.toString()),
                    description.text.toString(),
                    completion.text.toString().toFloat()
                )
                // Insert newGame via HomeViewModel to access DAO
                ViewModelProvider(this).get(HomeViewModel::class.java).insert(newGame)
                Toast.makeText(applicationContext, newGame.name + " added!", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    // Check inputs aren't empty or passing invalid data.
    private fun checkInput(): Boolean {
        val name = findViewById<EditText>(R.id.name)
        val description = findViewById<EditText>(R.id.description)
        val completion = findViewById<EditText>(R.id.completion)

        var result = true

        if (name.text.isEmpty()) {
            name.error = "Please fill in the name."
            result = false
        }
        if (description.text.isEmpty()) {
            description.error = "Please fill in the description."
            result = false
        }
        if (completion.text.isNullOrEmpty()) {
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