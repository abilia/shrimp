package com.abilia.shrimp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.abilia.shrimp.service.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val greetingService = GreetingService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        renderGreeting()


        findViewById<BottomNavigationView>(R.id.navigation).setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_greeting -> {
                renderGreeting()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_businesscard ->
                // TODO implement business cards
                return@OnNavigationItemSelectedListener true
        }
        false
    }

    fun renderGreeting() {
        GlobalScope.launch(Dispatchers.Main) {

            val greeting: TextView = findViewById(R.id.greeting)

            val person = withContext(Dispatchers.IO) {greetingService.getGreetingPerson() }

            greeting.setText("Hello says ${person.fullName}")

        }
    }
}