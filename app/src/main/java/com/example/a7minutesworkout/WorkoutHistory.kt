package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.a7minutesworkout.adapter.HistoryAdapter
import com.example.a7minutesworkout.databinding.ActivityWorkoutHistoryBinding
import kotlinx.android.synthetic.main.activity_workout_history.*
import kotlinx.coroutines.launch

class WorkoutHistory : AppCompatActivity() {
    private var binding : ActivityWorkoutHistoryBinding ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.light_grey)

        setSupportActionBar(binding?.historyToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding?.historyToolbar?.setNavigationOnClickListener{
            onBackPressed()
        }

         val dao = (application as WorkoutApp).db.historyDao()
        getAllCompletedDates(dao)

    }

    private fun getAllCompletedDates(historyDao:HistoryDao){

        lifecycleScope.launch{
            historyDao.fetchAllDates().collect(){allCompletedList ->
                if (allCompletedList.isNotEmpty()){
                    tvExerciseCompleted.visibility = View.VISIBLE
                    historyRecyclerView.visibility = View.VISIBLE
                    tvNoRecords.visibility = View.GONE

                    val dates = ArrayList<String>()
                    for (date in allCompletedList){
                        dates.add(date.date)
                    }

                    val historyAdapter = HistoryAdapter(dates)
                    historyRecyclerView.adapter = historyAdapter


                }else{
                    tvExerciseCompleted.visibility = View.GONE
                    historyRecyclerView.visibility = View.GONE
                    tvNoRecords.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}