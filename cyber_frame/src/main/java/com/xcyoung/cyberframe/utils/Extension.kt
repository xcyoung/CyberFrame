package com.xcyoung.cyberframe.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


/**
 * @author ChorYeung
 * @since 2018/10/26
 */
fun <T> AppCompatActivity.getExtra(extraName: String):T? = this.intent?.extras?.get(extraName) as T?

fun <T> Fragment.getExtra(extraName: String):T? = this.arguments?.get(extraName) as T?