package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bmi_calculator.*
import kotlin.math.roundToInt

class BmiCalculator : AppCompatActivity() {

    private var BMI: Double? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculator)


        setSupportActionBar(bmiToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bmiToolbar.setNavigationOnClickListener{
            onBackPressed()
        }

        calculatorButton.setOnClickListener{
           if (validityCheck()){
               calBMI()
           }else{
               Toast.makeText(this, "Enter credentials", Toast.LENGTH_LONG).show()
           }
            tvBmiResult.text = "$BMI"
            report()
        }

    }

    private fun calBMI(){
        val et_weight : Float = etWeight.text.toString().toFloat()
        val et_height : Float = etHeight.text.toString().toFloat()/100

        BMI = (et_weight/(et_height * et_height)).roundToInt().toDouble()


    }
    private fun report(){
        if(BMI!!<18.50){
            tvHeightResult.text = "Under Weight"
            tvBmiRecommendation.text ="Oops!! You should gain weight"
        }else if (BMI!!>=18.50 && BMI!!<25){
            tvHeightResult.text = "Normal"
            tvBmiRecommendation.text ="You are fit."
        }else{
            tvHeightResult.text = "Over Weight"
            tvBmiRecommendation.text ="You need to do a lot of workout!"
        }
    }

    private fun validityCheck(): Boolean{
        var isValid = true
        if (etWeight.text.toString().isEmpty()){
            isValid = false
        }else if (etHeight.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }
}