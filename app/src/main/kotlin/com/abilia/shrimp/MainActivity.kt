package com.abilia.shrimp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.abilia.shrimp.fragments.BusinessCardFragment
import com.abilia.shrimp.fragments.GreetingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.abilia.shrimp.service.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val greetingService = GreetingService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        findViewById<BottomNavigationView>(R.id.navigation).setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


        if (savedInstanceState == null) {
            changeFramgment(GreetingsFragment())
        }
    }

    val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_greeting -> {
                changeFramgment(GreetingsFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_businesscard -> {
                changeFramgment(BusinessCardFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun changeFramgment(framgment:Fragment) =
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, framgment)
                    .commit()
}