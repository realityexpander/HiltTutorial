package com.example.hilttutorial.database

import android.util.Log
import com.example.hilttutorial.TAG
import javax.inject.Inject

class DatabaseAdapter @Inject constructor(var databaseService: DatabaseService){
    fun log(message: String) {
        Log.d(TAG, "DatabaseAdapter log: $message")
        databaseService.log(message)
    }
}