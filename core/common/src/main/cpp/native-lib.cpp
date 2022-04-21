//
// Created by Stefanus Ayudha on 18/04/2022.
//

#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_iddevops_core_common_data_Secured_getBaseUrl(JNIEnv *env, jclass clazz) {
    return env->NewStringUTF("https://jsonplaceholder.typicode.com/");
}
