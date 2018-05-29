package masterfzu.myplugin.loader;

import android.content.Context;

/**
 * Created by zhengsiyuan on 2018-05-18.
 */

public class Plugin {
    Context hostCtx;
    PluginLoader mLoader;
    PluginInfo mInfo;
    ClassLoader pluginClassLoader;
    PluginContext mPluginContext;
    PluginAppClient client;

    Plugin(Context ctx, PluginInfo info, ClassLoader plClassLoader, PluginLoader loader) {
        this.hostCtx = ctx;
        this.mInfo = info;
        this.mLoader = loader;
        this.pluginClassLoader = plClassLoader;
    }

    public PluginContext getPluginActivityContext(String activityName) {
        PluginContext pc = new PluginContext(hostCtx, android.R.style.Theme, mLoader.mPkgResources, pluginClassLoader);
        if (client == null)
            return pc;

        pc.setTheme(client.p.applicationInfo.theme);
        int activityTheme = client.lookupActivityTheme(activityName);

        if (activityTheme != -1)
            pc.setTheme(activityTheme);

        return pc;
    }

    public boolean callAppIfneed() {
        if (client != null)
            return true;

        client = new PluginAppClient(mInfo);
        mPluginContext = new PluginContext(hostCtx, client.p.applicationInfo.theme, mLoader.mPkgResources, pluginClassLoader);
        client.callAppRun(mPluginContext, pluginClassLoader);
        return true;
    }
}
