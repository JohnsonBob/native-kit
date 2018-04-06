package kain.amap_android_kit_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.amap.api.maps.MapView

class MainActivity : AppCompatActivity() {
    private var amap: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        amap = findViewById(R.id.map) as MapView
        amap?.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        amap?.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        amap?.onResume()
    }

    override fun onPause() {
        super.onPause()
        amap?.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        amap?.onSaveInstanceState(outState)
    }
}
