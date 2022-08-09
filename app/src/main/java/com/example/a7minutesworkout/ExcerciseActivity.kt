package com.example.a7minutesworkout

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.a7minutesworkout.adapter.ItemAdapter
import com.example.a7minutesworkout.databinding.ActivityExcerciseBinding
import kotlinx.android.synthetic.main.activity_excercise.*
import kotlinx.android.synthetic.main.activity_excercise.view.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class ExcerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private  var binding : ActivityExcerciseBinding? = null
    private var resetTimer:CountDownTimer?= null
    var restProgress = 0
    private var excercise1Timer:CountDownTimer?= null
    var excercise1Progress = 0
    private var exerciseTimerDuration:Long = 30

    private var excerciseList : ArrayList<ExcerciseModels>? = null
    private var currentExcercise = -1

    private var tts :TextToSpeech? = null

    private var player : MediaPlayer? = null

    private var exerciseAdapter: ItemAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExcerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.excerciseToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tts = TextToSpeech(this, this)

        binding?.excerciseToolbar?.setNavigationOnClickListener{
            onBackPressed()
        }
         excerciseList = Constants.defaultExcerciseList()

          setupRestView()
        exerciseNumberRecyclerView()

    }

    private fun exerciseNumberRecyclerView(){
      recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        exerciseAdapter = ItemAdapter(excerciseList!!)
        recycler_view.adapter = exerciseAdapter
    }

    private fun setupRestView(){

        try{
            val soundUri = Uri.parse("android.resource://com.example.a7minutesworkout" + R.raw.app_src_main_res_raw_press_start)
            player =  MediaPlayer.create(applicationContext, soundUri)
            player?.isLooping = false
            player?.start()
        }catch (e:Exception){
            e.printStackTrace()
        }

        flprogressButton.visibility = View.VISIBLE
        flprogressExcercise1.visibility= View.INVISIBLE
        binding?.timerHeading?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.excerciseImages?.visibility = View.INVISIBLE
        binding?.upcomingExerciseName?.visibility = View.VISIBLE
        binding?.upcomingExerciseTitle?.visibility = View.VISIBLE

        binding?.upcomingExerciseName?.text= excerciseList!![currentExcercise+1].getName()

        val totalUpcoming = "${upcomingExerciseTitle.text } ${upcomingExerciseName.text}"

        speakOut(totalUpcoming)

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

                excerciseList!![currentExcercise].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()

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

                excerciseList!![currentExcercise].setIsSelected(false)
                excerciseList!![currentExcercise].setIsCompleted(true)
                exerciseAdapter!!.notifyDataSetChanged()

                if (currentExcercise< excerciseList!!.size -1){
                    setupRestView()
                }else{
                   val intent = Intent(this@ExcerciseActivity, ExerciseFinishPage::class.java)
                    startActivity(intent)
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
        binding?.upcomingExerciseTitle?.visibility = View.INVISIBLE
        binding?.upcomingExerciseName?.visibility = View.INVISIBLE


        if (excercise1Timer!=null){
            excercise1Timer?.cancel()
            excercise1Progress = 0
        }

        binding?.excerciseImages?.setImageResource(excerciseList!![currentExcercise].getImage())
        binding?.tvExerciseName?.text = excerciseList!![currentExcercise].getName()

        speakOut(excerciseList!![currentExcercise].getName())

        excercise1Countdown()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            val result = tts?.setLanguage(Locale.ENGLISH)

            if (result == TextToSpeech.LANG_NOT_SUPPORTED || result== TextToSpeech.LANG_MISSING_DATA){
                Toast.makeText(this, "language not supported or language missing", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this, "Text to speech failed", Toast.LENGTH_LONG).show()
        }
    }

    private fun speakOut(text:String){
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {

        if (resetTimer!=null){
          resetTimer?.cancel()
            restProgress = 0
        }
        if (excercise1Timer!=null){
            excercise1Timer?.cancel()
            excercise1Progress = 0
        }
        if (tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }

        if (player!=null){
            player!!.stop()
        }
        super.onDestroy()
        binding = null
    }


}
