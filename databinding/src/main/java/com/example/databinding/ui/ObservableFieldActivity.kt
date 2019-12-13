package com.example.databinding.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableInt
import com.example.databinding.data.ObservableFieldProfile
import com.example.databinding.R
import com.example.databinding.databinding.ActivityObservableFieldBinding
import com.example.databinding.increment

class ObservableFieldActivity : AppCompatActivity() {

    private var observableFieldProfile =
        ObservableFieldProfile("Ada", "Lovelace", ObservableInt(0))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_observable_field)

        val binding: ActivityObservableFieldBinding =
            DataBindingUtil.setContentView(this,
                R.layout.activity_observable_field
            )
        binding.user = observableFieldProfile
    }

    fun onLike(view: View) {
//        observableFieldProfile.likes.set(observableFieldProfile.likes.get() + 1)
        observableFieldProfile.likes.increment()
    }
}

