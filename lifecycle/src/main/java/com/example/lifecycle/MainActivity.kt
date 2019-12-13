package com.example.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle

class MainActivity : AppCompatActivity() {

    private lateinit var lifeCycleObserver: MyLifeCycleObserver
    private lateinit var lifeCycleOwner: MyLifeCycleOwner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifeCycleObserver = MyLifeCycleObserver()
//        lifeCycleOwner = MyLifeCycleOwner()
//        lifeCycleOwner.markState(Lifecycle.State.CREATED)

        // 通过调用Lifecycle类的addObserver（）方法并传递观察者的实例来添加观察者
        lifecycle.addObserver(lifeCycleObserver)
    }

//    override fun onStart() {
//        super.onStart()
//        lifeCycleOwner.markState(Lifecycle.State.STARTED)
//    }
//
//
//    override fun onResume() {
//        super.onResume()
//        lifeCycleOwner.markState(Lifecycle.State.RESUMED)
//    }
}
