package kain.amap_android_kit_kotlin

import android.graphics.Color
import android.location.Location
import android.util.Log
import com.amap.api.maps.AMap
import com.amap.api.maps.AMapOptions
import com.amap.api.maps.UiSettings
import com.amap.api.maps.model.MyLocationStyle

class MapOptions(map: AMap) {
    private var map: AMap? = null
    private var locationStyle: MyLocationStyle? = null
    private var uiSet: UiSettings? = null

    init {
        this.map = map
        this.uiSet = this.map?.getUiSettings()
        map.setOnMyLocationChangeListener {
            this.onMyLocationChange(it)
        }
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
        // 定位蓝点精度圆圈的边框颜色
        locationStyle?.strokeColor(Color.RED)
        // 定位蓝点精度圆圈的填充颜色
        locationStyle?.radiusFillColor(Color.BLACK)
        // 精度圈边框宽度自定义
        locationStyle?.strokeWidth((2).toFloat())
        map?.setMyLocationStyle(locationStyle!!)
        // 设置默认定位按钮是否显示
        map?.getUiSettings()?.setMyLocationButtonEnabled(true)
        map?.setMyLocationEnabled(true)
    }

    /**
     * TODO:开启室内地图
     */
    fun showsIndoorMap() {
        map?.showIndoorMap(true)
    }

    /**
     * TODO:显示交通状态
     */
    fun showTraffic() {
        map?.setTrafficEnabled(false)
    }

    /**
     * TODO:地图图层设置
     * MAP_TYPE_NAVI 导航地图
     * MAP_TYPE_NIGHT 夜景地图
     * MAP_TYPE_SATELLITE 卫星图
     */
    fun setMapType() {
        map?.setMapType(AMap.MAP_TYPE_NORMAL)
    }

    /**
     * TODO:地图Logo设置
     */
    fun setLogoPosition() {
        uiSet?.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT)
    }

    /**
     * TODO:缩放控件
     */
    fun setZoomControls() {
        uiSet?.setZoomControlsEnabled(false)
        uiSet?.setZoomPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT)
    }

    /**
     * TODO:指南针
     */
    fun setCompass() {
        uiSet?.setCompassEnabled(false)
    }

    /**
     * TODO:比例尺
     */
    fun setScale() {
        uiSet?.setScaleControlsEnabled(false)
    }


    /**
     * TODO:定位坐标监听
     */
    fun onMyLocationChange(location: Location?) {
        Log.wtf("Msg:", location?.latitude.toString() + " " + location?.longitude.toString())
    }
}

