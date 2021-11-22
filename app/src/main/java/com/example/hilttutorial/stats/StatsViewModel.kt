package com.example.hilttutorial.stats

import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.hilttutorial.TAG
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(private val state: SavedStateHandle): ViewModel() {
    val statsLiveData = MutableLiveData<Int>()
    var i = 0
    var runnable: Runnable? = null
    val h = Handler(Looper.getMainLooper())

    val stateInt: MutableLiveData<Int> = state.getLiveData("testInt")

    fun startStatsCollection() {
        println("$TAG StatsViewModel start $i")
        statsLiveData.value = state["testInt"] // Update the LiveData immediately

        runnable = Runnable {
            // statsLiveData.value = ++i // Updates livedata Immediately
            statsLiveData.postValue(++i) // Update the livedata async
            state["testInt"] = i // Update the save state
            h.postDelayed(runnable!!, 1000)
        }
        h.postDelayed(runnable!!, 1000)
    }

    fun stopStatsCollection() {
        runnable?.let { h.removeCallbacks(it) }
        state["testInt"] = i
        println("$TAG StatsViewModel stop ${(stateInt.value)}")
    }

    fun loadInitialState() {
        if(stateInt.value == null) {
            state["testInt"] = 0
        }
    }
}
