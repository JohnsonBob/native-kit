package kain.amap_android_kit;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;

public class BaseMapActivity extends AppCompatActivity {

    MapView mMapView = null;
    AMap  aMap = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_map);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState); // 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        MapOptions mapOptions = MapOptions.getInstance(aMap);
        mapOptions.setOnMapOptionsLocationChangeListener(new MapOptions.OnMapOptionsLocationChangeListener() {
            @Override
            public void onMapOptionsLocationChange(Location location) {
                Log.wtf("Msg:",location.getLatitude() + " " + location.getLongitude());
            }
        });
        //设置地图底层为英语
        //aMap.setMapLanguage("en");

        //调用sdk功能测试
        //设置缩放按钮
        mapOptions.setZoomControls(true);
        //显示指南针
        mapOptions.setCompass(true);
        //设置是否显示比例尺
        mapOptions.setScale(true);
        //指定屏幕中心点的手势操作
        //mapOptions.setGestureScaleByMapCenter(500,500,true);

        // 定义北京市经纬度坐标（此处以北京坐标为例）
        LatLng centerBJPoint= new LatLng(18.312963, 109.616185);
// 定义了一个配置 AMap 对象的参数类
        AMapOptions mapOptions1 = new AMapOptions();
// 设置了一个可视范围的初始化位置
// CameraPosition 第一个参数： 目标位置的屏幕中心点经纬度坐标。
// CameraPosition 第二个参数： 目标可视区域的缩放级别
// CameraPosition 第三个参数： 目标可视区域的倾斜度，以角度为单位。
// CameraPosition 第四个参数： 可视区域指向的方向，以角度为单位，从正北向顺时针方向计算，从0度到360度
        mapOptions1.camera(new CameraPosition(centerBJPoint, 10f, 0, 0));
// 定义一个 MapView 对象，构造方法中传入 mapOptions 参数类
        MapView mapView = new MapView(this, mapOptions1);
// 调用 onCreate方法 对 MapView LayoutParams 设置
        mapView.onCreate(savedInstanceState);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
