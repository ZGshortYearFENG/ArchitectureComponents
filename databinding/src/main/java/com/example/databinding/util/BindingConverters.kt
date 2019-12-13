package com.example.databinding.util

import android.view.View
import androidx.databinding.BindingConversion

object ConverterUtil {
    @JvmStatic fun isZero(number: Int): Boolean {
        return number == 0
    }
}


object BindingConverters{

    @BindingConversion
    @JvmStatic fun booleanToVisibility(isNotVisible: Boolean): Int {
        return if (isNotVisible) View.GONE else View.VISIBLE
    }
}