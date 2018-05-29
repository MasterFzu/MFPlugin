package masterfzu.myplugin.loader;

import android.content.Context;
import android.os.Build;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import dalvik.system.DexClassLoader;
import masterfzu.myplugin.utils.AssestsUtils;
import masterfzu.myplugin.utils.FileUtils;

/**
 * Created by zhengsiyuan on 2018-04-26.
 */
public class PluginDexClassLoaderCache {
    private static final String CONTEXT_ODEX_DIR = "plugins_odex";

    private static PluginDexClassLoaderCache instance;
    private Map<String, ClassLoader> cache = new HashMap<>();
    private Map<String, ClassLoader> onlyCache = new HashMap<>();

    private PluginDexClassLoaderCache(){

    }

    public static final PluginDexClassLoaderCache getInstance() {
        if (instance == null)
            instance = new PluginDexClassLoaderCache();

        return instance;
    }


    public ClassLoader getClassLoader(Context ctx, PluginInfo info) {
        if (cache.get(info.getPluginname()) != null)
            return cache.get(info.getPluginname());

        ClassLoader sc = quickInstallAssestPlugin(ctx, info, false);
        cache.put(info.getPluginname(), sc);
        return sc;
    }

    @Deprecated
    public ClassLoader getPluginClassLoader(Context ctx, PluginInfo info) {
        if (onlyCache.get(info.getPluginname()) != null)
            return onlyCache.get(info.getPluginname());

        ClassLoader sc = quickInstallAssestPlugin(ctx, info, true);
        onlyCache.put(info.getPluginname(), sc);
        return sc;
    }

    private ClassLoader quickInstallAssestPlugin(Context ctx, PluginInfo info, boolean pluginOnly) {

        boolean rc = AssestsUtils.extractTo(ctx, AssestsUtils.getPluginPathByName(ctx, info.getPluginname()), info.getApkpath());
        if (!rc) {
            Logger.e("AssestsUtils.extractTo failed!!!");
            return null;
        }

        File libdir = PluginNativeLibsHelper.getNativeLibsDir(ctx, info.getPluginname());
        rc = PluginNativeLibsHelper.install(info.getApkpath(), libdir);
        if (!rc) {
            Logger.e("PluginNativeLibsHelper.install failed!!!");
            return null;
        }

        ClassLoader pcl = null;
        if (!pluginOnly)
            pcl = ctx.getClassLoader();

        File dexDir = getDexdir(ctx);
        ClassLoader cl = new DexClassLoader(info.getApkpath(), dexDir.getAbsolutePath(), libdir.getAbsolutePath(), pcl);

        return cl;
    }


    private File getDexdir(Context context) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            return new File(PluginInfo.CONTEXT_PLUGIN_DIR + File.separator + "oat" + File.separator + VMRuntimeCompat.getArtOatCpuType());
        } else {
            return context.getDir(CONTEXT_ODEX_DIR, Context.MODE_PRIVATE);
        }
    }


    /**
     * 根据插件文件名取得插件包名 插件命名规则 com.myplugin.demo.01.jar
     * 01为版本号
     */
    private String getPureName(String name) {
        try {
            int dotIdx = name.lastIndexOf(".");
            if (dotIdx != -1) {
                String fileNameWithoutType = name.substring(0, dotIdx);
                int dotIdx2 = fileNameWithoutType.lastIndexOf(".");
                if (dotIdx2 != -1) {
                    try {
                        return name.substring(0, dotIdx2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return name.substring(0, dotIdx);
                } else {
                    return name.substring(0, dotIdx);
                }
            } else {
                return name;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return name;
        }
    }

}
