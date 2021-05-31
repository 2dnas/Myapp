package com.example.products.db

import android.app.Application
import androidx.room.Room

class App : Application(){

    lateinit var db : AppDatabase

    companion object {
        lateinit var instance : App
        private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "AppDatabase"
        ).allowMainThreadQueries().build()

    }
}
