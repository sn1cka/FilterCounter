package com.example.filtercounter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.filtercounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!CacheHelper.isFilterSet(this)) {
            startActivity(Intent(this, NewFilterActivity::class.java))
            finish()
        } else initUi()
    }

    private fun initUi() {
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.mFilter = CacheHelper.getFilter(this)

    }
}
