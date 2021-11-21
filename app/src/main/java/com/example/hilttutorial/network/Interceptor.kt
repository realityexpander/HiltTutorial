package com.example.hilttutorial.network

interface Interceptor {
    fun log(message: String)
    fun modifyArgs(args: String): String
}