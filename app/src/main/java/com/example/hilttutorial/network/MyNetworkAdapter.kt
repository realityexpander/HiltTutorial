package com.example.hilttutorial.network

import android.util.Log
import com.example.hilttutorial.TAG
import javax.inject.Inject

class MyNetworkAdapter @Inject constructor(): NetworkAdapter {
    override fun log(message: String) {
        Log.d(TAG, "MyNetworkAdapter: $message")
    }
}

class OtherNetworkAdapter @Inject constructor(): NetworkAdapter {
    override fun log(message: String) {
        Log.d(TAG, "OtherNetworkAdapter: $message")
    }
}