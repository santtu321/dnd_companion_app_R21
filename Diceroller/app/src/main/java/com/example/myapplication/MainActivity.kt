package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlin.random.Random
import android.content.Intent


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val radioGroup: RadioGroup = findViewById(R.id.rGroup1)
    val rollButton: Button = findViewById(R.id.bRoll)
    val textViewResult: TextView = findViewById(R.id.tView2)
    val diceTextView1: TextView = findViewById(R.id.tViewDice1)
    val diceTextView2: TextView = findViewById(R.id.tViewDice2)
    val proficiencyNumber: EditText = findViewById(R.id.eText1)
    val intentButton1: Button = findViewById(R.id.bIntent)
    var proficiency = 0

        fun checkAdvantage(): Int {                                                       // tämä funktio katsoo mikä radiobuttoneista on selectattu ja palauttaa sen id:n.
            return radioGroup!!.checkedRadioButtonId
        }

    fun rollTheDice(advantage: String) {
        when(advantage){

            "advantage" -> {
                val myRandomNumbers = List (2){Random.nextInt(1,21)}                //Katsotaan mikä advantage on ja edetään siihen switch-statella
                val mySortedNumbers = myRandomNumbers.sortedDescending()                           //Aina kun tätä funktiota kutsutaan se rollaa 2 numeroa ja sorttaa ne laskevasti tai nousevasti
                diceTextView1.text = myRandomNumbers[0].toString()                                 // riippuen advantagesta. Jos advantagea ei ole se antaa resultiksi ensimmäisen numeron.
                diceTextView2.text = myRandomNumbers[1].toString()
                textViewResult.text = (mySortedNumbers[0] + proficiency).toString()
            }
            "no advantage" -> {
                val myRandomNumbers = List (2){Random.nextInt(1,21)}
                diceTextView1.text = myRandomNumbers[0].toString()
                diceTextView2.text = myRandomNumbers[1].toString()
                textViewResult.text = (myRandomNumbers[0] + proficiency).toString()
            }
            "Disadvantage" -> {
                val myRandomNumbers = List (2){Random.nextInt(1,21)}
                val mySortedNumbers = myRandomNumbers.sorted()
                diceTextView1.text = myRandomNumbers[0].toString()
                diceTextView2.text = myRandomNumbers[1].toString()
                textViewResult.text = (mySortedNumbers[0] + proficiency).toString()
            }
            else -> {
                error("Error")
            }
        }
    }

        rollButton.setOnClickListener{
            val advantage = checkAdvantage()                                //tässä kun painetaan roll buttonia otamme radiobuttonin textin
                                                                            // ja pistämme sen rolleTheDice funktiolle parametriksi
            val radioButton: RadioButton = findViewById(advantage)
            val sAdvantage = radioButton.text

            proficiency = proficiencyNumber.text.toString().toInt()         // katsomme samalla aina kun nappia painetaan, mikä numero on proficiencyssä ja tallennetaan se
            rollTheDice(sAdvantage.toString())
        }
        intentButton1.setOnClickListener{
            val intent = Intent(this, sumActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }
}


