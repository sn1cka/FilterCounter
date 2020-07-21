package com.example.filtercounter

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.filtercounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!CacheHelper.isFilterSet(this)) {
            startActivity(Intent(this, NewFilterActivity::class.java))
            finish()
        } else initViewModel()
    }

    private fun initViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = MainActivityViewModel()
        binding.viewModel!!.filter.set(CacheHelper.getFilter(this))
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    override fun onClick(view: View) {
        binding.viewModel!!.fillFilter()
        CacheHelper.saveFilter(this, binding.viewModel!!.filter.get()!!)
        val v = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        v.vibrate(VibrationEffect.createOneShot(30, VibrationEffect.EFFECT_TICK))
    }

}
