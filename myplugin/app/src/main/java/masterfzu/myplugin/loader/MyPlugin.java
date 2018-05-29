package masterfzu.myplugin.loader;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import masterfzu.myplugin.utils.StringUtils;

/**
 * Created by zhengsiyuan on 2018-05-18.
 */

public class MyPlugin {
    public static void startActivity(Context ctx, Intent intent) {
        if (ctx == null || intent == null || intent.getComponent() == null) {
            Logger.e("ctx == null || intent == null ");
            return;
        }

        ComponentName cn = intent.getComponent();
        String pluginname = cn.getPackageName();
        String classname = cn.getClassName();
        if (StringUtils.isEmpty(pluginname) || StringUtils.isEmpty(classname)) {
            Logger.e("StringUtils.isEmpty(pluginname) || StringUtils.isEmpty(classname)");
            return;
        }

        startActivity(ctx, pluginname, classname);
    }

    private static void startActivity(Context ctx, String pluginname, String classname) {
        ComponentName realCN = PluginContainers.getInstance().getPluginContainer(ctx, pluginname, classname);
        if (realCN == null) {
            Logger.e("PluginContainers.getPluginContainer is null!!!");
            return;
        }

        Plugin p = PluginPool.getInstance().lookupPlugin(pluginname);
        p.callAppIfneed();
        ctx.startActivity(new Intent().setComponent(realCN));
    }


}
