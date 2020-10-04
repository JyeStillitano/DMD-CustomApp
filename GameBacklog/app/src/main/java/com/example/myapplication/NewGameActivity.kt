package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
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
            val replyIntent = Intent()
            if (TextUtils.isEmpty(name.text) ||
                TextUtils.isEmpty(description.text) ||
                TextUtils.isEmpty(completion.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val newGame = Game(
                    name.text.toString(),
                    Platform.valueOf(platform.selectedItem.toString()),
                    description.text.toString(),
                    completion.text.toString().toFloat()
                )
                replyIntent.putExtra("NEW_GAME", newGame)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}