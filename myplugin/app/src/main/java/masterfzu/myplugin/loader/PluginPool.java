package masterfzu.myplugin.loader;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import masterfzu.myplugin.utils.StringUtils;

/**
 * Created by zhengsiyuan on 2018-05-18.
 */

public class PluginPool {
    private static PluginPool instance;

    private Map<String, Plugin> pool = new HashMap<String, Plugin>();

    private PluginPool() {

    }

    public static PluginPool getInstance() {
        if (instance == null)
            instance = new PluginPool();

        return instance;
    }

    public Plugin loadPlugin(Context ctx, String pluginname) {
        if (ctx == null || StringUtils.isEmpty(pluginname))
            return null;

        if (pool.get(pluginname) != null)
            return pool.get(pluginname);

        /**
         * PluginInfo基础信息
         */
        PluginInfo info = new PluginInfo(ctx, pluginname);
        /**
         * 生成classloader，copy apk 到指定data目录,需要再PluginLoader之前调用，apk在这个步骤copy
         */
        ClassLoader pluginClassLoader = PluginDexClassLoaderCache.getInstance().getClassLoader(ctx, info);
        if (pluginClassLoader == null) {
            Logger.e("(pluginClassLoader == null");
            return null;
        }
        /**
         * 生成Resource
         */
         PluginLoader loader = new PluginLoader(ctx, info);
        /**
         * 组合成Plugin
         */
         Plugin p = new Plugin(ctx, info, pluginClassLoader, loader);
         invokeInjectPlugin(pluginClassLoader, pluginname);
         pool.put(info.getPluginname(), p);

         return p;
    }

    private void invokeInjectPlugin(ClassLoader pluginClassLoader, String pluginname) {
        try {
            Class<?> clazz = pluginClassLoader.loadClass("mypluginsdk.android.masterfzu.mypluginsdk.PluginContextInjector");
            Method inject = clazz.getMethod("inject", ClassLoader.class, String.class);
            inject.invoke(null, PluginPool.class.getClassLoader(), pluginname);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 主要给插件反射回调使用，在启动插件时对应的Plugin肯定已经加载完成
     * @param pluginName
     * @return
     */
    public Plugin lookupPlugin(String pluginName) {
        if (StringUtils.isEmpty(pluginName))
            return null;

        return pool.get(pluginName);
    }
}
