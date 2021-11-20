package com.example.hilttutorial.database

import android.util.Log
import javax.inject.Inject
import com.example.hilttutorial.TAG

class DatabaseService @Inject constructor() {
    fun log(message: String) {
        Log.d(TAG, "log: $message")
    }
}