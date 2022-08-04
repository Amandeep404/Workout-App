package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.viewbinding.ViewBinding
import com.example.a7minutesworkout.databinding.ActivityExcerciseBinding
import kotlinx.android.synthetic.main.activity_excercise.*
import kotlinx.android.synthetic.main.activity_excercise.view.*

class ExcerciseActivity : AppCompatActivity() {

    private  var binding : ActivityExcerciseBinding? = null
    private var resetTimer:CountDownTimer?= null
    var restProgress = 0
    private var excercise1Timer:CountDownTimer?= null
    var excercise1Progress = 0
    private var exerciseTimerDuration:Long = 30

    private var excerciseList : ArrayList<ExcerciseModels>? = null
    private var currentExcercise = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExcerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.excerciseToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding?.excerciseToolbar?.setNavigationOnClickListener{
            onBackPressed()
        }

          setupRestView()

        excerciseList = Constants.defaultExcerciseList()


    }

    private fun setupRestView(){
        flprogressButton.visibility = View.VISIBLE
        flprogressExcercise1.visibility= View.INVISIBLE
        binding?.timerHeading?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.excerciseImages?.visibility = View.INVISIBLE

        if (resetTimer!= null){
            resetTimer?.cancel()
            restProgress=0
        }
        startTimer()
    }


    private fun startTimer(){
        binding?.restProgressBar?.progress= restProgress


        resetTimer = object :CountDownTimer(10000, 1000){
            override fun onTick(millisUntilFinished: Long) {
             restProgress++
                binding?.restProgressBar?.progress = 10- restProgress
                tv_timer.text = (10 - restProgress).toString()

            }
            override fun onFinish() {
                currentExcercise++
                setupExcerciseView()

            }

        }.start()

        }
    private fun excercise1Countdown(){
        binding?.excerciseProgressBar?.progress= excercise1Progress

        excercise1Timer = object :CountDownTimer(30000, 1000){
            override fun onTick(p0: Long){
                excercise1Progress++
                binding?.excerciseProgressBar?.progress = 30- excercise1Progress
                tv_timer2.text= (30 - excercise1Progress).toString()
            }

            override fun onFinish() {
                if (currentExcercise< excerciseList!!.size -1){
                    setupRestView()
                }else{
                    Toast.makeText(this@ExcerciseActivity, "done", Toast.LENGTH_LONG).show()
                }
            }
        }.start()
    }
    private fun setupExcerciseView(){

        binding?.flprogressButton?.visibility = View.INVISIBLE
        binding?.flprogressExcercise1?.visibility= View.VISIBLE
        binding?.timerHeading?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.excerciseImages?.visibility = View.VISIBLE


        if (excercise1Timer!=null){
            excercise1Timer?.cancel()
            excercise1Progress = 0
        }

        binding?.excerciseImages?.setImageResource(excerciseList!![currentExcercise].getImage())
        binding?.tvExerciseName?.text = excerciseList!![currentExcercise].getName()

        excercise1Countdown()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (resetTimer!=null){
          resetTimer?.cancel()
            restProgress = 0
        }
        if (excercise1Timer!=null){
            excercise1Timer?.cancel()
            excercise1Progress = 0
        }


        binding = null
    }
    }
