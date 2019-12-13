package com.example.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var model: NameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // viewmodel
        model = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(NameViewModel::class.java)

        model.currentName.observe(this, Observer { textview.text = it.toString() })
        button.setOnClickListener { model.currentName.value = model.currentName.value?: 0 + 1}
//        Transformations.map()
//        Transformations.switchMap()
        /***
         *
         */
    }

    class NameViewModel : ViewModel() {
        val currentName: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    }
}
