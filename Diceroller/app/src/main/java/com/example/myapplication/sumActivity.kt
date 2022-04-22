package com.example.myapplication

import MinMaxFilter
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.view.*
import android.widget.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.ceil
import kotlin.random.Random

class sumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sum)

        val diceAmount: EditText = findViewById(R.id.eText3)
        val radioGroup: RadioGroup = findViewById(R.id.rGroup1)
        val diceSides: EditText = findViewById(R.id.eText2)
        val rollButton2: Button = findViewById(R.id.bRoll2)
        val sum: TextView = findViewById(R.id.tViewSum)
        var mp: MediaPlayer? = null

        diceAmount.filters = arrayOf<InputFilter>(MinMaxFilter(1,100))                                    //tämä filteroi käyttäjäninputin, ettei hän pysty pistämään muita lukuja kuin 1-100
        diceSides.filters = arrayOf<InputFilter>(MinMaxFilter(1,100))                                     //tuo MinMaxFilter koodi löytyy InputFilter.kt tiedostosta



        fun intenseRoll(index: Int, tView : TextView, myRandomNumbers: List<Int>) = runBlocking {         // pistetään tässä nuo threadit omaan funktioon, jossa käytetään co
            launch {                                                                                      //routineja, koska muuten yksittäinen threadi ei pysty tekemään kaikkia
                Thread(Runnable{                                                                          // numeroita yhtäaikaa ja siitä tulisi sekava.

                    var i = 0
                    var total = 0
                    var dices = diceAmount.text.toString().toInt()
                    val maxroll = diceSides.text.toString().toInt()
                    val resistance = radioGroup.checkedRadioButtonId
                    val radioButton: RadioButton = findViewById(resistance)



                    while(i < 20){
                        if(i != 19){
                            runOnUiThread({tView.text = (1..maxroll).random().toString()})                 //toimii samalla tavalla kuin mainactivityssäkin olevat threadit.
                                                                                                           //ainoa ero on, että tätä funktiota kutsutaan aina kun uusi textview olio luodaan
                            Thread.sleep(50)                                                         // eli tämä funktio pyörittää yhtä oliota kerralla.
                        }
                        else{
                            runOnUiThread({tView.text = myRandomNumbers[index-1].toString()})

                            when(myRandomNumbers[index-1]){                                             //tässä sitten katsotaan, että jos tuli 1, niin laitetaan tekstin väri punaiseksi,
                                1->{                                                                    //ja jos tuli maksimiluku nopasta, niin laitetaan teksti vihreäksi.

                                    tView.setTextColor(Color.RED)
                                }
                                maxroll ->{

                                    tView.setTextColor(Color.GREEN)
                                }
                            }
                        }
                        i++
                    }
                    var z = 0                                                                               // tässä vielä summataan kaikki randomit numerot yhteen
                                                                                                            // ja laitetaan tulos sum textviewiin.
                                                                                                            //laitetaan tämä tähän, koska ennen se oli tuolla createwidgets funktion lopussa,
                    while (dices > z)                                                                       //jonka takia tulos tuli ennen kuin nopat olivat rollanneet
                    {                                                                                       //nyt tulos tulee vasta kun nopat ovat rullanneet
                        total += myRandomNumbers[z]
                        z++
                    }
                    when(radioButton.text){
                        "Weak"->
                        {
                           sum.text = (total*2).toString()
                        }
                        "Resistant"->
                        {
                            sum.text = (total/2).toString()
                        }
                        "Neutral"->
                        {
                            sum.text = total.toString()
                        }
                    }


                }).start()
            }
        }
        fun createWidgets(rows: Int, dices: Int){

            var x = 1
            var k = 1


            val myRandomNumbers = List (dices){Random.nextInt(1,diceSides.text.toString().toInt()+1)}  //tehdään ensimmäiseksi randomit numerot
                                                                                                                 // listan koko ja numerot määrääntyy siitä, mitä käyttäjä antoi parametreiksi

            val parent: LinearLayout = findViewById(R.id.pLayout)                                   //tehdään olio meidän linearlayoutista, johon pistetään kaikki tulevat layoutit
            parent.removeAllViews()                                                                 //ja jos siinä oli jo jotakin lapsia, niin tuhotaan ne ennenkuin aletaan tekemään lisää.

            while (rows >= x){
                var i = 0

                val lL = LinearLayout(this)
                lL.layoutParams = LinearLayout.LayoutParams(                                        // eli tässä luodaan linearlayoutteja niin paljon kuin rivejä haluttiin.
                    LinearLayout.LayoutParams.MATCH_PARENT,                                         // ja annetaan niille parametrit tässä
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                lL.id = x
                lL.orientation = LinearLayout.HORIZONTAL
                lL.gravity = Gravity.CENTER_HORIZONTAL

                parent.addView(lL)                                                                  // tässä me liitämme lL layoutin meidän parent layouttiin.

                while (i < 8 && dices >= k) {                                                       // jonka jälkeen pistämmä lL layouttiin 10 textview oliota, jotka toimivat noppina.
                    val tView = TextView(this)

                    tView.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    tView.id = x+10
                    tView.text = "${myRandomNumbers[k-1]}"



                    tView.textSize = 30.0F
                    tView.setPadding(10,0,10,0)                                // taas parametrejä text oliolle.
                    tView.gravity = Gravity.CENTER
                    tView.setTextColor(0xff000000.toInt())
                    tView.setTypeface(null, Typeface.BOLD)

                    lL.addView(tView)                                                               // ja liitetään text olio lL layouttiin.
                    intenseRoll(k, tView, myRandomNumbers)                                          //kutsutaan rollausfunktiota ja annetaan sille tämä textiolio mikä luotiin juuri ja
                    i++                                                                             //sen hetkinen noppanumero mikä on k ja ne oikeat numerot josta k:ta käyttämällä löydämme
                    k++                                                                             //sille oikean numeron. tämä tehdään sen takia, koska muuten näytössä olevat numerot ei ole
                                                                                                    //sama, mikä summatuloksessa oleva numero.
                }
                x++                                                                                 // tämä rumba kestää niinkauan, kun rivejä on ja jokaiseen riviin teemme 10 text oliota.
            }                                                                                       // samalla pidetään lukua nopista mikä on k variable.
            if(dices >= k) {                                                                        // ja jos noppia jäi vielä niin tässä vielä tehdään yksi rivi jäljellä oleville nopille.
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

                    while (i < 8 && dices >= k) {
                        val tView = TextView(this)

                        tView.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                        )
                        tView.id = x+10
                        tView.text = "${myRandomNumbers[k-1]}"
                        tView.textSize = 30.0F
                        tView.setPadding(10,0,10,0)
                        tView.gravity = Gravity.CENTER
                        tView.setTextColor(0xff000000.toInt())
                        tView.setTypeface(null, Typeface.BOLD)

                        lL.addView(tView)
                        intenseRoll(k, tView, myRandomNumbers)
                        i++
                        k++
                    }
                }



        }




        rollButton2.setOnClickListener{

            var rows = (diceAmount.text.toString().toInt()/8).toDouble()                            // kutsutaan createwidgets funktiota ja annetaan sille parametreiksi kuinka monta
            rows = ceil(rows)                                                                       // riviä sen pitää tehdä eli noppamäärä/8 ja kuin monta noppaa halutaan.
                                                                                                    // ja 8 noppaa yhdelle riville muuten alkaa tulemaan liian ahdasta välillä.

            var dices = diceAmount.text.toString().toInt()
            createWidgets(rows.toInt(), dices)
            mp = MediaPlayer.create(this, R.raw.diceroll)
            mp!!.start()
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.popup_menu,menu)
        return true
    }                                                                                               //tämä kaikkiin activityihin tekee kolmepisteen appbaariin ja sen jälkeen se näyttää popup menun
                                                                                                    // kun siitä klikataan. alempana on myös on funktio kun popupmenun itemeistä klikataan

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_back->{
                onBackPressed()
                true
            }
            R.id.action_home->{
                val intent = Intent(this, RuleActivity::class.java)
                startActivity(intent)
                true

            }
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