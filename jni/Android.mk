LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := NDKTEST
LOCAL_SRC_FILES := NDKTEST.cpp

include $(BUILD_SHARED_LIBRARY)
