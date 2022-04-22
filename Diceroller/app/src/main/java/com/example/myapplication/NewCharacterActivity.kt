package com.example.myapplication

import android.app.Activity
import android.util.Log
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class NewCharacterActivity : AppCompatActivity() {
    private lateinit var editWordView: EditText
    private lateinit var editLevelView: EditText
    private lateinit var editJobView: EditText
    private lateinit var editRaceView: EditText
    private lateinit var editSTRView: EditText
    private lateinit var editDEXView: EditText
    private lateinit var editCONView: EditText
    private lateinit var editINTView: EditText
    private lateinit var editWISView: EditText
    private lateinit var editCHAView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)
        editWordView = findViewById(R.id.edit_word)
        editLevelView = findViewById(R.id.edit_level)
        editJobView = findViewById(R.id.edit_job)
        editRaceView = findViewById(R.id.edit_race)
        editSTRView = findViewById(R.id.edit_str)
        editDEXView = findViewById(R.id.edit_dex)
        editCONView = findViewById(R.id.edit_con)
        editINTView = findViewById(R.id.edit_int)
        editWISView = findViewById(R.id.edit_wis)
        editCHAView = findViewById(R.id.edit_cha)


        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWordView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
                Log.d("canceltag","cancelled")
            } else {

                val character = editWordView.text.toString()
                val level = editLevelView.text.toString()
                val job= editJobView.text.toString()
                val race= editRaceView.text.toString()
                val str= editSTRView.text.toString()
                val dex= editDEXView.text.toString()
                val con= editCONView.text.toString()
                val ao= editINTView.text.toString()
                val wis= editWISView.text.toString()
                val cha= editCHAView.text.toString()
                Log.d("mytag","job")
                replyIntent.putExtra(EXTRA_REPLY, arrayOf(character,level,job,race,str,dex,con,ao,wis,cha))

                setResult(Activity.RESULT_OK, replyIntent)

            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.characterlistsql.REPLY"

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.popup_menu, menu)
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
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true

            }
            R.id.action_sum->{
                val intent = Intent(this, sumActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_rule_spells->{
                val intent = Intent(this, RuleActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_rule_monster->{
                val intent = Intent(this, RuleActivityMonsters::class.java)
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

            else->super.onOptionsItemSelected(item)
        }
    }
}

