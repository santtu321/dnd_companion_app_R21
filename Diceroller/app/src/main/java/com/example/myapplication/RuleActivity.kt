package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.databinding.ActivityRuleBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject


class RuleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRuleBinding
    private lateinit var adapter: SpellAdapter
    private var aList: ArrayList<SpellReportModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityRuleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRV()

        //assign values
        var btn_getData = findViewById<Button>(R.id.btn_getData)
        var et_dataInput = findViewById<EditText>(R.id.et_dataInput)


        et_dataInput.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // TODO Auto-generated method stub
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // TODO Auto-generated method stub
            }
            override fun afterTextChanged(s: Editable) {
                ""
            }
        })




        //listeners
        btn_getData.setOnClickListener {
            getDataSpells()

        }
    }

    private fun initRV() {
        adapter = SpellAdapter(aList)
        binding.rvSpellData.layoutManager = LinearLayoutManager(this)
        binding.rvSpellData.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getDataSpells(){
        var et_dataInput = findViewById<EditText>(R.id.et_dataInput)



        val urlSpells = "https://www.dnd5eapi.co/api/spells/fireball"
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET,urlSpells, { response ->
              val data = response.toString()
              var jArray = JSONObject(data)


            aList.add(
                SpellReportModel(
                    jArray.optString("name"),
                    jArray.optString("desc").replace("[", "").replace("]", ""),
                    jArray.optString("range"),
                    jArray.optString("duration"),
                    jArray.getJSONObject("damage").getJSONObject("damage_type").optString("name"),
                    jArray.getJSONObject("damage").getJSONObject("damage_at_slot_level").optString("3"),
                    jArray.getJSONObject("damage").getJSONObject("damage_at_slot_level").optString("4"),
                    jArray.getJSONObject("damage").getJSONObject("damage_at_slot_level").optString("5"),
                    jArray.getJSONObject("damage").getJSONObject("damage_at_slot_level").optString("6"),
                    jArray.getJSONObject("damage").getJSONObject("damage_at_slot_level").optString("7"),
                    jArray.getJSONObject("damage").getJSONObject("damage_at_slot_level").optString("8"),
                    jArray.getJSONObject("damage").getJSONObject("damage_at_slot_level").optString("9"),
                    jArray.optString("casting_time"),
                    jArray.getJSONObject("school").optString("name"),
                    )
            )

            adapter.notifyDataSetChanged()


        }, {
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
        })
        queue.add(request)




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
