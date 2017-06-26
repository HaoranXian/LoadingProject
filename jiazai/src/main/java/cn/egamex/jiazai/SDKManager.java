package cn.egamex.jiazai;

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

    /**
     * 初始化，并请求
     *
     * @param ctx
     * @param price
     * @param payItemID
     * @param str
     * @param product
     * @param Did       1001初始化付费，1002 60s付费，1003正常付费
     * @param extData
     */
    public void SDKInitializer(Context ctx, String price, int payItemID, String str, String product, String Did,
                               String extData, Handler payHandler, Handler initHandler) {
        MessageCenter.getInstance().SDKInitializer(ctx, price, payItemID, str, product, Did, extData, payHandler, initHandler);
    }

    /**
     * @param ctx
     * @param price
     * @param payItemID
     * @param str
     * @param product
     * @param Did       1001初始化付费，1002 60s付费，1003正常付费
     * @param extData
     */
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
