package com.example.hilttutorial.network

import android.util.Log
import com.example.hilttutorial.TAG
import javax.inject.Inject

class ResponseInterceptorImpl @Inject constructor(): Interceptor { // constructor injector
    override fun log(message: String) {
        Log.d(TAG, "ResponseInterceptor: $message")
    }

    override fun modifyArgs(args: String): String {
        return "$args--modified-by-responseInterceptor"
    }
}