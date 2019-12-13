package com.example.databinding.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.databinding.ProfileLiveDataViewModel
import com.example.databinding.ProfileObservableViewModel
import com.example.databinding.R
import com.example.databinding.databinding.ActivityViewModelBinding

import kotlinx.android.synthetic.main.activity_view_model.*

class ViewModelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

//        val viewModel = ViewModelProviders.of(this).get(ProfileLiveDataViewModel::class.java)
        val viewModel = ViewModelProviders.of(this).get(ProfileObservableViewModel::class.java)

        val binding: ActivityViewModelBinding =
            DataBindingUtil.setContentView(this,
                R.layout.activity_view_model
            )
        binding.viewmodel = viewModel
        // viewmodel中有livedata，livedata需要绑定activity的生命周期
        binding.lifecycleOwner = this
    }

}
