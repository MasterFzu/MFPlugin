package mypluginsdk.android.masterfzu.mypluginsdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by zhengsiyuan on 2018-05-21.
 */

public class MFPluginActivity extends Activity {
    @Override
    protected void attachBaseContext(Context newBase) {
        Context pluginContext = PluginContextInjector.createPluginActivityContext(this.getClass().getName());
        if (pluginContext != null)
            newBase = pluginContext;

        super.attachBaseContext(newBase);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }
}
