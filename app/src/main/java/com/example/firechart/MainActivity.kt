package com.example.firechart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var database: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database= FirebaseDatabase.getInstance("https://firechat-2-e0e30-default-rtdb.firebaseio.com/")
        var dbReference= database.getReference("message")
        dbReference.push().setValue("wooh")
    }
}
data class Message(var render: String, var text: String)