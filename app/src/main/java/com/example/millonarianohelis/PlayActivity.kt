package com.example.millonarianohelis

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.millonarianohelis.ElegirCatActivity.Companion.espa
import com.example.millonarianohelis.ElegirCatActivity.Companion.histo
import com.example.millonarianohelis.ElegirCatActivity.Companion.mate
import com.example.millonarianohelis.Entitys.Model
import com.example.millonarianohelis.databinding.ActivityPlayBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.random.Random
import kotlin.random.nextInt

class PlayActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayBinding
    lateinit var DB: FirebaseDatabase
    lateinit var refEspañol: DatabaseReference
    lateinit var refMatematicas: DatabaseReference
    lateinit var refHistoria: DatabaseReference
    var lista:MutableList<Model> = mutableListOf()
    var r=0
    var premio=0
    lateinit var rpt:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPlayBinding.inflate(layoutInflater)
        DB= FirebaseDatabase.getInstance()
        refMatematicas = DB.getReference("Matematicas")
        refEspañol = DB.getReference("Español")
        refHistoria = DB.getReference("Historia")
        setContentView(binding.root)
        getData()
        jugar()
        validar()
    }
    fun getData(){
        if(mate=="si")
        {
            refMatematicas.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        var data=it.getValue(Model::class.java)
                        lista.add(Model(data!!.pregunta,data!!.respuesta,data!!.a,data!!.b,data!!.c,data!!.d))
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@PlayActivity, "error", Toast.LENGTH_SHORT).show()
                }
            })
        }
        if(histo=="si")
        {
            refHistoria.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        var data=it.getValue(Model::class.java)
                        lista.add(Model(data!!.pregunta,data!!.respuesta,data!!.a,data!!.b,data!!.c,data!!.d))
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@PlayActivity, "error", Toast.LENGTH_SHORT).show()
                }
            })
        }
        if(espa=="si")
        {
            refEspañol.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        var data=it.getValue(Model::class.java)
                        lista.add(Model(data!!.pregunta,data!!.respuesta,data!!.a,data!!.b,data!!.c,data!!.d))
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@PlayActivity, "error", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
    fun jugar(){
        Handler().postDelayed({
            binding.textView2.setBackgroundResource(R.drawable.decorar)
            binding.textView3.setBackgroundResource(R.drawable.decorar)
            binding.textView4.setBackgroundResource(R.drawable.decorar)
            binding.textView5.setBackgroundResource(R.drawable.decorar)
            var size=lista.size
            var numale= Random
            r=Random.nextInt(1,size)
            var listaRespuestas :MutableList<String> = mutableListOf(lista[r].a,lista[r].b,lista[r].c,lista[r].d)
            var numalea1 = numale.nextInt(0..3)

            var numalea2 = Random.nextInt(0,3)
            while (numalea1==numalea2) {
                numalea2 = numale.nextInt(0..3)
            }

            var numalea3 = Random.nextInt(0,3)
            while (numalea3==numalea1 || numalea3==numalea2){
                numalea3 = numale.nextInt(0..3)
            }

            var numalea4 = Random.nextInt(0,3)
            while (numalea4==numalea1 || numalea4==numalea2 || numalea4==numalea3){
                numalea4 = numale.nextInt(0..3)
            }
            binding.textView.text=lista[r].pregunta
            binding.textView2.text=listaRespuestas[numalea1]
            binding.textView3.text=listaRespuestas[numalea2]
            binding.textView4.text=listaRespuestas[numalea4]
            binding.textView5.text=listaRespuestas[numalea3]
            rpt=lista[r].respuesta
        },2000)
    }
    fun validar(){
        binding.textView2.setOnClickListener {
            if(binding.textView2.text==rpt)
            {
                premio+=100000
                binding.textAcu.text=premio.toString()
                lista.removeAt(r)
                jugar()
                binding.textView2.setBackgroundColor(Color.parseColor("#00ff00"))
            }
            else
            {
                Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show()
                binding.textView2.setBackgroundColor(Color.parseColor("#ff0000"))
                Handler().postDelayed({
                    startActivity(Intent(this@PlayActivity,OptionesActivity::class.java))
                },1000)
            }
        }
        binding.textView3.setOnClickListener {
            if(binding.textView3.text==rpt)
            {
                binding.textView3.setBackgroundColor(Color.parseColor("#ff0000"))
                premio+=100000
                binding.textAcu.text=premio.toString()
                lista.removeAt(r)
                jugar()
            }
            else
            {
                Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show()
                binding.textView3.setBackgroundColor(Color.parseColor("#ff0000"))
                Handler().postDelayed({
                    startActivity(Intent(this@PlayActivity,OptionesActivity::class.java))
                },1000)
            }
        }
        binding.textView4.setOnClickListener {
            if(binding.textView4.text==rpt)
            {
                premio+=100000
                binding.textAcu.text=premio.toString()
                lista.removeAt(r)
                jugar()
                binding.textView4.setBackgroundColor(Color.parseColor("#00ff00"))
            }
            else
            {
                Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show()
                binding.textView4.setBackgroundColor(Color.parseColor("#ff0000"))
                Handler().postDelayed({
                    startActivity(Intent(this@PlayActivity,OptionesActivity::class.java))
                },1000)
            }
        }
        binding.textView5.setOnClickListener {
            if(binding.textView5.text==rpt)
            {
                premio+=100000
                binding.textAcu.text=premio.toString()
                lista.removeAt(r)
                binding.textView5.setBackgroundColor(Color.parseColor("#00ff00"))
                jugar()

            }
            else
            {
                binding.textView5.setBackgroundColor(Color.parseColor("#ff0000"))
                Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    startActivity(Intent(this@PlayActivity,OptionesActivity::class.java))
                },1000)
            }
        }
    }
}