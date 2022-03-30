package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainDatabaseActivity : AppCompatActivity() {

    private val newCharacterActivityRequestCode = 1
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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newCharacterActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NewCharacterActivity.EXTRA_REPLY)?.let { reply ->
                val character = Character(reply)
                characterViewModel.insert(character)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.popup_menu,menu)
        return true
    }                                                                                   //tämä kaikkiin activityihin tekee kolmepisteen appbaariin ja sen jälkeen se näyttää popup menun
                                                                                        // kun siitä klikataan. alempana on myös on funktio kun popupmenun itemeistä klikataan

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sum -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_rule -> {
                val intent = Intent(this, RuleActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_main -> {
                val intent = Intent(this, sumActivity::class.java)
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
}