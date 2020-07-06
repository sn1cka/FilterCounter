package com.example.filtercounter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_new_filter.*
import java.util.Arrays.stream
import java.util.stream.Collectors

class NewFilterActivity : AppCompatActivity(), NumberPicker.OnValueChangeListener, View.OnClickListener {
    private var oneFillList = arrayOf(1.5f, 1.6f, 1.7f, 1.8f, 1.9f, 2.0f, 2.1f, 2.2f, 2.3f, 2.4f, 2.5f, 3.0f, 3.5f, 4.0f)
    private var resourceList = arrayOf(100, 200, 300, 400, 500, 1000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_filter)

        initPickers()
        okButton.setOnClickListener(this)
    }


    private fun initPickers() {
        oneFillContainer.isVisible = false

        resourcePicker.minValue = 0
        resourcePicker.maxValue = resourceList.size-1
        resourcePicker.wrapSelectorWheel = false
        resourcePicker.displayedValues = stream(resourceList).map { "$it л" }.collect(Collectors.toList()).toTypedArray()
        resourcePicker.setOnValueChangedListener(this)


        oneFillPicker.minValue = 0
        oneFillPicker.maxValue = oneFillList.size - 1
        oneFillPicker.wrapSelectorWheel = false
        oneFillPicker.displayedValues = stream(oneFillList).map { "$it л" }.collect(Collectors.toList()).toTypedArray()
        oneFillPicker.setOnValueChangedListener(this)
    }

    override fun onValueChange(p0: NumberPicker?, p1: Int, p2: Int) {
        val v = this@NewFilterActivity.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        v.vibrate(VibrationEffect.createOneShot(20,5))
    }

    override fun onClick(p0: View?) {
        if (resourceContainer.isVisible) {
            resourceContainer.isVisible = false
            oneFillContainer.isVisible = true
        } else {
            CacheHelper.saveFilter(this, Filter(resourceList[resourcePicker.value], oneFillList[oneFillPicker.value]))
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }
    }

    override fun onBackPressed() {
        if (oneFillContainer.isVisible) {
            oneFillContainer.isVisible = false
            resourceContainer.isVisible = true
        } else super.onBackPressed()
    }

}
