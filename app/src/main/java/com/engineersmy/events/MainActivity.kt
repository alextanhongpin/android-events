package com.engineersmy.events

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create a counter
        var i = 0

        // Set the textView once the view initialized
//        val textView: TextView = findViewById(R.id.textView) as TextView
        textView.text = "this is awesome!"

        // Target the button
//        val button: Button = findViewById(R.id.button) as Button
        button.setOnClickListener {
            i++
            textView.text = i.toString()
            Toast.makeText(
                    this@MainActivity,
                    "You clicked me.",
                    Toast.LENGTH_SHORT
            ).show()
        }

//        val buttonList: Button = findViewById(R.id.button_list) as Button
        button_list.text = "Go to list"
        button_list.setOnClickListener {
            Log.i("route", "going to list")
            // ::class.java is an alternative of .class in kotlin
            val newIntent = Intent(this, EventsActivity::class.java)
            startActivity(newIntent)
        }
    }

}
