package masterfzu.myplugin.loader;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.view.ContextThemeWrapper;

import java.io.File;

/**
 * Created by zhengsiyuan on 2018-05-17.
 */

public class PluginContext extends ContextThemeWrapper {
    private Resources pluginResources;
    private ClassLoader pluginClassLoader;


    public PluginContext(Context hostctx, int themeID, Resources pluginResources, ClassLoader pluginClassLoader) {
        super(hostctx, themeID);

        this.pluginResources = pluginResources;
        this.pluginClassLoader = pluginClassLoader;
    }

    @Override
    public Resources getResources() {
        if (pluginResources != null)
            return pluginResources;

        return super.getResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        if (pluginClassLoader != null)
            return pluginClassLoader;

        return super.getClassLoader();
    }

    @Override
    public AssetManager getAssets() {
        if (pluginResources != null)
            return pluginResources.getAssets();

        return super.getAssets();
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        name = "plugin_" + name;
        return super.getSharedPreferences(name, mode);
    }


}
