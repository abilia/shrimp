package com.abilia.shrimp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.abilia.shrimp.MainActivity
import com.abilia.shrimp.R
import com.abilia.shrimp.service.GreetingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GreetingsFragment() : Fragment()  {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        renderGreeting(container)
        return inflater.inflate(R.layout.fragment_greeting, container, false)
    }

    fun renderGreeting(container: ViewGroup?) {
        GlobalScope.launch(Dispatchers.Main) {

            val greeting: TextView? = container?.findViewById(R.id.greeting)

            if (greeting != null) {
                val person = withContext(Dispatchers.IO) {
                    (activity as MainActivity).greetingService.getGreetingPerson()
                }

                greeting.text = "Hello says ${person.fullName}"
            }
        }
    }
}