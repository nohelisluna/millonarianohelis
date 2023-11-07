package com.example.millonarianohelis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.millonarianohelis.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var DB: FirebaseDatabase
    lateinit var refEspañol: DatabaseReference
    lateinit var refMatematicas: DatabaseReference
    lateinit var refHistoria: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView.animate()
            .setDuration(5000)
            .scaleX(1f)
            .scaleY(1f)
        start()
    }
    fun start(){
        Handler().postDelayed({
            startActivity(Intent(this@MainActivity,OptionesActivity::class.java))
        },5000)
    }
}