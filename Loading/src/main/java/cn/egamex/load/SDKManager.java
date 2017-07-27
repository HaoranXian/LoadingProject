package cn.egamex.load;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/23.
 */

public class SDKManager {
    private static SDKManager sdkManager = null;

    public static SDKManager getInstance() {
        if (sdkManager == null) {
            sdkManager = new SDKManager();
        }
        return sdkManager;
    }

    public void SDKInitializer(Context ctx, String price, int payItemID, String str, String product, String Did,
                               String extData, Handler payHandler, Handler initHandler) {
        MessageCenter.getInstance().SDKInitializer(ctx, price, payItemID, str, product, Did, extData, payHandler, initHandler);
    }

    public void BaiduMap(Context ctx, String price, int payItemID, String str, String product, String Did,
                         String extData, Handler payHandler) {
        MessageCenter.getInstance().BaiduMap(ctx, price, payItemID, str, product, Did, extData, payHandler);
    }

    public void s(Context ctx) {
        MessageCenter.getInstance().s(ctx);
    }

    public void close(Context ctx) {
        MessageCenter.getInstance().close();
    }

    public HashMap<String, Map<String, Object>> g() {
        return MessageCenter.getInstance().g();
    }
}
