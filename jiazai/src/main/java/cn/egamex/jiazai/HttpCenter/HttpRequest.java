package cn.egamex.jiazai.HttpCenter;

/**
 * Created by Administrator on 2016/12/23.
 */

public class HttpRequest {
    private static HttpRequest httpRequest = null;

    public static HttpRequest getInstance() {
        if (httpRequest == null) {
            httpRequest = new HttpRequest();
        }
        return httpRequest;
    }

    public void doPostRequestWithoutListener(String url, String content) {
        HttpCenter.getInstance().submitPostData(url, content, null);
    }

    public void doPostRequest(String url, String content, HttpListener httpListener) {
        HttpCenter.getInstance().submitPostData(url, content, httpListener);
    }
}
