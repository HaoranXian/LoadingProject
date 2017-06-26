package cn.egamex.jiazai.DownLoad;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.egamex.jiazai.Utils.Constants;


public class DownLoadJar extends Thread {
    private static final String taga = "DownLoadAPK";
    private Context context;
    private static String dexPath;//dex路径
    private DownLoadListener listener;
    private String apkName = "";
    private String downLoadUrl;

    public DownLoadJar(Context context, String apkName, String downLoadUrl, DownLoadListener listener) {
        this.context = context;
        this.apkName = apkName;
        this.downLoadUrl = downLoadUrl;
        this.listener = listener;
        dexPath = getFilesDir(context) + File.separator + apkName;
        if (Constants.isOutPut) {

            Log.i(taga, dexPath);
        }
    }

    @Override
    public void run() {
        super.run();
        ishasFile(dexPath);
        createFile(context);
        download(downLoadUrl, context);
    }


    /**
     * 查询路径下是否存在文件夹
     */
    public static void ishasFile(String path) {
        File destDir = new File(path);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
    }

    /**
     * 在下载路径下创建文件，用来写入
     */
    public void createFile(Context context) {
        try {
            File dir = new File(dexPath);
            if (!dir.exists()) {
                dir.createNewFile();
            } else {
                dir.delete();
                dir.createNewFile();
            }
        } catch (Exception e) {
            Log.d("sdc", "=================>" + e);
        }
    }

    /**
     * 下载文件，并写入
     *
     * @param urlStr
     */
    public void download(String urlStr, Context context) {
        if (Constants.isOutPut) {
            Log.i("SDK", urlStr);
        }
        try {
            URL url = new URL(urlStr);
            try {
                if (Constants.isOutPut) {
                    Log.i(taga, "开始下载");
                }
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                FileOutputStream fos = new FileOutputStream(dexPath);
                byte[] buf = new byte[10 * 1024];
                int count = 0;
                while ((count = is.read(buf)) != -1) {
                    fos.write(buf, 0, count);
                }
                fos.close();
                is.close();
                if (Constants.isOutPut) {
                    Log.i(taga, "下载完成");
                }
                listener.DownLoadState(1);
            } catch (IOException e) {
                if (Constants.isOutPut) {
                    Log.e(taga, "下载失败：" + e);
                }
                listener.DownLoadState(2);
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            listener.DownLoadState(3);
            if (Constants.isOutPut) {
                Log.e(taga, "下载失败：" + e);
            }
            e.printStackTrace();
        }
    }

    private String getFilesDir(Context context) {
        if (context != null) {
            return context.getFilesDir().getAbsolutePath();
        }
        return null;
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
