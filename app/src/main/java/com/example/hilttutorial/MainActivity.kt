package com.example.hilttutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.hilttutorial.database.DatabaseAdapter
import com.example.hilttutorial.database.DatabaseService
import com.example.hilttutorial.databinding.ActivityMainBinding
import com.example.hilttutorial.hilt.NetworkService1
import com.example.hilttutorial.hilt.NetworkService2
import com.example.hilttutorial.network.NetworkAdapter
import com.example.hilttutorial.network.NetworkService
import com.example.hilttutorial.stats.StatsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // Field injection
    @Inject
    lateinit var databaseAdapter: DatabaseAdapter // Constructor Injection

    @Inject
    @Named("MyNetworkAdapter")
    lateinit var myNetworkAdapter: NetworkAdapter // Interface Injection

    @Inject
    @Named("OtherNetworkAdapter")
    lateinit var otherNetworkAdapter: NetworkAdapter // Interface Injection

    @Inject
//    @Named("NetworkService1")
    @NetworkService1
    lateinit var networkService: NetworkService  // Module+Provides Injection

    private val statsViewModel: StatsViewModel by viewModels()  // Injected here automatically

    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Log.d(TAG, "MainActivity DatabaseAdapter: $databaseAdapter ")
        databaseAdapter.log("Hello hilt")

        myNetworkAdapter.log("Interface binding for NetworkAdapter=MyNetworkAdapter")
        otherNetworkAdapter.log("Interface binding for NetworkAdapter=OtherNetworkAdapter")

        networkService.performNetworkCall("user=chrisa-Service-NetworkService")

        // Use the viewModel
        statsViewModel.statsLiveData.observe(this) { stats ->
            ("${stats}s : " +
                    statsViewModel.stateInt.value).also { bind.tvText.text = it }
        }
    }

    override fun onPause() {
        super.onPause()
        statsViewModel.stopStatsCollection()
    }

    override fun onResume() {
        super.onResume()

        statsViewModel.loadInitialState()
        statsViewModel.i = statsViewModel.stateInt.value!!
        println("$TAG onResume stateInt=${statsViewModel.stateInt.value!!}")
        statsViewModel.startStatsCollection()
    }

    // Method injection - called once hilt has the object in the graph
    @Inject
    fun directToNetworkService(/*@Named("NetworkService2")*/ @NetworkService2 networkService: NetworkService) { // Method injection via @Provides
        networkService.performNetworkCall("user=from-method-injection")
    }

    // Method injection - called once hilt has the object in the graph
    @Inject
    fun directToDatabase(databaseService: DatabaseService) { // Method injection via constructor injection
        databaseService.log("Method Injection")
    }
}