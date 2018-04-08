package kain.amap_android_kit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

public class ChangeLayerActivity extends AppCompatActivity {

    MapView mMapView = null;
    AMap aMap = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_layer);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState); // 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        Button btNormal = (Button)findViewById(R.id.bt_normal);
        btNormal.setOnClickListener(new ChangeLayerActivity.ButtonOnClickListener());
        Button bSatellite = (Button)findViewById(R.id.bt_satellite);
        bSatellite.setOnClickListener(new ChangeLayerActivity.ButtonOnClickListener());
        Button btNight = (Button)findViewById(R.id.bt_night);
        btNight.setOnClickListener(new ChangeLayerActivity.ButtonOnClickListener());
        Button btTraffic = (Button)findViewById(R.id.bt_traffic);
        btTraffic.setOnClickListener(new ChangeLayerActivity.ButtonOnClickListener());

    }

    /**
     * 设置多个按钮监听事件
     */
    private class  ButtonOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case R.id.bt_normal:
                    aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                    break;

                case R.id.bt_satellite:
                    aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 设置卫星地图模式，aMap是地图控制器对象。
                    break;

                case R.id.bt_night:
                    aMap.setMapType(AMap.MAP_TYPE_NIGHT);//夜景地图，aMap是地图控制器对象。
                    break;

                case R.id.bt_traffic:
                    Button btTraffic = (Button)findViewById(R.id.bt_traffic);
                    if(!aMap.isTrafficEnabled()){
                        aMap.setTrafficEnabled(true);//显示实时路况图层，aMap是地图控制器对象。
                        btTraffic.setText("关闭实时路况");
                    }else {
                        aMap.setTrafficEnabled(false);//关闭实时路况图层，aMap是地图控制器对象。
                        btTraffic.setText("开启实时路况");
                    }
                    aMap.setMapType(AMap.MAP_TYPE_NIGHT);//夜景地图，aMap是地图控制器对象。
                    break;
            }
        }
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
