package cn.egamex.jiazai.Utils;

/**
 * Created by Administrator on 2016/12/20.
 */

public class Constants {
    public static final boolean isOutPut = true;

    static {
        System.loadLibrary("Constants");
    }

    /**
     * 获取服务器地址
     *
     * @return
     */
    public static native String getLocationAddress();

    /**
     * 获取包名
     *
     * @return
     */
    public static native String getPackageName();

    /**
     * 获取动态加载时加载的类名
     *
     * @return
     */
    public static native String getLoadClassName();

}
