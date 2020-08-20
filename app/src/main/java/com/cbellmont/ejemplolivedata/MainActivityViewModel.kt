package com.cbellmont.ejemplolivedata

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class MainActivityViewModel : ViewModel() {

    private lateinit var personajes : LiveData<List<Personaje>>

    fun loadPersonajes(context: Context) : LiveData<List<Personaje>>{
        personajes = App.getDatabase(context).PersonajesDao().getAllLive()
        return personajes
    }

    fun addNewPersonajeAleatorio(context: Context){
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO){
                val nombre = "Orco feo " + Random.nextInt()
                App.getDatabase(context).PersonajesDao().insert(Personaje(nombre, "Orco"))
            }
        }
    }

}