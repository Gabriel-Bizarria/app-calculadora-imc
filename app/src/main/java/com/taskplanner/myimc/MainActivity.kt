package com.taskplanner.myimc

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import com.taskplanner.myimc.databinding.ActivityMainBinding
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

         binding.calculateButton.setOnClickListener {
             if(binding.weightInput.text.isNullOrEmpty() ||
                     binding.heightInput.text.isNullOrEmpty()){

                 Toast.makeText(
                     applicationContext,
                     "Preencha os dois campos",
                     Toast.LENGTH_SHORT).show()
             } else {
                 val weightInput = binding.weightInput.text.toString().toFloat()
                 val heightInput = binding.heightInput.text.toString().toFloat()

                 calculateImc(weightInput, heightInput)
                 binding.resultCv.visibility = VISIBLE
             }
         }
    }


    fun calculateImc(weight: Float, height:Float){
        val resultImc = roundNumber(weight / (height * height))

        binding.indexResult.text = resultImc.toString()

        when(resultImc){
            in 0f..15.99f -> {
                binding.resultTxt.text = "Você está extremamente abaixo do peso!"
                binding.backgroundCv.setBackgroundColor(Color.parseColor("#F08080"))
            }
            in 16f..18.49f -> {
                binding.resultTxt.text = "Você está abaixo do peso!"
                binding.backgroundCv.setBackgroundColor(Color.parseColor("#FDFD66"))
            }
            in 18.50f..24.99f -> {
                binding.resultTxt.text = "Você está no peso ideal!"
                binding.backgroundCv.setBackgroundColor(Color.parseColor("#BEF3CA"))
            }
            in 25f..29.99f -> {
                binding.resultTxt.text = "Você está acima do peso ideal!"
                binding.backgroundCv.setBackgroundColor(Color.parseColor("#FDFD66"))
            }
            in 30f..34.99f -> {
                binding.resultTxt.text = "Você está na obesidade nível I!"
                binding.backgroundCv.setBackgroundColor(Color.parseColor("#F08080"))
            }
            in 35f..39.99f -> {
                binding.resultTxt.text = "Você está na obesidade nível II!"
                binding.backgroundCv.setBackgroundColor(Color.parseColor("#F08080"))
            }
            else -> {
                binding.resultTxt.text = "Você está na obesidade nível III!"
                binding.backgroundCv.setBackgroundColor(Color.parseColor("#F08080"))
            }
        }
    }

    fun roundNumber(number:Float): Float{
        val df = DecimalFormat("#,##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toFloat()
    }
}



