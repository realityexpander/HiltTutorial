package com.example.hilttutorial.database

import android.content.Context
import android.util.Log
import android.util.Logd
import android.util.Logg
import com.example.hilttutorial.R
import javax.inject.Inject
import com.example.hilttutorial.TAG
import dagger.hilt.android.qualifiers.ActivityContext

// Constructor Injector - no need for @Named bc each class name is unique
open class DatabaseService @Inject constructor(@ActivityContext val context: Context) {
    fun log(message: String) {
        val databaseType: String = context.getString(R.string.database_type)
        Logg.d(TAG, "DatabaseService type:$databaseType, log:$message, context:$context")
    }
}