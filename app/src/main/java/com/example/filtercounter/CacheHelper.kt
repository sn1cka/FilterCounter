package com.example.filtercounter

import android.content.Context

object CacheHelper {
    fun isFilterSet(context: Context): Boolean {
        val preferences = context.getSharedPreferences("Filter", Context.MODE_PRIVATE)
        return preferences.getBoolean("isFilterSet", false)
    }

    private fun isFilterSet(context: Context, b: Boolean) {
        val preferences = context.getSharedPreferences("Filter",Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean("isFilterSet", b)
        editor.apply()
    }

    fun saveFilter(context: Context, filter: Filter) {
        val preferences = context.getSharedPreferences("Filter", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt("FilterResource", filter.resource)
        editor.putFloat("FilterPassed", filter.passed)
        editor.putFloat("FilterOneFill", filter.oneFill)
        editor.apply()
        isFilterSet(context, true)
    }

    fun getFilter(context: Context): Filter {
        val pref = context.getSharedPreferences("Filter",Context.MODE_PRIVATE)
        return Filter(
            pref.getInt("FilterResource", 300),
            pref.getFloat("FilterOneFill", 2.2f),
            pref.getFloat("FilterPassed", 0.0f)
        )
    }
}