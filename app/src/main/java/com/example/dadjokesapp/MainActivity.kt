package com.example.dadjokesapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "https://icanhazdadjoke.com/"
        val textView = findViewById<TextView>(R.id.textView)
        val button: Button = findViewById(R.id.button)
        val queue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(Request.Method.GET, url,
        Response.Listener<String> { response ->
            val fronttrim = response.substringAfter("<p class=\"subtitle\">")
            val joke = fronttrim.substring(0, fronttrim.indexOf("<"))
            textView.text = joke
        },
        Response.ErrorListener {
            textView.text = "There was an error."
        })

        button.setOnClickListener { queue.add(stringRequest) }
    }
}