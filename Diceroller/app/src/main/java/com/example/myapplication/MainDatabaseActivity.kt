package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainDatabaseActivity : AppCompatActivity(), CharacterListAdapter.OnCharacterClickListener {

    private val newCharacterActivityRequestCode = 1
    private val deleteCharacterActivityRequestCode = 2
    private val updateCharacterActivityRequestCode = 3
    private val characterViewModel: CharacterViewModel by viewModels {
        CharacterViewModelFactory((application as CharactersApplication).repository)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_database)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = CharacterListAdapter(this)


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        characterViewModel.allCharacters.observe(owner = this) { characters ->
            // Update the cached copy of the words in the adapter.
            characters.let { adapter.submitList(it) }


        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainDatabaseActivity, NewCharacterActivity::class.java)
            startActivityForResult(intent, newCharacterActivityRequestCode)


        }
        val deleteButtonClick = findViewById<ImageButton>(R.id.DeleteButton)
        deleteButtonClick.setOnClickListener {
            val intent = Intent(this@MainDatabaseActivity, DeleteCharacterActivity::class.java)
            startActivityForResult(intent, deleteCharacterActivityRequestCode)


        }

    }
    override fun onCharacterClick(position: Characterdata) {
        Log.d("gorillagrip","$position")

        val id= position.id.toString()
        val name= position.character.toString()
        val level= position.level.toString()
        val characterclass=position.job.toString()
        val race=position.race.toString()
        val str=position.str.toString()
        val dex=position.dex.toString()
        val con=position.con.toString()
        val ao= position.ao.toString()
        val wis=position.wis.toString()
        val cha=position.wis.toString()
        val strpro=position.strpro.toString()
        val dexpro=position.dexpro.toString()
        val conpro=position.conpro.toString()
        val intpro=position.intpro.toString()
        val wispro=position.wispro.toString()
        val chapro=position.chapro.toString()


        intent = Intent(this@MainDatabaseActivity, Charactersheet::class.java)
        intent.putExtra("EXTRA", arrayOf( id,name,level,characterclass,race,str,dex,con,ao,wis,cha,strpro,dexpro,conpro,intpro,wispro,chapro))
        startActivityForResult(intent, updateCharacterActivityRequestCode)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newCharacterActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringArrayExtra(NewCharacterActivity.EXTRA_REPLY)?.let { reply ->
                Log.d("newctag","successnewc")
                val characterdata = Characterdata(
                    0,
                    reply.elementAt(0),
                    reply.elementAt(1).toInt(),
                    reply.elementAt(2),
                    reply.elementAt(3),
                    reply.elementAt(4).toInt(),
                    reply.elementAt(5).toInt(),
                    reply.elementAt(6).toInt(),
                    reply.elementAt(7).toInt(),
                    reply.elementAt(8).toInt(),
                    reply.elementAt(9).toInt(),
                    strpro = false,
                    dexpro = false,
                    conpro = false,
                    intpro = false,
                    wispro = false,
                    chapro = false
                )
                characterViewModel.insert(characterdata)

            }

        }
        if (requestCode == deleteCharacterActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringArrayExtra(DeleteCharacterActivity.EXTRA_REPLY)?.let { reply ->
                val id = reply.elementAt(0).toInt()
                characterViewModel.deleteByUserId(id)

            }

        }
        if (requestCode == updateCharacterActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringArrayExtra(Charactersheet.EXTRA_REPLY)?.let { reply ->
                Log.d("gorillagrip","aaaaah")
                val characterdata = Characterdata(
                    reply.elementAt(0).toInt(),
                    reply.elementAt(1),
                    reply.elementAt(2).toInt(),
                    reply.elementAt(3),
                    reply.elementAt(4),
                    reply.elementAt(5).toInt(),
                    reply.elementAt(6).toInt(),
                    reply.elementAt(7).toInt(),
                    reply.elementAt(8).toInt(),
                    reply.elementAt(9).toInt(),
                    reply.elementAt(10).toInt(),
                    reply.elementAt(11).toBoolean(),
                    reply.elementAt(12).toBoolean(),
                    reply.elementAt(13).toBoolean(),
                    reply.elementAt(14).toBoolean(),
                    reply.elementAt(15).toBoolean(),
                    reply.elementAt(16).toBoolean()

                )
                characterViewModel.updateByUserId(characterdata)

            }

        }
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

    companion object {
        const val EXTRA_REPLY = "com.example.android.characterlistsql.REPLY"

    }
}

