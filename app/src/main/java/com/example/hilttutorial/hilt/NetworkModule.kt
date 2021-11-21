package com.example.hilttutorial.hilt

import android.app.Application
import android.content.Context
import com.example.hilttutorial.HiltTutorialApplication
import com.example.hilttutorial.R
import com.example.hilttutorial.network.*
import com.example.hilttutorial.network.NetworkService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(ActivityComponent::class)
abstract class NetworkModule { // must be abstract to supply interfaces

    // this tells Hilt that when an @Named("MyNetworkAdapter") NetworkAdapter is needed, use MyNetworkAdapter
    //   In this case, an implementation of MyNetworkAdapter will be injected
    @Binds
    @Named("MyNetworkAdapter")
    abstract fun bindMyNetworkAdapterImpl(networkAdapterImp: MyNetworkAdapter): NetworkAdapter

    // this tells Hilt that when an @Named("OtherNetworkAdapter") NetworkAdapter is needed, use OtherNetworkAdapter
    //   In this case, an implementation of OtherNetworkAdapter will be injected
    @Binds
    @Named("OtherNetworkAdapter")
    abstract fun bindOtherNetworkAdapterImpl(networkAdapterImp: OtherNetworkAdapter): NetworkAdapter
}

@Singleton
class ProtocolUsed @Inject constructor(@ApplicationContext val context: Context) {
    var protocol: String = "http-"+this.toString().substringAfterLast(".") // test to see if @Singleton returns same or different objects
    val protocolStringResource: String = context.getString(R.string.http_protocol)
}

@Module
@InstallIn(SingletonComponent::class) // Is this the same as the Application class?
abstract class InterceptorModule { // must be abstract to supply interfaces

    @Binds
    @Named("CallInterceptor")
    abstract fun bindCallInterceptorImpl(callInterceptorImpl: CallInterceptorImpl): Interceptor

    @Binds
    @Named("ResponseInterceptor")
    abstract fun bindResponseInterceptorImpl(responseInterceptorImpl: ResponseInterceptorImpl): Interceptor
}

@Module
//@InstallIn(SingletonComponent::class, ActivityComponent::class) // Can install in multiple places
@InstallIn(SingletonComponent::class)
class NetworkService {  // must not be abstract to supply classes
    @Singleton
    //@ActivityScoped // Cant use @ActivityScoped here as it has a shorter scope(lifetime) than the Singleton for the interceptor
    @Provides
//    @Named("NetworkService1") // using a built-in @Named qualifier
    @NetworkService1 // using our custom Qualifier
    fun provideNetworkService1(@Named("CallInterceptor")
                               callInterceptor: Interceptor,
                               protocolUsed: ProtocolUsed): NetworkService {
        return  NetworkService.Builder()
//                .protocol("https") // instead of instantiating directly, we let Hilt do it (next line)
//                .protocol(ProtocolUsed(context).protocol) // // instead of instantiating directly, we let Hilt do it (next line)
                .protocol(protocolUsed.protocol)
                .host("google.com")
                .path("get")
//                .interceptor(CallInterceptorImpl()) // instead of instantiating directly, we let Hilt do it (next line)
                .interceptor(callInterceptor)
                .build()
    }

    @Singleton

    @Provides
    @NetworkService2 // using our custom Qualifier
    fun provideNetworkService2(@Named("ResponseInterceptor")
                               responseInterceptor: Interceptor,
                               protocolUsed: ProtocolUsed): NetworkService {
        return  NetworkService.Builder()
            .protocol(protocolUsed.protocol) //.protocolStringResource)
            .host("facebook.com")
            .path("users")
            .interceptor(responseInterceptor)
            .build()
    }
}