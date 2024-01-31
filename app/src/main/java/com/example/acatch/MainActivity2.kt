package com.example.acatch

import android.app.Application
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.renderscript.RenderScript.ContextType
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.acatch.databinding.ActivityMain2Binding
import com.example.acatch.databinding.ActivityMainBinding

private lateinit var binding: ActivityMain2Binding
lateinit var sharedPref: SharedPreferences
var score =0;
var highscore =0;

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPref = this.getSharedPreferences("package com.example.acatch", MODE_PRIVATE)
        highscore= sharedPref.getInt("HighScore",-1)



        object: CountDownTimer(60000, 1000){
            override fun onTick(p0: Long) {
                binding.textView3.text="Süre : " +(p0/1000).toString()
            }
            override fun onFinish() {
                    val alert = AlertDialog.Builder(this@MainActivity2)
                if(score > highscore) {
                    sharedPref.edit().putInt("HighScore", score).apply()
                    highscore= sharedPref.getInt("HighScore",-1)
                }
                    alert.setTitle("Oyun Sonu Skorunuz: ${score}")

                    alert.setMessage("Tekrar Oynamak İstermisiniz ?  \n\n En Yüksek Skor : ${highscore}"  )

                    alert.setPositiveButton("Evet") {dialog, which ->
                        //OnClick Listener
                        score = 0
                        val intent = Intent(applicationContext,MainActivity::class.java)
                        startActivity(intent)
                        finish()


                    }
                    alert.setNegativeButton("Hayır") {dialog, which ->
                        finishAffinity()
                    }

                    alert.show()

            }
        }.start()


        object: CountDownTimer(60000, 450){
            override fun onTick(p0: Long) {

                binding.imageView4.translationX = random(700).toFloat()
                binding.imageView4.translationY = random(1100).toFloat()

            }
            override fun onFinish() {

            }
        }.start()

        binding.imageView4.setOnClickListener {
            score++
            binding.textView2.text="Skor: " + score.toString()


        }

    }

    private fun random( number1: Int):Int{
        return (1..number1).random();
    }


}
