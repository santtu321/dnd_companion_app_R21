package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.content.ContextCompat

class Charactersheet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        updatebutton.setOnClickListener{
            val name = nametext.text.toString()
            val race = racetext.text.toString()
            val charlevel = leveltext.text.toString().toInt()
            val charclass = classtext.text.toString()
            val str = strtext.text.toString().toInt()
            val dex = dextext.text.toString().toInt()
            val con = context.text.toString().toInt()
            val int = inttext.text.toString().toInt()
            val wis = wistext.text.toString().toInt()
            val cha = chatext.text.toString().toInt()

            //TODO
            //eli tähän  updatettaa arvot sinne databaseen
            //TODO
        }



        racebutton.setOnClickListener{
            val popupMenu: PopupMenu = PopupMenu(this,classbutton)
            popupMenu.menuInflater.inflate(R.menu.popup_races_menu , popupMenu.menu)
            popupMenu.setOnMenuItemClickListener (PopupMenu.OnMenuItemClickListener {item ->
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
            popupMenu.show()
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