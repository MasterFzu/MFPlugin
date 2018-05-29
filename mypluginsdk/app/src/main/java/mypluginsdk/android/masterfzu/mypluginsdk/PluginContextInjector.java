package mypluginsdk.android.masterfzu.mypluginsdk;

import android.content.Context;

/**
 * Created by zhengsiyuan on 2018-05-21.
 * 初始化回调宿主相关方法，类名被反射调用，不可修改
 */
public class PluginContextInjector {
    /**
     * 宿主固定class，误改
     */
    public static final String MASTERFZU_MYPLUGIN_INVOKEBYPLUGIN_PLUGIN_INVOKED_SET = "masterfzu.myplugin.invokebyplugin.PluginInvokedSet";
    /**
     * 宿主class固定method，误改
     */
    public static final String CREATE_PLUGIN_ACTIVITY_CONTEXT = "createPluginActivityContext";
    private static boolean init = false;
    private static String pluginName;
    private static MethodInvoker invokeCreatePluginActivityContext;

    /**
     * 宿主反射使用，勿删勿改！！！
     */
    public static final void inject(ClassLoader hostCl, String pluginName) {
        doInject(hostCl, pluginName);
    }

    private static synchronized void doInject(ClassLoader hostCl, String pluginName) {
        if (init)
            return;

        init = true;
        PluginContextInjector.pluginName = pluginName;

        invokeCreatePluginActivityContext = new MethodInvoker(hostCl, MASTERFZU_MYPLUGIN_INVOKEBYPLUGIN_PLUGIN_INVOKED_SET, CREATE_PLUGIN_ACTIVITY_CONTEXT, new Class<?>[]{String.class});
    }

    public static Context createPluginActivityContext(String activityName) {
        if (!init)
            return null;

        return (Context) invokeCreatePluginActivityContext.call(pluginName, activityName);
    }

}
