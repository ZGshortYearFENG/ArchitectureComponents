package com.example.databinding

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class ProfileLiveDataViewModel : ViewModel() {
    private val _name = MutableLiveData("Ada")
    private val _lastName = MutableLiveData("Lovelace")
    private val _likes =  MutableLiveData(0)

    val name: LiveData<String> = _name
    val lastName: LiveData<String> = _lastName
    val likes: LiveData<Int> = _likes

    // popularity is exposed as LiveData using a Transformation instead of a @Bindable property.
    val popularity: LiveData<Popularity> = Transformations.map(_likes) {
        when {
            it > 9 -> Popularity.STAR
            it > 4 -> Popularity.POPULAR
            else -> Popularity.NORMAL
        }
    }

    fun onLike() {
        Log.d("fzj","onlike "+_likes.value)
        _likes.value = (_likes.value ?: 0) + 1
    }
}

/**
 * As an alternative to LiveData, you can use Observable Fields and binding properties.
 *
 * `Popularity` is exposed here as a `@Bindable` property so it's necessary to call
 * `notifyPropertyChanged` when any of the dependent properties change (`likes` in this case).
 */
class ProfileObservableViewModel : ObservableViewModel() {
    val name = ObservableField("Ada")
    val lastName = ObservableField("Lovelace")
    val likes =  ObservableInt(0)

    fun onLike() {
        likes.increment()
        // You control when the @Bindable properties are updated using `notifyPropertyChanged()`.
        notifyPropertyChanged(BR.popularity)
    }

    @Bindable
    fun getPopularity(): Popularity {
        return likes.get().let {
            when {
                it > 9 -> Popularity.STAR
                it > 4 -> Popularity.POPULAR
                else -> Popularity.NORMAL
            }
        }
    }
}

enum class Popularity {
    NORMAL,
    POPULAR,
    STAR
}

fun ObservableInt.increment() {
    set(get() + 1)
}