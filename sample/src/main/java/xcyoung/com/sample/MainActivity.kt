package xcyoung.com.sample

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.xcyoung.cyberframe.base.BaseActivity

class MainActivity : BaseActivity() {
    lateinit var sampleViewModel:SampleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        sampleViewModel = getViewModel(SampleViewModel::class.java)
//        sampleViewModel.gank()
//        sampleViewModel.sampleLiveData.observe(this, Observer{
//            Log.i("aaa",it.toString())
//        })
    }
}
