package com.example.a7minutesworkout.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.HistoryEntity
import com.example.a7minutesworkout.R

class HistoryAdapter(private val HistoryItems: ArrayList<String>):RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val llHistory: LinearLayout = view.findViewById(R.id.llHistoryView)
        val historyNum: TextView = view.findViewById(R.id.tvHistoryNumber)
        val historyDate: TextView = view.findViewById(R.id.tvHistoryDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return HistoryViewHolder(adapterLayout)
    }


    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {

        val date :String = HistoryItems[position]
        val HistoryItem = HistoryItems[position]

        holder.historyNum.text = (position +10).toString()
        holder.historyDate.text = date

        if (position%2 == 0){
            holder.llHistory.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.light_grey))
        }else{
            holder.llHistory.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
        }
    }

    override fun getItemCount() = HistoryItems.size
}