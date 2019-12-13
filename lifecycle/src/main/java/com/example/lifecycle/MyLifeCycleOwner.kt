package com.example.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

// LifecycleOwner是一个单一方法接口，表示该类具有生命周期。它有一个方法getLifecycle（），必须由该类实现。
class MyLifeCycleOwner : LifecycleOwner {

    private val TAG = "MyLifeCycleOwner"

    private lateinit var lifeCycleRegistry: LifecycleRegistry

    constructor() {
        lifeCycleRegistry = LifecycleRegistry(this)
    }

    override fun getLifecycle(): Lifecycle {
        return lifeCycleRegistry;
    }

    fun markState(state: Lifecycle.State) {
        lifeCycleRegistry.currentState = state
    }
}