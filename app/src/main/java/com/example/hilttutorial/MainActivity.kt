package com.example.hilttutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.hilttutorial.database.DatabaseAdapter
import com.example.hilttutorial.database.DatabaseService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // Field injection
    @Inject
    lateinit var databaseAdapter: DatabaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "MainActivity DatabaseAdapter: $databaseAdapter ")
        databaseAdapter.log("Hello hilt")
    }

    // Method injection - called once hilt has the object in the graph
    @Inject
    fun directToDatabase(databaseService: DatabaseService) {
        databaseService.log("Method Injection")
    }
}