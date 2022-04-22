package com.example.myapplication

import MinMaxFilter
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import kotlin.math.nextDown
import kotlin.math.roundToInt

class Charactersheet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dator = intent.extras!!.getStringArray("EXTRA")
        val characterViewModel: CharacterViewModel by viewModels {
            CharacterViewModelFactory((application as CharactersApplication).repository)

        }
        setContentView(R.layout.activity_charactersheet)

        val classbutton : Button = findViewById(R.id.bClass)
        val racebutton : Button = findViewById(R.id.bRace)
        val classtext : TextView = findViewById(R.id.tViewClass)
        val racetext : TextView = findViewById(R.id.tViewRace)
        val updatebutton : Button = findViewById(R.id.bUpdate)
        val leveltext : EditText = findViewById(R.id.eTextNumberLvl)
        val nametext: EditText = findViewById(R.id.eTextName)
        val strtext : EditText = findViewById(R.id.eTextNumberStr)
        val dextext : EditText = findViewById(R.id.eTextNumberDex)
        val context : EditText = findViewById(R.id.eTextNumberCon)
        val inttext : EditText = findViewById(R.id.eTextNumberInt)
        val wistext : EditText = findViewById(R.id.eTextNumberWis)
        val chatext : EditText = findViewById(R.id.eTextNumberCha)
        val swstr : Switch = findViewById(R.id.sProficiencyStr)
        val swdex : Switch = findViewById(R.id.sProficiencyDex)
        val swcon : Switch = findViewById(R.id.sProficiencyCon)
        val swint : Switch = findViewById(R.id.sProficiencyInt)
        val swwis : Switch = findViewById(R.id.sProficiencyWis)
        val swcha : Switch = findViewById(R.id.sProficiencyCha)
        val strmod : TextView = findViewById(R.id.tViewModifierStr)
        val dexmod : TextView = findViewById(R.id.tViewModifierDex)
        val conmod : TextView = findViewById(R.id.tViewModifierCon)
        val intmod : TextView = findViewById(R.id.tViewModifierInt)
        val wismod : TextView = findViewById(R.id.tViewModifierWis)
        val chamod : TextView = findViewById(R.id.tViewModifierCha)
        val strsave : TextView = findViewById(R.id.tViewSaveStr)
        val dexsave : TextView = findViewById(R.id.tViewSaveDex)
        val consave : TextView = findViewById(R.id.tViewSaveCon)
        val intsave : TextView = findViewById(R.id.tViewSaveInt)
        val wissave : TextView = findViewById(R.id.tViewSaveWis)
        val chasave : TextView = findViewById(R.id.tViewSaveCha)




        //teksteihin laitetaan valuet jotka saadaan intentin kautta
        nametext.setText(dator?.elementAt(1))
        leveltext.setText(dator?.elementAt(2))
        classtext.text = dator?.elementAt(3)
        racetext.text = dator?.elementAt(4)
        strtext.setText(dator?.elementAt(5))
        dextext.setText(dator?.elementAt(6))
        context.setText(dator?.elementAt(7))
        inttext.setText(dator?.elementAt(8))
        wistext.setText(dator?.elementAt(9))
        chatext.setText(dator?.elementAt(10))
        swstr.isChecked = dator?.elementAt(11).toBoolean()
        swdex.isChecked = dator?.elementAt(12).toBoolean()
        swcon.isChecked = dator?.elementAt(13).toBoolean()
        swint.isChecked = dator?.elementAt(14).toBoolean()
        swwis.isChecked = dator?.elementAt(15).toBoolean()
        swcha.isChecked = dator?.elementAt(16).toBoolean()
        

        strtext.filters = arrayOf<InputFilter>(MinMaxFilter(1,30))
        dextext.filters = arrayOf<InputFilter>(MinMaxFilter(1,30))
        context.filters = arrayOf<InputFilter>(MinMaxFilter(1,30))
        inttext.filters = arrayOf<InputFilter>(MinMaxFilter(1,30))
        wistext.filters = arrayOf<InputFilter>(MinMaxFilter(1,30))
        chatext.filters = arrayOf<InputFilter>(MinMaxFilter(1,30))
        leveltext.filters = arrayOf<InputFilter>(MinMaxFilter(1,20))



        fun modifierFromScore(score: Int): String {
            when(score){
                1 -> {return "-5"}
                2,3 ->{return "-4"}
                4,5 ->{return "-3"}
                6,7 ->{return "-2"}
                8,9 ->{return "-1"}
                10,11 ->{return "0"}
                12,13 ->{return "1"}
                14,15 ->{return "2"}
                16,17 ->{return "3"}
                18,19 ->{return "4"}
                20,21 ->{return "5"}
                22,23 ->{return "6"}
                24,25 ->{return "7"}
                26,27 ->{return "8"}
                28,29 ->{return "9"}
                30 ->{return "10"}
                else ->{return "0"}

            }
        }

        fun updateText(proficiency: Int){

            val strpro = swstr.isChecked
            val dexpro = swdex.isChecked
            val conpro = swcon.isChecked
            val intpro = swint.isChecked
            val wispro = swwis.isChecked
            val chapro = swcha.isChecked



            strmod.text = modifierFromScore(strtext.text.toString().toInt())
            dexmod.text = modifierFromScore(dextext.text.toString().toInt())
            conmod.text = modifierFromScore(context.text.toString().toInt())
            intmod.text = modifierFromScore(inttext.text.toString().toInt())
            wismod.text = modifierFromScore(wistext.text.toString().toInt())
            chamod.text = modifierFromScore(chatext.text.toString().toInt())                        //eli modifieri saadaan scoresta ja tietty score antaa tietyn modifierin. se näkyy tuossa modifierFromScore funktiosta


            if(strpro){
                strsave.text = (modifierFromScore(strtext.text.toString().toInt()).toInt()+proficiency).toString()              // ja sitten tässä katsotaan, että onko proficiency checkattu
            }                                                                                                            // ja jos on, niin silloin savearvo pitäisi olla proficiency+modifier
            else{                                                                                                       // ja proficiency saadaan kun tätä funktiota callataan.
                strsave.text = modifierFromScore(strtext.text.toString().toInt())                                       //jos proficiencyä ei ole tähän, niin silloin save arvo on sama kuin modifier
            }

            if(dexpro){
                dexsave.text = (modifierFromScore(dextext.text.toString().toInt()).toInt()+proficiency).toString()
            }
            else{
                dexsave.text = modifierFromScore(dextext.text.toString().toInt())
            }

            if(conpro){
                consave.text = (modifierFromScore(context.text.toString().toInt()).toInt()+proficiency).toString()
            }
            else{
                consave.text = modifierFromScore(context.text.toString().toInt())
            }

            if(intpro){
                intsave.text = (modifierFromScore(inttext.text.toString().toInt()).toInt()+proficiency).toString()
            }
            else{
                intsave.text = modifierFromScore(inttext.text.toString().toInt())
            }

            if(wispro){
                wissave.text = (modifierFromScore(wistext.text.toString().toInt()).toInt()+proficiency).toString()
            }
            else{
                wissave.text = modifierFromScore(wistext.text.toString().toInt())
            }

            if(chapro){
                chasave.text = (modifierFromScore(chatext.text.toString().toInt()).toInt()+proficiency).toString()
            }
            else{
                chasave.text = modifierFromScore(chatext.text.toString().toInt())
            }

        }
        updatebutton.setOnClickListener{
            Log.d("tagUpdClcLis","updateclicklistener")
            val replyIntentUpdate = Intent()
            if (TextUtils.isEmpty(nametext.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntentUpdate)
            } else {

            val id=dator?.elementAt(0)
            val name = nametext.text.toString()
            val race = racetext.text.toString()
            val charlevel = leveltext.text.toString()                                     //buttoni tallettaa arvot, mitkä on pistetty paikoilleen ja tallentaa ne databaseen
            val charclass = classtext.text.toString()
            val str = strtext.text.toString()
            val dex = dextext.text.toString()
            val con = context.text.toString()
            val int = inttext.text.toString()
            val wis = wistext.text.toString()
            val cha = chatext.text.toString()
            val strpro = swstr.isChecked.toString()                                                            //eli proficiency tallennetaan ture/falsena ja statsit ja leveli talletetaan inttinä
            val dexpro = swdex.isChecked.toString()                                                            // ja loput eli nimi, race sekä classi tallennetaan stringinä.
            val conpro = swcon.isChecked.toString()
            val intpro = swint.isChecked.toString()
            val wispro = swwis.isChecked.toString()
            val chapro = swcha.isChecked.toString()
                Log.d("gorillainmycode","$chapro")

                // ja updatetaan modifier ja save textviewit
                replyIntentUpdate.putExtra(EXTRA_REPLY, arrayOf(id,name,charlevel,charclass,race,str,dex,con,int,wis,cha,strpro,dexpro,conpro,intpro,wispro,chapro))

                setResult(Activity.RESULT_OK, replyIntentUpdate)
            }
            finish()
        }
        val charLevelForProficensy = leveltext.text.toString()
        val proficiency = 2+(0.25*(charLevelForProficensy.toInt()-1)).nextDown().toInt()                             // tämä funktio laskee levelistä, mikä proficiency sinulla pitäisi olla.
        updateText(proficiency)
        strmod.setOnClickListener{                                                                  //pistetään jokaiselle textviewille onclick listener jolla aukaistaan noppasovellus
                                                                                                    //lähetetään samalla dataa sinne, jotta se saa modifierin, jolla rollata
            val data:Int = strmod.text.toString().toInt()                                           // ja tällä hetkellä roll on aina true eli se tulee rollaamaan aina kun
            val roll:Boolean = true                                                                 //activity aukaistaan
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("data", data)
            intent.putExtra("roll", roll)
            startActivity(intent)
        }
        dexmod.setOnClickListener{

            val data:Int = dexmod.text.toString().toInt()
            val roll:Boolean = true
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("data", data)
            intent.putExtra("roll", roll)
            startActivity(intent)
        }

        conmod.setOnClickListener{

            val data:Int = conmod.text.toString().toInt()
            val roll:Boolean = true
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("data", data)
            intent.putExtra("roll", roll)
            startActivity(intent)
        }

        intmod.setOnClickListener{

            val data:Int = intmod.text.toString().toInt()
            val roll:Boolean = true
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("data", data)
            intent.putExtra("roll", roll)
            startActivity(intent)
        }

        wismod.setOnClickListener{

            val data:Int = wismod.text.toString().toInt()
            val roll:Boolean = true
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("data", data)
            intent.putExtra("roll", roll)
            startActivity(intent)
        }

        chamod.setOnClickListener{

            val data:Int = chamod.text.toString().toInt()
            val roll:Boolean = true
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("data", data)
            intent.putExtra("roll", roll)
            startActivity(intent)
        }
        strsave.setOnClickListener{

            val data:Int = strsave.text.toString().toInt()
            val roll:Boolean = true
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("data", data)
            intent.putExtra("roll", roll)
            startActivity(intent)
        }
        dexsave.setOnClickListener{

            val data:Int = dexsave.text.toString().toInt()
            val roll:Boolean = true
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("data", data)
            intent.putExtra("roll", roll)
            startActivity(intent)
        }
        consave.setOnClickListener{

            val data:Int = consave.text.toString().toInt()
            val roll:Boolean = true
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("data", data)
            intent.putExtra("roll", roll)
            startActivity(intent)
        }
        intsave.setOnClickListener{

            val data:Int = intsave.text.toString().toInt()
            val roll:Boolean = true
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("data", data)
            intent.putExtra("roll", roll)
            startActivity(intent)
        }
        wissave.setOnClickListener{

            val data:Int = wissave.text.toString().toInt()
            val roll:Boolean = true
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("data", data)
            intent.putExtra("roll", roll)
            startActivity(intent)
        }
        chasave.setOnClickListener{

            val data:Int = chasave.text.toString().toInt()
            val roll:Boolean = true
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("data", data)
            intent.putExtra("roll", roll)
            startActivity(intent)
        }




        racebutton.setOnClickListener{
            val popupMenu: PopupMenu = PopupMenu(this,classbutton)
            popupMenu.menuInflater.inflate(R.menu.popup_races_menu , popupMenu.menu)
            popupMenu.setOnMenuItemClickListener (PopupMenu.OnMenuItemClickListener {item ->            //perus popup menu classeille sekä racelle.
                when(item.itemId){
                    R.id.action_human->{
                        racetext.text = "Human"
                        true
                    }
                    R.id.action_elf->{
                        racetext.text = "Elf"
                        true
                    }
                    R.id.action_dwarf->{
                        racetext.text = "Dwarf"
                        true
                    }
                    R.id.action_gnome->{
                        racetext.text = "Gnome"
                        true
                    }
                    R.id.action_halfling->{
                        racetext.text = "Halfling"
                        true
                    }
                    R.id.action_half_elf->{
                        racetext.text = "Half Elf"
                        true
                    }
                    R.id.action_half_orc->{
                        racetext.text = "Half Orc"
                        true
                    }
                    else->super.onOptionsItemSelected(item)

                }
            })
            popupMenu.show()

        }
        classbutton.setOnClickListener{
            val popupMenu: PopupMenu = PopupMenu(this,classbutton)
            popupMenu.menuInflater.inflate(R.menu.popup_classes_menu , popupMenu.menu)
            popupMenu.setOnMenuItemClickListener (PopupMenu.OnMenuItemClickListener {item ->
                when(item.itemId){
                    R.id.action_barbarian->{
                        classtext.text = "Barbarian"
                        true
                    }
                    R.id.action_fighter->{
                        classtext.text = "Fighter"
                        true
                    }
                    R.id.action_rogue->{
                        classtext.text = "Rogue"
                        true
                    }
                    R.id.action_monk->{
                        classtext.text = "Monk"
                        true
                    }
                    R.id.action_ranger->{
                        classtext.text = "Ranger"
                        true
                    }
                    R.id.action_warlock->{
                        classtext.text = "Warlock"
                        true
                    }
                    R.id.action_cleric->{
                        classtext.text = "Cleric"
                        true
                    }
                    R.id.action_bard->{
                        classtext.text = "Bard"
                        true
                    }
                    R.id.action_sorcerer->{
                        classtext.text = "Sorcerer"
                        true
                    }
                    R.id.action_druid->{
                        classtext.text = "Druid"
                        true
                    }
                    R.id.action_wizard->{
                        classtext.text = "Wizard"
                        true
                    }
                    else->super.onOptionsItemSelected(item)


                }
            })
            try {
                val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")                  //tämä vain siksi, että saadaan iconit näkyville, muuten ei näy iconeja popupmenussa.
                fieldMPopup.isAccessible = true
                val mPopup = fieldMPopup.get(popupMenu)
                mPopup.javaClass
                    .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            } catch (e: Exception) {
                Log.e("Main", "Error showing menu icons.", e)
            } finally {
                popupMenu.show()
            }
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
    companion object {
        const val EXTRA_REPLY = "com.example.android.characterlistsql.REPLY"

    }
}