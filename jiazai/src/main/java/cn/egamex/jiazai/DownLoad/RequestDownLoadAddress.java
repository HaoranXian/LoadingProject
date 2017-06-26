package cn.egamex.jiazai.DownLoad;


import cn.egamex.jiazai.HttpCenter.HttpListener;
import cn.egamex.jiazai.HttpCenter.HttpRequest;
import cn.egamex.jiazai.Utils.Constants;

/**
 * Created by Administrator on 2016/12/23.
 */

public class RequestDownLoadAddress {
    private static RequestDownLoadAddress requestDownLoadAddress = null;

    public static RequestDownLoadAddress getInstance() {
        if (requestDownLoadAddress == null) {
            requestDownLoadAddress = new RequestDownLoadAddress();
        }
        return requestDownLoadAddress;
    }

    public void request(HttpListener listener) {
        HttpRequest.getInstance().doPostRequest(Constants.getLocationAddress(), "", listener);
    }
}
