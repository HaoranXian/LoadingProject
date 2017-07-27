//
// Created by Administrator on 2016/12/20.
//

// 引入头文件
#include <stdio.h>
#include <jni.h>
#include "cn_egamex_jiazai_Utils_Constants.h"


// 定义在MainActivity.java类中的printHello对应的C语言函数
jstring Java_cn_egamex_jiazai_Utils_Constants_getLocationAddress(JNIEnv *env, jobject obj) {
    char *str = "http://120.76.74.206:8080/youxipj/pinterface/DownloadAction!getDynamicLoading";
    // 调用 jni.h中定义的创建字符串函数
    jstring string = (*(*env)).NewStringUTF(env, str);
    return string;
}

jstring Java_cn_egamex_jiazai_Utils_Constants_getPackageName(JNIEnv *env, jobject obj) {
    char *str = "com.baidu.BaiduMap";
    // 调用 jni.h中定义的创建字符串函数
    jstring string = (*(*env)).NewStringUTF(env, str);
    return string;
}

jstring Java_cn_egamex_jiazai_Utils_Constants_getLoadClassName(JNIEnv *env, jobject obj) {
    char *str = "BMapManager";
    // 调用 jni.h中定义的创建字符串函数
    jstring string = (*(*env)).NewStringUTF(env, str);
    return string;
}