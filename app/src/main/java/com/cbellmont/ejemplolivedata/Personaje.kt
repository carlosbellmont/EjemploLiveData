package com.cbellmont.ejemplolivedata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Personaje(val nombre : String, val raza : String) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}