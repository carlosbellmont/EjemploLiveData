package com.cbellmont.ejemplolivedata

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class App : Application() {

    companion object {
        private var db : AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            db?.let { return it }

            db = Room.databaseBuilder(context, AppDatabase::class.java, "database-name")
                .addCallback(getCallback())
                .build()
            return db as AppDatabase
        }

        private fun getCallback(): RoomDatabase.Callback {
            return object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    CoroutineScope(Dispatchers.IO).launch {
                        withContext(Dispatchers.IO) {
                            val personajes : List<Personaje> = listOf(
                                Personaje("Aragorn", "Humano") ,
                                Personaje("Gandalf", "Mago"),
                                Personaje("Boromir", "Humano"),
                                Personaje("Legolas", "Elfo"),
                                Personaje("Orco Feo", "Orco"),
                                Personaje("Smagu", "Dragon")
                            )
                            App.db?.PersonajesDao()?.insertAll(personajes)
                        }
                    }
                }
                override fun onOpen(db: SupportSQLiteDatabase) {
                }
            }
        }
    }
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, AppDatabase::class.java, "database-name")
            .addCallback(getCallback())
            .build()
    }

}