package xcyoung.com.sample

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.xcyoung.cyberframe.base.BaseViewModel
import com.xcyoung.cyberframe.http.Exception
import com.xcyoung.cyberframe.http.HttpRelustObserver
import com.xcyoung.cyberframe.http.RetrofitHandler
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.ResourceObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * @author ChorYeung
 * @since 2018/10/29
 */
class SampleViewModel : BaseViewModel(){
    val sampleLiveData = MutableLiveData<String>()

    fun gank(){
        addDisposable(SampleController().gank()
                .subscribeWith(object : HttpRelustObserver<String>(){
                    override fun onSuccess(resultMap: String?) {
                        Timber.i("aaa:${resultMap}")
                    }

                    override fun onFailed(exception: Exception) {
                       Timber.i("aaa:${exception.message}")
                    }

                }))
    }
}