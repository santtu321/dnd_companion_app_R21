package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlin.random.Random
import android.content.Intent
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.media.MediaPlayer


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
        var mp: MediaPlayer? = null

    var proficiency = 0

        fun checkAdvantage(): Int {
            if(radioGroup.checkedRadioButtonId != -1){
                return radioGroup.checkedRadioButtonId
            }
            else{
                return 2131231081                                                                   //tämä funktio palauttaattaa selectatun radiobuttonin id:n ja jos mitään ei ole valittuna
            }                                                                                       // eli .checkedRadioButtonId palauttaa -1 palautamme no advantage buttonin id:n
        }                                                                                           // .xml tiedostossa on jo yksi nappula valittuna, mutta tämä vain jos käyttäjä saa jollain tapaa rikottua ohjelman


    fun rollTheDice(advantage: String) {
        when(advantage){

            "advantage" -> {
                Thread(Runnable{                                                                    //tehdään threadi näistä rollauksista, jotta voidaan pyöritellä vähän aikaa numeroita
                                                                                                    // näytöllä
                    var i = 0

                    while(i < 20){
                        runOnUiThread({diceTextView1.text = (1..20).random().toString()})
                        runOnUiThread({diceTextView2.text = (1..20).random().toString()})               // tässä sitten tehdään looppi jossa pyöritellään numeroita 50 millisekunnin välein
                        Thread.sleep(50)                                                           // eli koko rumbassa kestää sekuntti.
                        i++
                    }
                    val myRandomNumbers = List (2){Random.nextInt(1,21)}                //Katsotaan mikä advantage on ja edetään siihen switch-statella
                    val mySortedNumbers = myRandomNumbers.sortedDescending()                            //Aina kun tätä funktiota kutsutaan se rollaa 2 numeroa ja sorttaa ne laskevasti tai nousevasti
                    runOnUiThread({diceTextView1.text = myRandomNumbers[0].toString()})                                 // riippuen advantagesta. Jos advantagea ei ole se antaa resultiksi ensimmäisen numeron.
                    runOnUiThread({diceTextView2.text = myRandomNumbers[1].toString()})
                    runOnUiThread({textViewResult.text = (mySortedNumbers[0] + proficiency).toString()})
                }).start()                                                                                  // ja laitetaan threadi pyörimmää

            }
            "no advantage" -> {
                Thread(Runnable{

                    var i = 0

                    while(i < 20){
                        runOnUiThread({diceTextView1.text = (1..20).random().toString()})
                        runOnUiThread({diceTextView2.text = (1..20).random().toString()})
                        Thread.sleep(50)
                        i++
                    }
                    val myRandomNumbers = List (2){Random.nextInt(1,21)}

                    runOnUiThread({diceTextView1.text = myRandomNumbers[0].toString()})
                    runOnUiThread({diceTextView2.text = myRandomNumbers[1].toString()})
                    runOnUiThread({textViewResult.text = (myRandomNumbers[0] + proficiency).toString()})
                }).start()

            }
            "Disadvantage" -> {
                Thread(Runnable{

                    var i = 0

                    while(i < 20){
                        runOnUiThread({diceTextView1.text = (1..20).random().toString()})
                        runOnUiThread({diceTextView2.text = (1..20).random().toString()})
                        Thread.sleep(50)
                        i++
                    }
                    val myRandomNumbers = List (2){Random.nextInt(1,21)}
                    val mySortedNumbers = myRandomNumbers.sorted()
                    runOnUiThread({diceTextView1.text = myRandomNumbers[0].toString()})
                    runOnUiThread({diceTextView2.text = myRandomNumbers[1].toString()})
                    runOnUiThread({textViewResult.text = (mySortedNumbers[0] + proficiency).toString()})
                }).start()
            }
            else -> {
                rollTheDice("no advantage")
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
            mp = MediaPlayer.create(this, R.raw.diceroll)
            mp!!.start()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.popup_menu,menu)
        return true
    }                                                                                   //tämä kaikkiin activityihin tekee kolmepisteen appbaariin ja sen jälkeen se näyttää popup menun
                                                                                        // kun siitä klikataan. alempana on myös on funktio kun popupmenun itemeistä klikataan

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_sum->{
                val intent = Intent(this, sumActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_rule->{
                val intent = Intent(this, RuleActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_main->{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_database->{
                val intent = Intent(this, MainDatabaseActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.test->{
                val intent = Intent(this, Charactersheet::class.java)
                startActivity(intent)
                true
            }
            else->super.onOptionsItemSelected(item)
        }
    }
}


