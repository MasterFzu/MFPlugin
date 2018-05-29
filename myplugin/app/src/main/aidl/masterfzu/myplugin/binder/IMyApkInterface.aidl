// IMyApkInterface.aidl
package masterfzu.myplugin.binder;

import masterfzu.myplugin.binder.model.PluginInfo;
// Declare any non-default types here with import statements

interface IMyApkInterface {
    /**
     * 安装一个插件
     * <p>
     * 注意：若为旧插件（p-n开头），则应使用IPluginHost的pluginDownloaded方法
     *
     * @return 安装的插件的PluginInfo对象
     */
    PluginInfo install(String path);
}
