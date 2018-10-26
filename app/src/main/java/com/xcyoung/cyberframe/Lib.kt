package com.xcyoung.cyberframe

import android.app.Application

/**
 * @author ChorYeung
 * @since 2018/10/26
 */
open class Lib {
    companion object {
        lateinit var application:Application
        open fun init(application: Application){
            this.application = application
        }
    }
}