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
import androidx.recyclerview.widget.ListAdapter
import androidx.room.Room


class MainDatabaseActivity : AppCompatActivity() {

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
        val adapter = CharacterListAdapter()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.setOnCharacterClickListener(object : CharacterListAdapter.OnCharacterClickListener {
            override fun onCharacterClick(positionC: Int) {

                //characterViewModel.loadCharacter(positionC)
                /* CoroutineScope(Dispatchers.IO).launch {
                     val raw = characterViewModel.loadCharacter(positionC).get(0)
                     Log.d("mytagrip","$raw")
                 }*/

                val intent = Intent(this@MainDatabaseActivity, Charactersheet::class.java)
                intent.putExtra("EXTRA", positionC)
                startActivity(intent)

                /* val intent = Intent(this@MainDatabaseActivity, Charactersheet::class.java)
                 startActivityForResult(intent, updateCharacterActivityRequestCode)*/
                Log.d("mytag1", "$positionC")


            }

        })


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
        /*  val updateButtonClick = findViewById<ImageButton>(R.id.updateButton)
          updateButtonClick.setOnClickListener {
              val intent = Intent(this@MainDatabaseActivity, updateCharacterActivity::class.java)
              startActivityForResult(intent, deleteCharacterActivityRequestCode)



          }*/
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newCharacterActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringArrayExtra(NewCharacterActivity.EXTRA_REPLY)?.let { reply ->

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
                    reply.elementAt(9).toInt()
                )
                characterViewModel.insert(characterdata)

            }

        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }

        if (requestCode == deleteCharacterActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringArrayExtra(DeleteCharacterActivity.EXTRA_REPLY)?.let { reply ->
                val id = reply.elementAt(0).toInt()
                characterViewModel.deleteByUserId(id)

            }

        }

        if (requestCode == updateCharacterActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringArrayExtra(Charactersheet.EXTRA_REPLY)?.let { reply ->

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
                    reply.elementAt(10).toInt()
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
        return when (item.itemId) {
            R.id.action_sum -> {
                val intent = Intent(this, sumActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_rule -> {
                val intent = Intent(this, RuleActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_main -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_database -> {
                val intent = Intent(this, MainDatabaseActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.characterlistsql.REPLY"

    }
}