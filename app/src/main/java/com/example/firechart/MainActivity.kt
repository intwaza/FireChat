package com.example.firechart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firechart.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var database: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database= FirebaseDatabase.getInstance()
//        database= FirebaseDatabase.getInstance("https://firechat-2-e0e30-default-rtdb.firebaseio.com/")
//        var dbReference= database.getReference("message")
//        dbReference.push().setValue("wooh")
    }

    override fun onResume() {
        super.onResume()
        readMessageFromDatabase()
        binding.btnSend.setOnClickListener {
            var text= binding.etName.text.toString()
            if(text.isBlank() || text.isEmpty()){
                binding.etName.setError("Message is required")
            }
            else{
                postMessage(text)
            }
        }
    }
    fun postMessage(text: String){
        var dbRef= database.getReference("message").child("message")
        var message= Messages("Message",text)
        dbRef.push().setValue(message)
    }
    fun readMessageFromDatabase(){
        var messages= mutableListOf<String>()
        var dbRef= database.getReference("message").child("message")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (messageSnapshot in snapshot.children){
//                    var message= messageSnapshot.getValue(String::class.java)
                    val map = messageSnapshot.getValue()

                    messages.add(map!!.toString())
                }

                val adapter= MessageAdapter(messages)
                binding.recyclerView.layoutManager= LinearLayoutManager(this@MainActivity)
                binding.recyclerView.adapter=adapter
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}
