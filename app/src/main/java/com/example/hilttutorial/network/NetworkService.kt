package com.example.hilttutorial.network

import android.util.Log
import com.example.hilttutorial.TAG

class NetworkService private constructor(builder: Builder) {

    val protocol: String?
    val host: String?
    val path: String?
    private val interceptor: Interceptor?

    init {
        protocol = builder.protocol
        host = builder.host
        path = builder.path
        interceptor = builder.interceptor
    }

    fun performNetworkCall(query: String) {
        interceptor?.log("Interceptor before: args: $query")
        val newQuery = interceptor?.modifyArgs(query) ?: query
        Log.d(TAG, "performNetworkCall: " +
                "${this.protocol}://" +
                "${this.host}/" +
                "${this.path}?" +
                newQuery
        )
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