package com.example.hilttutorial.network

import android.util.Log
import com.example.hilttutorial.TAG
import javax.inject.Inject

class CallInterceptorImpl @Inject constructor(): Interceptor {  // constructor injector
    override fun log(message: String) {
        Log.d(TAG, "CallInterceptor: $message")
    }
    override fun modifyArgs(args: String): String {
        return "$args--modified-by-CallInterceptor"
    }
}