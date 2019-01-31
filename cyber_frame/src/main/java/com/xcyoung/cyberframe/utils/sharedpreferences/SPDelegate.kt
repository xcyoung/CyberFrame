package com.xcyoung.cyberframe.utils.sharedpreferences

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.google.gson.reflect.TypeToken
import com.xcyoung.cyberframe.utils.GsonHandler
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author ChorYeung
 * @since 2019/1/12
 */
private inline fun <T> SharedPreferences.delegate(
        key:String,
        defaultValue:T,
        crossinline getter: SharedPreferences.(String,T) -> T,
        crossinline setter: Editor.(String,T) -> Editor
):ReadWriteProperty<Any,T> = object :ReadWriteProperty<Any,T>{
    override fun getValue(thisRef: Any, property: KProperty<*>): T = getter(key,defaultValue)

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) = edit().setter(key, value).apply()
}

inline fun <reified T> SharedPreferences.gson(key:String, defaultValue: T):ReadWriteProperty<Any,T> = object :ReadWriteProperty<Any,T>{
    val gson = GsonHandler.gson
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        val value = getString(key,"")
        return if(value!!.isBlank()) defaultValue else gson.fromJson(value,T::class.java)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        edit().putString(key,gson.toJson(value)).apply()
    }
}

inline fun <reified T> SharedPreferences.gsonList(key:String):ReadWriteProperty<Any,List<T>> = object :ReadWriteProperty<Any,List<T>>{
    val gson = GsonHandler.gson
    override fun getValue(thisRef: Any, property: KProperty<*>): List<T> {
        val value = getString(key,"")
        return if(value!!.isBlank()) emptyList() else gson.fromJson<List<T>>(value,object :TypeToken<List<T>>(){}.type)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: List<T>) {
        edit().putString(key,gson.toJson(value)).apply()
    }
}

fun SharedPreferences.int(key:String):ReadWriteProperty<Any,Int> =
        delegate(key,0,SharedPreferences::getInt, Editor::putInt)

fun SharedPreferences.float(key:String):ReadWriteProperty<Any,Float> =
        delegate(key,0f,SharedPreferences::getFloat, Editor::putFloat)

fun SharedPreferences.long(key:String):ReadWriteProperty<Any,Long> =
        delegate(key,0L,SharedPreferences::getLong, Editor::putLong)

fun SharedPreferences.boolean(key:String):ReadWriteProperty<Any,Boolean> =
        delegate(key,false,SharedPreferences::getBoolean, Editor::putBoolean)

fun SharedPreferences.string(key:String):ReadWriteProperty<Any,String> =
        delegate(key,"",SharedPreferences::getString, Editor::putString)

fun SharedPreferences.getInt(key:String,defaultValue: Int = 0):Int = this.getInt(key,defaultValue)

fun Editor.putInt(key: String,value: Int):Editor = this.putInt(key,value)

fun SharedPreferences.getFloat(key:String,defaultValue: Float = 0f):Float = this.getFloat(key,defaultValue)

fun Editor.putFloat(key: String,value: Float):Editor = this.putFloat(key,value)

fun SharedPreferences.getLong(key:String,defaultValue: Long = 0L):Long = this.getLong(key,defaultValue)

fun Editor.putLong(key: String,value: Long):Editor = this.putLong(key,value)

fun SharedPreferences.getBoolean(key:String,defaultValue: Boolean = false):Boolean = this.getBoolean(key,defaultValue)

fun Editor.putBoolean(key: String,value: Boolean):Editor = this.putBoolean(key,value)

fun SharedPreferences.getString(key:String,defaultValue: String? = null):String? = this.getString(key,defaultValue)

fun Editor.putString(key: String,value: String):Editor = this.putString(key,value)
