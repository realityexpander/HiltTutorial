package com.example.hilttutorial.database

import android.content.Context
import android.util.Logg
import com.example.hilttutorial.TAG
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject


// Constructor Injector
class DatabaseAdapter @Inject constructor(@ActivityContext val context: Context,
                                          val databaseService: DatabaseService){
    fun log(message: String) {
        Logg.d(TAG, "DatabaseAdapter log: $message")
        databaseService.log(message)
    }
}