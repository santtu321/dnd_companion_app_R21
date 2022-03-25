package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import org.intellij.lang.annotations.JdkConstants
import kotlin.math.ceil
import kotlin.random.Random

class sumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sum)

        val diceAmount: EditText = findViewById(R.id.eText3)
        val diceSides: EditText = findViewById(R.id.eText2)
        val rollButton2: Button = findViewById(R.id.bRoll2)
        val sum: TextView = findViewById(R.id.tViewSum)



        fun createWidgets(rows: Int, dices: Int){

            var x = 1
            var k = 1
            var total = 0


            val myRandomNumbers = List (dices){Random.nextInt(1,diceSides.text.toString().toInt())}         //tehdään ensimmäiseksi randomit numerot
                                                                                                                 // listan koko ja numerot määrääntyy siitä, mitä käyttäjä antoi parametreiksi
            var dices = dices
            val parent: LinearLayout = findViewById(R.id.pLayout)                               //tehdään olio meidän linearlayoutista, johon pistetään kaikki tulevat layoutit
            parent.removeAllViews()                                                             //ja jos siinä oli jo jotakin lapsia, niin tuhotaan ne ennenkuin aletaan tekemään lisää.

            while (rows >= x){
                var i = 0

                val lL = LinearLayout(this)
                lL.layoutParams = LinearLayout.LayoutParams(                                    // eli tässä luodaan linearlayoutteja niin paljon kuin rivejä haluttiin.
                    LinearLayout.LayoutParams.MATCH_PARENT,                                     // ja annetaan niille parametrit tässä
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                lL.id = x
                lL.orientation = LinearLayout.HORIZONTAL
                lL.gravity = Gravity.CENTER_HORIZONTAL

                parent.addView(lL)                                                              // tässä me liitämme lL layoutin meidän parent layouttiin.

                while (i < 10 && dices >= k) {                                                  // jonka jälkeen pistämmä lL layouttiin 10 textview oliota, jotka toimivat noppina.
                    val tView = TextView(this)

                    tView.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    tView.text = "${myRandomNumbers[k-1]}"
                    tView.textSize = 30.0F
                    tView.setPadding(10,0,10,0)                         // taas parametrejä text oliolle.
                    tView.gravity = Gravity.CENTER
                    tView.setTextColor(0xff000000.toInt())
                    tView.setTypeface(null, Typeface.BOLD)

                    lL.addView(tView)                                                       // ja liitetään text olio lL layouttiin.
                    i++
                    k++
                }
                x++                                                                         // tämä rumba kestää niinkauan, kun rivejä on ja jokaiseen riviin teemme 10 text oliota.
            }                                                                               // samalla pidetään lukua nopista mikä on k variable.
            if(dices >= k) {                                                                 // ja jos noppia jäi vielä niin tässä vielä tehdään yksi rivi jäljellä oleville nopille.
                                                                                            // koodi on sama kuin ylhäällä.
                    var i = 0

                    val lL = LinearLayout(this)
                    lL.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    lL.id = x
                    lL.orientation = LinearLayout.HORIZONTAL
                    lL.gravity = Gravity.CENTER

                    parent.addView(lL)

                    while (i < 10 && dices >= k) {
                        val tView = TextView(this)

                        tView.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                        )
                        tView.text = "${myRandomNumbers[k-1]}"
                        tView.textSize = 30.0F
                        tView.setPadding(10,0,10,0)
                        tView.gravity = Gravity.CENTER
                        tView.setTextColor(0xff000000.toInt())
                        tView.setTypeface(null, Typeface.BOLD)

                        lL.addView(tView)
                        i++
                        k++
                    }
                }

            var z = 0                       // tässä vielä summataan kaikki randomit numerot yhteen
                                            // ja laitetaan tulos sum textviewiin.
            while (dices > z)
            {
             total = total + myRandomNumbers[z]
                z++
            }

            sum.text = total.toString()

        }




        rollButton2.setOnClickListener{

            var rows = (diceAmount.text.toString().toInt()/10).toDouble()                // kutsutaan createwidgets funktiota ja annetaan sille parametreiksi kuinka monta
            rows = ceil(rows)                                                            // riviä sen pitää tehdä eli noppamäärä/10 ja kuin monta noppaa halutaan.

            var dices = diceAmount.text.toString().toInt()
            createWidgets(rows.toInt(), dices)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.popup_menu,menu)
        return true
    }                                                                                   //tämä kaikkiin activityihin tekee kolmepisteen appbaariin ja sen jälkeen se näyttää popup menun
                                                                                        // kun siitä klikataan. alempana on myös on funktio kun popupmenun itemeistä klikataan

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_sum->{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_rule->{
                val intent = Intent(this, RuleActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_main->{
                val intent = Intent(this, sumActivity::class.java)
                startActivity(intent)
                true
            }
            else->super.onOptionsItemSelected(item)
        }
    }
}