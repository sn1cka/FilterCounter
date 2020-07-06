package com.example.filtercounter

object DataBindingViewsUtil{
   open fun stringValue(obj:Any): String {
        return obj.toString()
    }
    fun roundedString(number:Float, charCount:Int): String {
        val count = '.'+charCount.toString()+'f'
        return String.format(count,number)
    }
}