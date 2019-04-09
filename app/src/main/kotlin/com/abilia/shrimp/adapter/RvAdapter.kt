package com.abilia.shrimp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abilia.shrimp.R
import com.abilia.shrimp.models.ExtendedPerson

class RvAdapter(val persons: List<ExtendedPerson>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.business_card_item, parent,false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int = persons.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = persons[position]
        holder.name?.text = p.person.fullName
        holder.count?.text = "${p.occupation.occupation} at ${p.company.name}, ${p.company.city}"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView?>(R.id.cardName)
        val count = itemView.findViewById<TextView?>(R.id.cardOccupation)
    }
}