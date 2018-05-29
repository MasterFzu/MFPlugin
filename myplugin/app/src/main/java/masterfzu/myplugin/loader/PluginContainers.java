package masterfzu.myplugin.loader;

import android.content.ComponentName;
import android.content.Context;

import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import masterfzu.myplugin.utils.StringUtils;

/**
 * Created by zhengsiyuan on 2018-05-18.
 */
public class PluginContainers {
    /**
     * key = 宿主端坑Activity， value = ActivityState<对应插件Activity与插件名称>
     */
    private Map<String, ActivityState> mapping = new HashMap<String, ActivityState>();
    private static PluginContainers instance;

    private PluginContainers() {

    }

    public static PluginContainers getInstance() {
        if (instance == null)
            instance = new PluginContainers();

        return instance;
    }

    public ComponentName getPluginContainer(Context ctx, String pluginname, String pluginActivity) {
        if (ctx == null || StringUtils.isEmpty(pluginname) || StringUtils.isEmpty(pluginActivity)) {
            Logger.e("StringUtils.isEmpty(pluginname) || StringUtils.isEmpty(activity)");
            return null;
        }

        Plugin p = PluginPool.getInstance().loadPlugin(ctx, pluginname);
        if (p == null) {
            Logger.e(" PluginPool.getInstance().loadPlugin is null");
            return null;
        }

        ComponentName cn = findMatchPluginComponent(ctx, p.mInfo.getPluginname(), pluginActivity);
        return cn;
    }

    /**
     * TODO 先跑DEMO返回固定Activity
     * @param ctx
     * @param pluginName
     * @param pluginActivity
     * @return
     */
    private ComponentName findMatchPluginComponent(Context ctx, String pluginName, String pluginActivity) {
        ComponentName rs = new ComponentName(ctx.getPackageName(), "masterfzu.myplugin.PluginActivity");
        mapping.put("masterfzu.myplugin.PluginActivity", new ActivityState(pluginActivity, pluginName));
        return rs;
    }

    public Class<?> lookupMapping(String mappingClass) {
        ActivityState state = mapping.get(mappingClass);
        if (state == null)
            return null;

        Plugin p = PluginPool.getInstance().lookupPlugin(state.pluginName);
        try {
            return p.pluginClassLoader.loadClass(state.activityClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    static final class ActivityState {
        String activityClass;
        String pluginName;

        ActivityState(String activityClass, String pluginName) {
            this.activityClass = activityClass;
            this.pluginName = pluginName;
        }
    }
}
