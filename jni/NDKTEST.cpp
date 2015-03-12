#include <jni.h>
#include <stdio.h>
#include "com_example_ndktest_GetInt.h"
#include  "com_example_ndktest_GetString.h"
#include "Hello.h"

JNIEXPORT jstring JNICALL Java_com_example_ndktest_GetString_getstr(JNIEnv *env,
		jclass) {

	return env->NewStringUTF("static method..");

}

/*
 * Class:     com_example_ndktest_GetString
 * Method:    getStr
 * Signature: ()Ljava/lang/String;
 */JNIEXPORT jstring JNICALL Java_com_example_ndktest_GetString_getStr(
		JNIEnv *env, jobject) {

	return env->NewStringUTF("not static method");

}

/*
 * Class:     com_example_ndktest_GetString
 * Method:    add
 * Signature: (II)I
 */JNIEXPORT jint JNICALL Java_com_example_ndktest_GetString_add(JNIEnv *,
		jobject, jint a, jint b) {
	return a + b;
}

JNIEXPORT jint JNICALL Java_com_example_ndktest_GetInt_getint(JNIEnv *,
		jclass) {

	return 9;
}

JNIEXPORT jstring JNICALL Java_com_example_ndktest_GetString_getWords(
		JNIEnv *env, jclass) {

	Hello h;
	return env->NewStringUTF(h.getWords());
}
