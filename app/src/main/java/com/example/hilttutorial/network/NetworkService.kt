package com.example.hilttutorial.network

import android.util.Log
import com.example.hilttutorial.TAG

class NetworkService private constructor(builder: Builder) {

    val protocol: String?
    val host: String?
    val path: String?
    val interceptor: Interceptor?

    init {
        protocol = builder.protocol
        host = builder.host
        path = builder.path
        interceptor = builder.interceptor
    }

    fun perfomNetworkCall() {
        interceptor?.log("Call Performed")
        Log.d(TAG, "perfomNetworkCall: $this")
    }

    class Builder {
        var protocol: String? = null
            private set
        var host: String? = null
            private set
        var path: String? = null
            private set
        var interceptor: Interceptor? = null
            private set

        fun protocol(protocol: String) = apply { this.protocol = protocol }
        fun host(host: String) = apply { this.host = host }
        fun path(path: String) = apply { this.path = path }
        fun interceptor(interceptor: Interceptor) = apply { this.interceptor = interceptor }
        fun build() = NetworkService(this)
    }
}