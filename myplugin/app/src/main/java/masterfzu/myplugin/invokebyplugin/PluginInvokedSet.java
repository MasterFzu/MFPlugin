package masterfzu.myplugin.invokebyplugin;

import android.content.Context;

import masterfzu.myplugin.loader.Plugin;
import masterfzu.myplugin.loader.PluginPool;

/**
 * Created by zhengsiyuan on 2018-05-21.
 * 插件反射调用宿主相关函数的集合类，被插件调用，类名不能改！
 */
public class PluginInvokedSet {
    /**
     * 被插件反射调用，函数体全部不能改！
     * @param pluginName
     * @return
     */
    public static final Context createPluginActivityContext(String pluginName, String activityName) {
        Plugin p = PluginPool.getInstance().lookupPlugin(pluginName);
        if (p == null)
            return null;

        return p.getPluginActivityContext(activityName);
    }
}
