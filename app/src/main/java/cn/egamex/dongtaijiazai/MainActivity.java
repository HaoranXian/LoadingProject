package cn.egamex.dongtaijiazai;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import cn.egamex.dynamicloadings.R;
import cn.egamex.load.SDKManager;


public class MainActivity extends Activity {
    private static Activity mActivity;
    private static boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;
        SDKManager.getInstance().SDKInitializer(this, "100", 16, "", "撞他一个亿", "1001", "", new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                isFirst = false;
                Toast.makeText(getApplicationContext(), "初始化成功返回码：" + String.valueOf(msg.what), Toast.LENGTH_LONG).show();
            }
        }, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Toast.makeText(getApplicationContext(), "支付状态返回码：" + String.valueOf(msg.what), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void Init(View view) {
        SDKManager.getInstance().SDKInitializer(this, "100", 16, "", "撞他一个亿", "1001", "", new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                isFirst = false;
                Toast.makeText(getApplicationContext(), "初始化成功返回码：" + String.valueOf(msg.what), Toast.LENGTH_LONG).show();
            }
        }, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Toast.makeText(getApplicationContext(), "支付状态返回码：" + String.valueOf(msg.what), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void Pay(View view) {
        SDKManager.getInstance().BaiduMap(this, "1500", 16, "", "撞他一个亿", "1001", "", new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Toast.makeText(getApplicationContext(), "支付状态返回码：" + String.valueOf(msg.what), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void PayPoint(View view) {
        Toast.makeText(this, String.valueOf(SDKManager.getInstance().g()), Toast.LENGTH_LONG).show();
    }

    public void SMS(View view) {
        SDKManager.getInstance().s(mActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SDKManager.getInstance().s(mActivity);
    }

}
