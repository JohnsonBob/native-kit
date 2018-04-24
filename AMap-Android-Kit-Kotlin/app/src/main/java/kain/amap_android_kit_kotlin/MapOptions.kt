package kain.amap_android_kit_kotlin

import android.content.Context
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.util.Log
import com.amap.api.maps.*
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.LatLngBounds
import com.amap.api.maps.model.MyLocationStyle
import io.reactivex.subjects.AsyncSubject
import com.amap.api.maps.MapView
import com.amap.api.maps.model.CameraPosition
import com.amap.api.maps.AMapOptions



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

    /**
     * 改变地图的缩放级别
     * 地图的缩放级别一共分为 17 级，从 3 到 19。数字越大，展示的图面信息越精细。
     */
    fun mapZoomTo(value: Float){
        map?.animateCamera(CameraUpdateFactory.zoomTo(value))
    }

    /**
     * 设置地图显示范围的方法，手机屏幕仅显示设定的地图范围
     * 注意：如果限制了地图显示范围，地图旋转手势将会失效。
     */
    fun setMapStatusLimits(startPointLatitude: Double, startPointLongitude: Double, endPointLatitude: Double, endPointLongitude: Double){
        var startPoint: LatLng?=  LatLng(startPointLatitude, startPointLongitude)
        var endPoint: LatLng? = LatLng(endPointLatitude, endPointLongitude)
        var latLngBounds: LatLngBounds? =  LatLngBounds(startPoint, endPoint);
        map?.setMapStatusLimits(latLngBounds);
    }

    /**
     * 改变地图默认显示区域
     * @param Latitude 纬度 目标位置的屏幕中心点经纬度坐标纬度
     * @param Longitude 经度 目标位置的屏幕中心点经纬度坐标经度
     * @param zoomValue 地图缩放级别
     */
    fun setDefaultMap(paramContext: Context, Latitude: Double, Longitude: Double, zoomValue: Float): MapView {
        // 定义北京市经纬度坐标（此处以北京坐标为例）
        val centerBJPoint = LatLng(Latitude, Longitude)
        // 定义了一个配置 AMap 对象的参数类
        val mapOptions = AMapOptions()
        // 设置了一个可视范围的初始化位置
        // CameraPosition 第一个参数： 目标位置的屏幕中心点经纬度坐标。
        // CameraPosition 第二个参数： 目标可视区域的缩放级别
        // CameraPosition 第三个参数： 目标可视区域的倾斜度，以角度为单位。
        // CameraPosition 第四个参数： 可视区域指向的方向，以角度为单位，从正北向顺时针方向计算，从0度到360度
        mapOptions.camera(CameraPosition(centerBJPoint, zoomValue, 0f, 0f))
        // 定义一个 MapView 对象，构造方法中传入 mapOptions 参数类
         var mapView: MapView = MapView(paramContext, mapOptions)
        this.map = mapView.map
        this.uiSet = map?.uiSettings
        return mapView

    }



    fun setGestureScaleByMapCenter(x: Int, y: Int, stuas: Boolean){
        if(x==y && x != -1){
            map?.setPointToCenter(x, y)
        }
        uiSet?.setGestureScaleByMapCenter(stuas)
    }
}

