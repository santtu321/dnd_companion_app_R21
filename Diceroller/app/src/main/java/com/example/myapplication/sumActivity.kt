package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.math.ceil
import kotlin.random.Random

class sumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sum)

        val diceAmount: EditText = findViewById(R.id.eText3)
        val diceSides: EditText = findViewById(R.id.eText2)
        val rollButton2: Button = findViewById(R.id.bRoll2)

        var i = 1
        var x = 0

        var rows = 7.0//(diceAmount.text.toString().toInt()/7).toDouble()
        rows = ceil(rows)

        rollButton2.setOnClickListener{

            while (  i < rows ){

                when(i) {

                    1 -> {
                        while (x < 6) {
                            val lL: LinearLayout = findViewById(R.id.lLayout1)
                            val tView = TextView(this)
                            tView.layoutParams = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                            )
                            tView.text = "${diceSides.text}"
                            tView.textSize = 24.0F
                            lL.addView(tView)
                            x++
                        }
                    }
                    2 -> {
                        while (x < 6) {
                            val lL: LinearLayout = findViewById(R.id.lLayout2)
                            val tView = TextView(this)
                            tView.layoutParams = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                            )
                            tView.text = "${diceSides.text}"
                            tView.textSize = 24.0F
                            lL.addView(tView)
                            x++
                        }
                    }
                }
                i++

            }
        }
    }
}