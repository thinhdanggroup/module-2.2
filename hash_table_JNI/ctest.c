#include <jni.h>
#include <stdio.h>
#include "HelloWorld.h"

JNIEXPORT void JNICALL Java_HelloWorld_helloFromC
  (JNIEnv * env, jobject jobj)
{
    printf("Hello from C!\n");
}