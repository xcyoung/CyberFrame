package com.xcyoung.cyberframe.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlin.reflect.KProperty

/**
 * @author ChorYeung
 * @since 2019/1/12
 */
class ExtrasDelegate<out T>(private val extraName:String,private val defalutValue: T){
    private var extra:T?=null

    operator fun getValue(thisRef: AppCompatActivity,propety:KProperty<*>):T{
        extra = getExtra(extra,extraName,thisRef)
        return extra ?: defalutValue        //?:若extra为null return defalutValue
    }

    operator fun getValue(thisRef: Fragment,propety: KProperty<*>):T{
        extra = getExtra(extra,extraName,thisRef)
        return extra ?: defalutValue        //?:若extra为null return defalutValue
    }
}

fun <T> extraDelegate(extraName: String,defalutValue:T) = ExtrasDelegate(extraName,defalutValue)

fun <T> extraDelegate(extraName: String) = extraDelegate(extraName,null)

private fun <T> getExtra(oldExtra:T?,extraName: String,thisRef: AppCompatActivity):T? =
        oldExtra ?: thisRef.intent?.extras?.get(extraName) as T?

private fun <T> getExtra(oldExtra:T?,extraName: String,thisRef: Fragment):T? =
        oldExtra ?: thisRef.arguments?.get(extraName) as T?