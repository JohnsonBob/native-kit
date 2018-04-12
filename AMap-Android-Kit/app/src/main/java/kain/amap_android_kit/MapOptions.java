package kain.amap_android_kit;

import android.graphics.Color;
import android.location.Location;
import android.util.Log;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.MyLocationStyle;

public class MapOptions {
    //volatile, 声明变量值的一致性；static,声明变量的唯一性。 volatile同步机制:内存同步
    private static volatile MapOptions instance = null;
    private AMap aMap = null;
    private MyLocationStyle myLocationStyle = null;
    private UiSettings uiSet = null;
    private Location myLocation = null;

    private MapOptions(AMap aMap){
        this.aMap = aMap;
        this.uiSet = aMap.getUiSettings();
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                myLocation = location;
                Log.wtf("Msg:",myLocation.getLatitude() + " " + myLocation.getLongitude());
            }
        });
    }

    /**
     * @param aMap 地图对象
     * @return MapOptions对象
     */
    public static MapOptions getInstance(AMap aMap) {
        // if already inited, no need to get lock everytime
        if (instance == null) {
            synchronized (MapOptions.class) {
                if (instance == null) {
                    instance = new MapOptions(aMap);
                }
            }
        }

        return instance;
    }

    /**
     * 开启默认蓝点
     */
    public void showPositionDotitionDot(){
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        //        精度圈颜色自定义方法如下
        myLocationStyle.strokeColor(Color.BLACK); //设置定位蓝点精度圆圈的边框颜色的方法。
        myLocationStyle.radiusFillColor(Color.argb(127,82, 249, 82));
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
    }

    /**
     * 完全自定义蓝点
     * @param myLocationStyle 自定义蓝点
     */
    public void showPositionDot(MyLocationStyle myLocationStyle) {
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);
    }

    /**
     * 设置蓝点样式开启蓝点
     * @param LOCATION_TYPE  MyLocationStyle.LOCATION_TYPE 蓝点样式
     */
    public void showPositionDot(int LOCATION_TYPE){
        /*
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);//只定位一次。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;//定位一次，且将视角移动到地图中心点。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) ;//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);//连续定位、且将视角移动到地图中心点，地图依照设备方向旋转，定位点会跟随设备移动。（1秒1次定位）
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
        //以下三种模式从5.1.0版本开始提供
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，并且蓝点会跟随设备移动。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，地图依照设备方向旋转，并且蓝点会跟随设备移动。
        * */
        myLocationStyle.myLocationType(LOCATION_TYPE);
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        //        精度圈颜色自定义方法如下
        myLocationStyle.strokeColor(Color.BLACK); //设置定位蓝点精度圆圈的边框颜色的方法。
        myLocationStyle.radiusFillColor(Color.argb(127,82, 249, 82));
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

    }

    /**
     * 是否开启室内地图
     * @param stuas
     */
    public void showsIndoorMap(Boolean stuas){
        aMap.showIndoorMap(stuas);
    }

    /**
     * 是否启动实时路况
     * @param stuas
     */
    public void showTraffic(Boolean stuas){
        aMap.setTrafficEnabled(stuas);
    }

    /**
     * 地图图层设置
     * @param var1
     * AMap.MAP_TYPE_NAVI 导航地图
     * AMap.MAP_TYPE_NIGHT 夜景地图
     * AMap.MAP_TYPE_SATELLITE 卫星图
     */
    public void setMapType(int var1) {
        aMap.setMapType(var1);
    }

    /**
     * 设置高德地图”Logo的位置。
     * position
     * LOGO_POSITION_BOTTOM_LEFT = 0;
     LOGO_POSITION_BOTTOM_CENTER = 1;
     LOGO_POSITION_BOTTOM_RIGHT = 2;
     ZOOM_POSITION_RIGHT_CENTER = 1;
     ZOOM_POSITION_RIGHT_BUTTOM = 2;
     LOGO_MARGIN_LEFT = 0;
     LOGO_MARGIN_RIGHT = 1;
     LOGO_MARGIN_BOTTOM = 2;
     */
    public void setLogoPosition(int position) {
        uiSet.setLogoPosition(position);
    }

    /**
     * 设置默认高德地图”Logo的位置。
     */
    public void setLogoPosition() {
        uiSet.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);
    }

    /**
     * 设置是否开启缩放按钮和缩放按钮位置
     * @param stuas
     * @param position
     * LOGO_POSITION_BOTTOM_LEFT = 0;
    LOGO_POSITION_BOTTOM_CENTER = 1;
    LOGO_POSITION_BOTTOM_RIGHT = 2;
    ZOOM_POSITION_RIGHT_CENTER = 1;
    ZOOM_POSITION_RIGHT_BUTTOM = 2;
    LOGO_MARGIN_LEFT = 0;
    LOGO_MARGIN_RIGHT = 1;
    LOGO_MARGIN_BOTTOM = 2;
     */
    public void setZoomControls(Boolean stuas,int position) {
        uiSet.setZoomControlsEnabled(stuas);
        uiSet.setZoomPosition(position);
    }

    /**设置是否开启缩放按钮
     * @param stuas
     */
    public void setZoomControls(Boolean stuas) {
        uiSet.setZoomControlsEnabled(stuas);
        uiSet.setZoomPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);
    }

    /**
     * 是否显示指南针
     */
    public void setCompass(Boolean stuas) {
        uiSet.setCompassEnabled(stuas);
    }

    /**
     * 是否显示比例尺
     */
    public void setScale(Boolean stuas) {
        uiSet.setScaleControlsEnabled(stuas);
    }

    /**
     * 设置地图显示语言
     * 即"zh_cn", AMap.ENGLISH 表示英文，即"en"
     */
    public void setScale(String languge) {
        aMap.setMapLanguage(languge);
    }
}
