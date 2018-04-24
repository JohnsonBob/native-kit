package kain.amap_android_kit_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.amap.api.maps.MapView

class MainActivity : AppCompatActivity() {
    private var mapView: MapView? = null
    private var mapOptions: MapOptions? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mapView = findViewById(R.id.map) as MapView
        mapView?.onCreate(savedInstanceState)
        mapOptions = MapOptions(mapView?.map!!)
        mapOptions?.showPositionDot()
        mapOptions?.asyncMapLocation?.subscribe {
            Log.wtf("Main Msg", it.latitude.toString() + ":" + it.longitude.toString())
        }
        //mapOptions?.setGestureScaleByMapCenter(100,100,true)
        //改变地图的缩放级别
        //mapOptions?.mapZoomTo(17F)
        //设置地图显示区域
        //mapOptions?.setMapStatusLimits(33.789925, 104.838326,38.740688, 114.647472)
        //设置默认显示地点
        mapView = mapOptions?.setDefaultMap(this, 18.312963, 109.616185, 12f)
        mapView?.onCreate(savedInstanceState)


    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        mapView?.onSaveInstanceState(outState)
    }
}
