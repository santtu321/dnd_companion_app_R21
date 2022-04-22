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

class DeleteCharacterActivity : AppCompatActivity() {
    private lateinit var deleteCharacterView: EditText



    public override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TAG", "messageaa")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_character)
        deleteCharacterView = findViewById(R.id.delete_character)

        Log.d("TAG", "message")

        val button = findViewById<Button>(R.id.button_delete)
        button.setOnClickListener {
            val replyIntentdelete = Intent()
            if (TextUtils.isEmpty(deleteCharacterView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntentdelete)
            } else {
                val id = deleteCharacterView.text.toString()
                Log.d("TAG", "message")
                Log.d("mytag","delete")
                //replyIntent.putExtra(EXTRA_REPLY, character)
                replyIntentdelete.putExtra(EXTRA_REPLY, arrayOf(id))

                setResult(Activity.RESULT_OK, replyIntentdelete)

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