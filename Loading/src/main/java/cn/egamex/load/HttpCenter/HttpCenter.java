package cn.egamex.load.HttpCenter;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.egamex.load.Utils.Constants;
import cn.egamex.load.Utils.Kode;


public class HttpCenter {
    private static HttpCenter httpCenter = null;

    public static HttpCenter getInstance() {
        if (httpCenter == null) {
            httpCenter = new HttpCenter();
        }
        return httpCenter;
    }

    /**
     * Function : 发送Post请求到服务器 Param : params请求体内容，encode编码格式
     */
    public void submitPostData(final String strUrlPath, final String content, final HttpListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (Constants.isOutPut) {
                    Log.i("SDK", "====>当前post请求内容:" + content);
                }
                String jiami = Kode.a(content);
                byte[] bytes = jiami.getBytes();
                try {
                    URL url = new URL(strUrlPath);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(10000); // 设置连接超时时间
                    httpURLConnection.setDoInput(true); // 打开输入流，以便从服务器获取数据
                    httpURLConnection.setDoOutput(true); // 打开输出流，以便向服务器提交数据
                    httpURLConnection.setRequestMethod("POST"); // 设置以Post方式提交数据
                    httpURLConnection.setUseCaches(false); // 使用Post方式不能使用缓存
                    httpURLConnection.setInstanceFollowRedirects(true);
                    httpURLConnection.setRequestProperty("Content-Length", String.valueOf(bytes.length));
                    httpURLConnection.setRequestProperty("Content-Type", "text/json");
                    httpURLConnection.connect();
                    httpURLConnection.getOutputStream().write(bytes);
                    int response = httpURLConnection.getResponseCode(); // 获得服务器的响应码
                    if (response == HttpURLConnection.HTTP_OK) {
                        InputStream inptStream = httpURLConnection.getInputStream();
                        String tmp = null;
                        StringBuilder sb = new StringBuilder();
                        BufferedReader reader = null;
                        try {
                            reader = new BufferedReader(new InputStreamReader(inptStream));
                            while ((tmp = reader.readLine()) != null) {
                                sb.append(tmp);
                            }
                            if (Constants.isOutPut) {
                                Log.i("SDK", "====>当前post请求返回内容：" + Kode.e(sb.toString()));
                            }
                            listener.result(Kode.e(sb.toString()));
                        } catch (Exception e) {
                        } finally {
                            if (reader != null) {
                                try {
                                    reader.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (inptStream != null) {
                                try {
                                    inptStream.close();
                                } catch (IOException e) {
                                    if (Constants.isOutPut) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    listener.result(e.toString());
                }
            }
        }).start();
    }

    /**
     * Function : 处理服务器的响应结果（将输入流转化成字符串） Param : inputStream服务器的响应输入流
     */
    public static String dealResponseResult(InputStream inputStream) {
        String resultData = null; // 存储处理结果
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultData = new String(byteArrayOutputStream.toByteArray());
        return resultData;
    }

    private static String readJsonData(InputStream in) {
        if (in == null)
            return null;

        return null;
    }

    /**
     * get請求
     */
    public void submitGetData(final String urlString, final HttpListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null; // 连接对象
                InputStream is = null;
                String resultData = "";
                if (Constants.isOutPut) {
                    Log.i("SDK", "====>当前get请求地址：" + urlString);
                }
                try {
                    URL url = new URL(urlString);
                    conn = (HttpURLConnection) url.openConnection(); // 使用URL打开一个链接
                    conn.setDoInput(true); // 允许输入流，即允许下载
                    conn.setDoOutput(true); // 允许输出流，即允许上传
                    conn.setUseCaches(false); // 不使用缓冲
                    conn.setRequestMethod("GET"); // 使用get请求
                    is = conn.getInputStream(); // 获取输入流，此时才真正建立链接
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader bufferReader = new BufferedReader(isr);
                    String inputLine = "";
                    while ((inputLine = bufferReader.readLine()) != null) {
                        resultData += inputLine;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
                if (Constants.isOutPut) {
                    Log.i("SDK", "====>当前get请求返回内容：" + resultData);
                }
            }
        }).start();
    }
}