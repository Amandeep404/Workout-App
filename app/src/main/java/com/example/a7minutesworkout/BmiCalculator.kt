package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_bmi_calculator.*
import kotlin.math.roundToInt

class BmiCalculator : AppCompatActivity() {

    companion object{
        private const val METRIC_UNITS_VIEW = "METRIC_UNITS_VIEW"
        private const val US_UNITS_VIEW = "US_UNITS_VIEW"
    }

    private var BMI: Double? =null

    private var currentVisibleView: String = METRIC_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculator)

        window.statusBarColor = ContextCompat.getColor(this, R.color.navy_blue)

        setSupportActionBar(bmiToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bmiToolbar.setNavigationOnClickListener{
            onBackPressed()
        }

        makeMetricUnitsVisible()
        radioUsUnits.setOnClickListener{
            etWeightHint.setHint("Weight(in Pounds)")
            makeUsUnitsVisible()

            calculatorButton.setOnClickListener{
                if (usUnitsValidityCheck()){
                    callBMIforUSUnits()
                    report()
                    tvBmiResult.text = "$BMI"
                    resultCard.visibility = View.VISIBLE
                }else{
                    Toast.makeText(this, "Enter correct Details", Toast.LENGTH_LONG).show()
                }

            }

        }
        radioMetricUnits.setOnClickListener{
            etWeightHint.setHint("Weight(in kg)")
            makeMetricUnitsVisible()

            calculatorButton.setOnClickListener{
                if (validityCheck()){
                    calBMI()
                    report()
                    tvBmiResult.text = "$BMI"
                    resultCard.visibility = View.VISIBLE

                }else{
                    Toast.makeText(this, "Enter correct Details", Toast.LENGTH_LONG).show()
                }


            }
        }

    }

    private fun calBMI(){
        val et_weight : Float = etWeight.text.toString().toFloat()
        val et_height : Float = etHeightMetricUnits.text.toString().toFloat()/100

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
            tvBmiRecommendation.text ="You need to workout regularly!"
        }
    }

    private fun validityCheck(): Boolean{
        var isValid = true
        if (etWeight.text.toString().isEmpty()){
            isValid = false
        }else if (etHeightMetricUnits.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }

    private fun usUnitsValidityCheck(): Boolean{
        var isValid = true
        if (etWeight.text.toString().isEmpty()){
            isValid = false
        }else if (etHeightUsUnitsInFeet.text.toString().isEmpty() || etHeightUsUnitsInInches.text.toString().isEmpty() ){
            isValid = false
        }
        return isValid
    }

    private fun callBMIforUSUnits(){
        val et_weight_in_pounds : Float = etWeight.text.toString().toFloat()
        val et_heightInFeet : Float = etHeightUsUnitsInFeet.text.toString().toFloat()
        val et_heightInInches : Float = etHeightUsUnitsInInches.text.toString().toFloat()/100

        val et_weight: Float = (et_weight_in_pounds*2.20462260).toFloat()
        val et_height : Float = (et_heightInFeet + et_heightInInches)*0.3048.toString().toFloat()

        BMI = (et_weight/(et_height * et_height)).roundToInt().toDouble()


    }

    private fun makeMetricUnitsVisible(){
        currentVisibleView = METRIC_UNITS_VIEW

        editTextMetricHeight.visibility = View.VISIBLE
        editTextUsHeightInFeet.visibility = View.GONE
        editTextUsHeightInInches.visibility = View.GONE

        etHeightMetricUnits.text!!.clear()
        etWeight.text!!.clear()

        resultCard.visibility = View.GONE

        calculatorButton.setOnClickListener{
            if (validityCheck()){
                calBMI()
                report()
                tvBmiResult.text = "$BMI"
                resultCard.visibility = View.VISIBLE

            }else{
                Toast.makeText(this, "Enter correct Details", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun makeUsUnitsVisible(){
        currentVisibleView = US_UNITS_VIEW

        editTextMetricHeight.visibility = View.GONE
        editTextUsHeightInFeet.visibility = View.VISIBLE
        editTextUsHeightInInches.visibility = View.VISIBLE

        etWeight.text!!.clear()
        etHeightUsUnitsInFeet.text!!.clear()
        etHeightUsUnitsInInches.text!!.clear()

        resultCard.visibility = View.GONE

    }
}