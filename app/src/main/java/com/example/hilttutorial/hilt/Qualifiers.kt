package com.example.hilttutorial.hilt

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkService1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkService2