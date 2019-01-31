package com.xcyoung.cyberframe.utils

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.xcyoung.cyberframe.base.BaseActivity
import com.xcyoung.cyberframe.base.BaseFragment
import com.xcyoung.cyberframe.base.BaseViewModel
import kotlin.reflect.KProperty

/**
 * @author ChorYeung
 * @since 2019/1/31
 */
class ViewModelDelegate<out T : BaseViewModel>(private val clazz:Class<T>,private val fromActivity:Boolean,private val factory:ViewModelProvider.Factory?){
    private var viewModel:T?=null

    operator fun getValue(thisRef: BaseActivity,propety: KProperty<*>):T = bulidViewModel(thisRef)

    operator fun getValue(thisRef: BaseFragment,propety: KProperty<*>):T = if(fromActivity)
        bulidViewModel(activity = thisRef.activity as? BaseActivity ?: throw IllegalStateException("Activity must be as BaseActivity!"))
        else bulidViewModel(fragment = thisRef)

    private fun bulidViewModel(activity: BaseActivity?= null,fragment: BaseFragment?= null):T{
        if(viewModel!=null) return viewModel!!

        activity?.let {
            viewModel = ViewModelProviders.of(activity,factory).get(clazz)
        } ?: fragment?.let {
            viewModel = ViewModelProviders.of(fragment,factory).get(clazz)
        } ?: throw IllegalStateException("Activity or Fragment is null! ")
        return viewModel!!
    }
}

fun <T:BaseViewModel> BaseActivity.viewModelDelegate(clazz: Class<T>,factory: ViewModelProvider.Factory? = null)
        = ViewModelDelegate(clazz,true,factory)

fun <T:BaseViewModel> BaseFragment.viewModelDelegate(clazz: Class<T>,fromActivity: Boolean = true,factory: ViewModelProvider.Factory? = null)
        = ViewModelDelegate(clazz,fromActivity,factory)