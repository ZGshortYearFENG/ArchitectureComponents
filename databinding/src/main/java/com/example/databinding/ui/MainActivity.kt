package com.example.databinding.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databinding.R
import com.example.databinding.data.User
import com.example.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 两种实例化databinding方法
//        var databinding = ActivityMainBinding.inflate(layoutInflater)
        var databinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        //
        databinding.observableFieldsActivityButton.setOnClickListener {
            startActivity(Intent(this, ObservableFieldActivity::class.java))
        }
        databinding.viewmodelActivityButton.setOnClickListener {
            startActivity(Intent(this, ViewModelActivity::class.java))
        }

        // data绑定
        databinding.user = User("firstNameeeee", "lastNamennnnn")
    }
}
