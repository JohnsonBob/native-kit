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
