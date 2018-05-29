package masterfzu.myplugin.loader;

import android.content.Context;
import android.os.Environment;

import masterfzu.myplugin.BuildConfig;
import masterfzu.myplugin.utils.StringUtils;

/**
 * Created by zhengsiyuan on 2018-05-18.
 */

public class PluginInfo {
    private static final String DATA_PATH = Environment.getDataDirectory() + "/data/" + BuildConfig.APPLICATION_ID + "/plugin/";
    public static final String CONTEXT_PLUGIN_DIR = "plugins";

    private String apkpath, dexpath, libdir, pluginname;

    public PluginInfo(Context ctx, String pluginname) {
        apkpath = ctx.getDir(CONTEXT_PLUGIN_DIR, Context.MODE_PRIVATE).getAbsolutePath() + "/" + pluginname;
        this.pluginname = pluginname;
    }

    public String getApkpath() {
        return apkpath;
    }

    public String getDexpath() {
        return dexpath;
    }

    public String getLibdir() {
        return libdir;
    }

    public String getPluginname() {
        return pluginname;
    }

    public boolean available() {
        if (StringUtils.isEmpty(pluginname) || StringUtils.isEmpty(apkpath))
            return false;

        return true;
    }

}
