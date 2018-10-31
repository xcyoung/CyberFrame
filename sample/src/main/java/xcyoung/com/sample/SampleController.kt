package xcyoung.com.sample

import com.xcyoung.cyberframe.base.BaseController
import com.xcyoung.cyberframe.http.Response
import com.xcyoung.cyberframe.http.RetrofitHandler
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author ChorYeung
 * @since 2018/10/29
 */
class SampleController : BaseController(){
    fun gank():Observable<Response<String>>{
        return RetrofitHandler.createService(Api::class.java,"http://gank.io/")
                .gank()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


}