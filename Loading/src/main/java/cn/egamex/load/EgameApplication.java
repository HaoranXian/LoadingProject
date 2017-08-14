package cn.egamex.load;

import android.app.Application;

/**
 * Created by Administrator on 2017/8/14/014.
 */

public class EgameApplication extends Application {
    private static EgameApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(getApplicationContext());
    }

    public static EgameApplication getInstance() {
        if (instance == null) {
            instance = new EgameApplication();
        }
        return instance;
    }
}
