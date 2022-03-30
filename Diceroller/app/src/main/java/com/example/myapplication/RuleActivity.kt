package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class RuleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rule)

        //assign values
        var btn_getData = findViewById<Button>(R.id.btn_getData)
        var et_dataInput = findViewById<EditText>(R.id.et_dataInput)


        //listeners
        btn_getData.setOnClickListener {
            getDataSpells()
        }
    }

    fun getDataSpells(){
        var lv_dndData = findViewById<ListView>(R.id.lv_dndData)
        val aList = arrayListOf<SpellReportModel>()
        var et_dataInput = findViewById<EditText>(R.id.et_dataInput)
        val urlSpells = "https://www.dnd5eapi.co/api/spells/" + et_dataInput.getText().toString()
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET,urlSpells, { response ->
            val data = response.toString()
            var jArray = JSONObject(data)
            //  Toast.makeText(this,"Data" + jArray,Toast.LENGTH_SHORT).show()

            aList.add(
                SpellReportModel(
                    jArray.getString("index"),
                    jArray.getString("name"),
                    jArray.getString("desc")
                )
            )
            val adapter = SpellAdapter(this, aList)
            lv_dndData.adapter = adapter


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
            R.id.action_sum->{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_rule->{
                val intent = Intent(this, RuleActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_main->{
                val intent = Intent(this, sumActivity::class.java)
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