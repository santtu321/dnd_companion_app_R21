package com.example.myapplication

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
import com.google.gson.Gson
import com.google.gson.GsonBuilder


class RuleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rule)


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

    fun getDataSpells(){
        var rvSpellData = findViewById<RecyclerView>(R.id.rvSpellData)
        var et_dataInput = findViewById<EditText>(R.id.et_dataInput)

        //val aList = listOf<SpellReportModel>()

        val urlSpells = "https://www.dnd5eapi.co/api/spells/" + et_dataInput.getText().toString()
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET,urlSpells, { response ->
            /*  val data = response.toString()
              var jArray = JSONObject(data)
              //val damageScore = jArray.getJSONObject("damage").getString("damage_at_slot_level")


              aList.add(
                  SpellReportModel(
                      jArray.getString("index"),
                      jArray.getString("name"),
                      jArray.getString("desc").replace("[", "").replace("]", ""),
                      jArray.getString("range"),
                      jArray.getString("duration"),
                      jArray.getJSONObject("damage").getJSONObject("damage_at_slot_level").getString("3"),
                      jArray.optString("heal_at_slot_level"),

                  )
              )*/


            var data = response.toString()
            Log.d("Logs", data.toString())

            var SpellReportModel = Gson().fromJson(data, SpellReportModel::class.java)


            Log.d("Logs", SpellReportModel.toString())

            rvSpellData.adapter = SpellAdapter(SpellReportModel.spellReportList)




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
