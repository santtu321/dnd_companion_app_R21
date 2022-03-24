package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
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

}