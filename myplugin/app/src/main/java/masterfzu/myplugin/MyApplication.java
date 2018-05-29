package masterfzu.myplugin;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import masterfzu.myplugin.loader.HookPathClassLoaderUtil;

/**
 * Created by zhengsiyuan on 2018-04-24.
 */

public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        Logger.addLogAdapter(new AndroidLogAdapter());
        HookPathClassLoaderUtil.hook(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "app start!", Toast.LENGTH_SHORT).show();

        Log.e("MyApplication", "app start!!");
    }
}
