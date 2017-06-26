package cn.egamex.jiazai.Utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;

/**
 * Created by Administrator on 2016/12/20.
 */

public class Utils {
    /**
     * 获取IMSI
     *
     * @param ctx
     * @return
     */
    public static String getIMSI(final Context ctx) {
        String imsi = "";
        try {
            TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
            imsi = tm.getSubscriberId();
            return imsi;
        } catch (Exception e) {
            return imsi;
        }
    }

    /**
     * 獲取包ID
     *
     * @param ctx
     * @return
     */
    public static String getPackId(Context ctx) {
        String gameId = null;
        ApplicationInfo appinfo = null;
        try {
            appinfo = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
            if (appinfo != null) {
                Bundle metaData = appinfo.metaData;
                if (metaData != null) {
                    gameId = metaData.get("Y-PAY-PID").toString();
                    return gameId;
                }
            }
        } catch (Exception e) {
            if (Constants.isOutPut) {
                e.printStackTrace();
            }
            return gameId;
        }
        return gameId;
    }
}
