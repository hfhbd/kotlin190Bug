package main

import jni.*
import kotlinx.cinterop.*

@CName("Java_main_HELLO_invoke")
fun hello(env: CPointer<JNIEnvVar>, obj: jobject, from: jstring, repeat: jint): Int {
    val from = env.getString(from)
    repeat(repeat) {
        println("Hello World from ${"JNI: $from"}.")
    }
    return repeat
}

private fun CPointer<JNIEnvVar>.getString(string: jstring): String {
    val getStringUTFChars = pointed.pointed!!.GetStringUTFChars!!
    val cString = getStringUTFChars(this, string, null)!!
    val kString = cString.toKString()
    pointed.pointed!!.ReleaseStringUTFChars!!(this, string, cString)
    return kString
}
