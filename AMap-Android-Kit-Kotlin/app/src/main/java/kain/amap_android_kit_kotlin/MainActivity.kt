package kain.amap_android_kit_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.amap.api.maps.MapView

class MainActivity : AppCompatActivity() {
    private var amapView: MapView? = null
    private var amapOptions: AMapOptions? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        amapView = findViewById(R.id.map) as MapView
        amapView?.onCreate(savedInstanceState)
        amapOptions = AMapOptions(amapView?.map!!)
        amapOptions?.showPositionDot()
    }

    override fun onDestroy() {
        super.onDestroy()
        amapView?.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        amapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        amapView?.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        amapView?.onSaveInstanceState(outState)
    }
}
