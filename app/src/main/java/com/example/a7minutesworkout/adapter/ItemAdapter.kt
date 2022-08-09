package com.example.a7minutesworkout.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.ExcerciseModels
import com.example.a7minutesworkout.R
import com.example.a7minutesworkout.databinding.ActivityExcerciseBinding
import com.example.a7minutesworkout.databinding.ExerciseNumberStatusBinding

class ItemAdapter(val exerciseId :ArrayList<ExcerciseModels>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textview: TextView = view.findViewById(R.id.tv_exercise_number)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.exercise_number_status, parent, false)
        return  ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item :ExcerciseModels = exerciseId[position]
        holder.textview.text = item.getId().toString()

        when{
            item.getIsCompleted() ->{
                holder.textview.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.circular_grey_background)
                holder.textview.setTextColor(Color.parseColor("#FF000000" ))
            }
            item.getIsSelected() ->{
                holder.textview.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.circular_green_background)
                holder.textview.setTextColor(Color.parseColor("#FFFFFFFF" ))
            }
            else ->{
                holder.textview.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.circular_white_background)
                holder.textview.setTextColor(Color.parseColor("#FF000000" ))
            }
        }
    }

    override fun getItemCount() = exerciseId.size

}