package cn.egamex.load.loading;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import org.json.JSONObject;

import java.io.File;

import cn.egamex.load.DownLoad.DownLoadJar;
import cn.egamex.load.DownLoad.DownLoadListener;
import cn.egamex.load.DownLoad.RequestDownLoadAddress;
import cn.egamex.load.HttpCenter.HttpListener;
import cn.egamex.load.Utils.Constants;
import cn.egamex.load.Utils.Kode;
import dalvik.system.DexClassLoader;

/**
 * Created by Administrator on 2017/7/27.
 */

public class LoadingManager {
    private static Context context;
    private static LoadingManager loadingManager = null;
    String jarName = "";
    Class<?> cls;

    public static LoadingManager getInstance() {
        if (loadingManager == null) {
            loadingManager = new LoadingManager();
        }
        return loadingManager;
    }

    public void loading(final Context ctx, final Handler LoadingCallBack) {
        context = ctx;
        RequestDownLoadAddress.getInstance().request(new HttpListener() {
            @Override
            public void result(String result) {
                if (Constants.isOutPut) {
                    System.out.println("======>" + Kode.a(result));
                }
//                String url = getUrl(Kode.a(result));
                String url = "http://120.76.74.206:8080/youxipj/Download/PaySDK.apk";
                jarName = getJarName(url);
                if (Constants.isOutPut) {
                    System.out.println("======>url:" + url);
                    System.out.println("======>jarName:" + jarName);
                }
                new DownLoadJar(ctx, jarName, url, new DownLoadListener() {
                    @Override
                    public void DownLoadState(int state) {
                        LoadingCallBack.sendEmptyMessage(1);
                    }
                }).start();
            }
        });
    }

    private String getUrl(String content) {
        try {
            JSONObject json = new JSONObject(content);
            String result = json.getString("url");
            return result;
        } catch (Exception e) {
            return "";
        }
    }

    private String getJarName(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    private String getFilesDir(Context context) {
        if (context != null) {
            return context.getFilesDir().getAbsolutePath();
        }
        return null;
    }

    private Class<?> LoadDex(Context context, String jarName) {
        Class<?> clazz = null;
        ClassLoader clsLoader = context.getClassLoader();
        String dexPath = getFilesDir(context) + File.separator + jarName;
        if (Constants.isOutPut) {
            Log.i("SDK", "====>dexPath:" + dexPath);
        }
        String dexOutputPath = getFilesDir(context) + File.separator;
        if (Constants.isOutPut) {
            Log.i("SDK", "====>dexOutputPath:" + dexOutputPath);
        }
        DexClassLoader cl = new DexClassLoader(dexPath, dexOutputPath, null, clsLoader);
        String pacageName = Constants.getPackageName();
        if (Constants.isOutPut) {
            Log.i("SDK", "获取到的包名：" + pacageName);
            Log.i("SDK", "加载jar完成");
        }
        try {
            clazz = cl.loadClass(pacageName + "." + "BMapManager");
        } catch (Exception e) {
            Log.i("SDK", e.toString());
        }
        return clazz;
    }

    public Class<?> getClazz() {
        cls = LoadDex(context, jarName);
        if (cls != null) {
            return cls;
        }
        return null;
    }

    public void setClassNull() {
        cls = null;
    }
}
