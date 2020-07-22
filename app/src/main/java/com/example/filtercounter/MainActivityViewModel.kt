package com.example.filtercounter

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    var filterWaterCount = ObservableFloatString()
    var filter = object : ObservableField<Filter>() {
        override fun set(value: Filter?) {
            filterWaterCount.set(value!!.passed,1)
            super.set(value)
        }
    }

    fun fillFilter() {
        filter.get()!!.passed += filter.get()!!.oneFill
        filterWaterCount.set(filter.get()!!.passed,1)
        filter.notifyPropertyChanged(0)
        filterWaterCount.notifyPropertyChanged(0)
    }
}

class ObservableFloatString : ObservableField<String>() {
    fun set(value: Float, charCount: Int) {
        super.set(String.format("%." + charCount.toString() + 'f', value))
    }
}

