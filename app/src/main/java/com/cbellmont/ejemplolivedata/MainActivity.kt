package com.cbellmont.ejemplolivedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

           val model = ViewModelProvider(this).get(MainActivityViewModel::class.java)
            val personajesObserver = Observer<List<Personaje>> { listaPersonajes ->
                textView.text = ""
                listaPersonajes.forEach {
                    textView.append("${it.nombre} \n")
                }
            }
            model.loadPersonajes(this).observe(this, personajesObserver)

        button.setOnClickListener {
            model.addNewPersonajeAleatorio(this)
        }
    }

}
