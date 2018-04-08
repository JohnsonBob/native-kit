package kain.amap_android_kit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //显示地图功能
        Button viewMap = (Button)findViewById(R.id.viewMap);
        viewMap.setOnClickListener(new ButtonOnClickListener());
        //显示蓝点
        Button btShowMyLocation = (Button)findViewById(R.id.bt_showMyLocation);
        btShowMyLocation.setOnClickListener(new ButtonOnClickListener());
        //bt_changeLayer 切换地图图层
        Button btChangeLayer = (Button)findViewById(R.id.bt_changeLayer);
        btChangeLayer.setOnClickListener(new ButtonOnClickListener());
    }

    /**
     * 设置多个按钮监听事件
     */
    private class  ButtonOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case R.id.viewMap:
                    intent = new Intent(MainActivity.this, BaseMapActivity.class);
                    startActivity(intent);
                    break;

                case R.id.bt_showMyLocation:
                    intent = new Intent(MainActivity.this, ShowMyLocationActivity.class);
                    startActivity(intent);
                    break;

                case R.id.bt_changeLayer:
                    intent = new Intent(MainActivity.this, ChangeLayerActivity.class);
                    startActivity(intent);
                    break;

            }
        }
    }
}
