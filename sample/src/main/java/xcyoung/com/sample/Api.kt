package xcyoung.com.sample

import com.xcyoung.cyberframe.http.Response
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author ChorYeung
 * @since 2018/10/29
 */
interface Api {
    @GET("api/today")
    fun gank():Observable<Response<String>>
}