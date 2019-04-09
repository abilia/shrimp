package com.abilia.shrimp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abilia.shrimp.MainActivity
import com.abilia.shrimp.R
import com.abilia.shrimp.adapter.RvAdapter
import com.abilia.shrimp.service.GreetingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BusinessCardFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        renderCards(container)
        return inflater.inflate(R.layout.fragment_business_card, container, false)
    }

    fun renderCards(container: ViewGroup?) {
        GlobalScope.launch(Dispatchers.Main) {

            val recyclerView: RecyclerView? = container?.findViewById(R.id.recyclerView)

            if (recyclerView != null) {
                val persons = withContext(Dispatchers.IO) {
                    (activity as MainActivity).greetingService.getAllPersonData()
                }
                val nonStudentsFromGothenburg = persons.filter { p -> p.occupation.occupation != "Student" && p.company.city == "Gothenburg"  }
                recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                recyclerView.adapter = RvAdapter(nonStudentsFromGothenburg)
            }
        }
    }
}