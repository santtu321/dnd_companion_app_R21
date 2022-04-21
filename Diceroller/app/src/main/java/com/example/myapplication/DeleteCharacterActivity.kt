package com.example.myapplication

import android.app.Activity
import android.util.Log
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
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


                Log.d("TAG", "message")
                setResult(Activity.RESULT_OK, replyIntentdelete)

            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.characterlistsql.REPLY"

    }
}