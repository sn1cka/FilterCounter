package com.example.filtercounter

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    var filterPassed = ObservableFloatString()
    var filter = object : ObservableField<Filter>() {
        override fun set(value: Filter?) {
            filterPassed.set(value!!.passed,2)
            super.set(value)
        }
    }

    fun fillFilter() {
        filter.get()!!.passed += filter.get()!!.oneFill
        filterPassed.set(filter.get()!!.passed,2)
        filter.notifyPropertyChanged(0)
        filterPassed.notifyPropertyChanged(0)
    }
}

class ObservableFloatString : ObservableField<String>() {
    fun set(value: Float, charCount: Int) {
        super.set(String.format("%." + charCount.toString() + 'f', value))
    }
}

