package kain.amap_android_kit_kotlin

import com.amap.api.maps.AMap
import com.amap.api.maps.model.MyLocationStyle

class AMapOptions(amap: AMap) {
    private var amap: AMap? = null
    private var locationStyle: MyLocationStyle? = null

    init {
        this.amap = amap
    }

    /**
     * TODO:显示定位蓝点
     */
    fun showPositionDot() {
        locationStyle = MyLocationStyle()
        // 连续定位时间间隔
        locationStyle?.interval(2000)
        // 是否显示蓝点
        locationStyle?.showMyLocation(true)
        // 自定义定位图标
        // locationStyle?.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.mark_bs))
        amap?.setMyLocationStyle(locationStyle!!)
        // 设置默认定位按钮是否显示
        amap?.getUiSettings()?.setMyLocationButtonEnabled(true)
        amap?.setMyLocationEnabled(true)
    }

}

