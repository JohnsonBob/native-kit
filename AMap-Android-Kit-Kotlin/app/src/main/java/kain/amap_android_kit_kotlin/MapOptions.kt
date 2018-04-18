package kain.amap_android_kit_kotlin

import android.graphics.Color
import android.location.Location
import android.util.Log
import com.amap.api.maps.AMap
import com.amap.api.maps.AMapOptions
import com.amap.api.maps.UiSettings
import com.amap.api.maps.model.MyLocationStyle
import io.reactivex.subjects.AsyncSubject

class MapOptions(map: AMap) {
    private var map: AMap? = null
    private var locationStyle: MyLocationStyle? = null
    private var uiSet: UiSettings? = null
    var asyncMapLocation: AsyncSubject<Location> = AsyncSubject.create()


    init {
        this.map = map
        this.uiSet = this.map?.getUiSettings()
        this.map?.setOnMyLocationChangeListener {
            if (it != null) {
                asyncMapLocation.onNext(it)
                asyncMapLocation.onComplete()
            } else {
                Log.e("AmapError", "location Error")
            }
        }
        this.asyncMapLocation.subscribe({
            Log.wtf("Msg", it.latitude.toString() + ":" + it.longitude.toString())
        })
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
     * 设置地图显示语言
     * 即"zh_cn", AMap.ENGLISH 表示英文，即"en"
     */
    fun setMapLanguage(languge: String) {
        map?.setMapLanguage(languge)
    }



    /**
     * @param stuas 开|关
     * 设置缩放手势
     */
    fun  setZoomGestures(stuas: Boolean){
        uiSet?.setZoomGesturesEnabled(stuas)
    }

    /**
     * @param stuas 开|关
     * 设置滑动手势
     */
    fun setScrollGestures(stuas: Boolean){
        uiSet?.setScrollGesturesEnabled(stuas)
    }

    /**
     * @param stuas 开|关
     * 设置旋转手势
     */
    fun setRotateGestures(stuas: Boolean){
        uiSet?.setRotateGesturesEnabled(stuas)
    }

    /**
     * @param stuas 开|关
     * 设置倾斜手势
     */
    fun setTiltGestures(stuas: Boolean){
        uiSet?.setTiltGesturesEnabled(stuas)
    }

    /**
     * @param stuas 开|关
     * 设置所有手势
     */
    fun setAllGestures(stuas: Boolean){
        uiSet?.setAllGesturesEnabled(stuas)
    }

    /**
     * 缩放手势是否开启
     */
    fun  isZoomGesturesEnabled(): Boolean?{
        return uiSet?.isZoomGesturesEnabled()
    }

    /**
     * 滑动手势是否开启
     */
    fun isScrollGesturesEnabled(): Boolean?{
        return uiSet?.isScrollGesturesEnabled()
    }

    /**
     * 旋转手势是否开启
     */
    fun  isRotateGesturesEnabled(): Boolean?{
        return uiSet?.isRotateGesturesEnabled()
    }

    /**
     * 倾斜手势是否开启
     */
    fun isTiltGesturesEnabled(): Boolean?{
        return uiSet?.isTiltGesturesEnabled()
    }


    /**
     * 指定屏幕中心点的手势操作 在对地图进行手势操作时（滑动手势除外），可以指定屏幕中心点后执行相应手势。
     * //x、y均为屏幕坐标，屏幕左上角为坐标原点，即(0,0)点。
     * 当x和y同时为-1时 设置坐标不生效
     * @param x
     * @param y
     * @param stuas 是否启用
     */
    fun setGestureScaleByMapCenter(x: Int, y: Int, stuas: Boolean){
        if(x==y && x != -1){
            map?.setPointToCenter(x, y)
        }
        uiSet?.setGestureScaleByMapCenter(stuas)
    }
}

