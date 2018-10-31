package xcyoung.com.sample

import android.app.Application
import com.xcyoung.cyberframe.Lib

/**
 * @author ChorYeung
 * @since 2018/10/29
 */
class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Lib.init(this)
    }
}