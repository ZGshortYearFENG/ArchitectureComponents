package com.example.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyLifeCycleObserver : LifecycleObserver {

    private val TAG = "MyLifecycleObserver"

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun created(){
        Log.d(TAG, "Created called")
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun started(){
        Log.d(TAG, "Started called")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resumed(){
        Log.d(TAG, "Resume Called")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun paused(){
        Log.d(TAG, "Pause called")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopped(){
        Log.d(TAG, "Stop Called")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroyed(){
        Log.d(TAG, "Destroy Called")
    }
}