package com.example.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.a7minutesworkout.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.different_orange)

        binding?.frameLayoutStartButton?.setOnClickListener{
            val intent = Intent(this, ExcerciseActivity::class.java)
            startActivity(intent)

        }

        binding?.flBmi?.setOnClickListener{
            val intent = Intent(this, BmiCalculator::class.java)
            startActivity(intent)
        }

        binding?.flHistory?.setOnClickListener{
            val intent = Intent(this, WorkoutHistory::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}