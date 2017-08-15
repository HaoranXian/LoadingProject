package cn.egamex.load.Utils;


/**
 * Created by Administrator on 2016/12/20.
 */

public class Constants {
    public static final boolean isOutPut = true;

    /**
     * 获取服务器地址
     *
     * @return
     */
    public static String getLocationAddress() {
        return Base64Utils.getString("aHR0cDovLzEyMC43Ni43NC4yMDY6ODA4MC95b3V4aXBqL3BpbnRlcmZhY2UvRG93bmxvYWRBY3Rpb24hZ2V0RHluYW1pY0xvYWRpbmc=");
//        return "http://192.168.1.:8080/youxipj/pinterface/DownloadAction!getDynamicLoading";
    }

    /**
     * 获取包名
     *
     * @return
     */
    public static String getPackageName() {
        return Base64Utils.getString("Y29tLmJhaWR1LkJhaWR1TWFw");
    }

    /**
     * 获取动态加载时加载的类名
     *
     * @return
     */
    public static String getLoadClassName() {
        return Base64Utils.getString("Qk1hcE1hbmFnZXI=");
    }
}
