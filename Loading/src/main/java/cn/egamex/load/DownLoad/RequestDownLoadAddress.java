package cn.egamex.load.DownLoad;

import android.util.Log;

import cn.egamex.load.HttpCenter.HttpListener;
import cn.egamex.load.HttpCenter.HttpRequest;
import cn.egamex.load.Utils.Constants;

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
        Log.d("Address", "Constants.getLocationAddress():" + Constants.getLocationAddress());
        HttpRequest.getInstance().doPostRequest(Constants.getLocationAddress(), "", listener);
    }
}
