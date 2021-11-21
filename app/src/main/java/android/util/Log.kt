package android.util

//object Log {  // Only works when in testing, crashes during normal app run
////    @JvmField
////    var INSTANCE: Log = this
//    fun d(tag: String, msg: String): Int {
//        println("$tag: $msg")
//        return 0
//    }
//}

fun Logd(tag: String, msg: String): Int  {
    println("$tag: $msg")
    return 0
}

object Logg {
    fun d(tag: String, msg: String): Int {
        println("$tag: $msg")
        return 0
    }
}
