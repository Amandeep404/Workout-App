package com.example.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_exercise_finish_page.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ExerciseFinishPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_finish_page)

        returnButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val dao = (application as WorkoutApp).db.historyDao()
        addDateToDatabase(dao)
    }

    private fun addDateToDatabase(historyDao:HistoryDao){

        val c = Calendar.getInstance()
        val dateTime= c.time

        val sdf = SimpleDateFormat("dd MM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)

        lifecycleScope.launch{
                historyDao.insert(HistoryEntity(date))
        }
    }
}