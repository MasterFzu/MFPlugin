package masterfzu.myplugin.loader;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageParser;

import java.io.File;
import java.lang.reflect.Constructor;

import masterfzu.myplugin.parse.PackageParserCompat;
import masterfzu.myplugin.utils.StringUtils;
import mirror.MethodParams;
import mirror.RefClass;
import mirror.RefMethod;

/**
 * Created by zhengsiyuan on 2018-05-23.
 */
public class PluginAppClient {
    public static Class<?> TYPE = RefClass.load(PluginAppClient.class, "android.app.Application");

    @MethodParams({Context.class})
    public static RefMethod<Void> attach;

    PackageInfo mInfo;
    PackageParser.Package p;
    Application app;

    public PluginAppClient(PluginInfo info) {
        if (info == null || !info.available())
            return;

        File apk = new File(info.getApkpath());
        //getPackageArchiveInfo
        PackageParser pp = PackageParserCompat.createParser(apk);
        try {
            p = PackageParserCompat.parsePackage(pp, apk, 0);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void callAppRun(Context pluginContext, ClassLoader pluginCl) {
        if (app == null)
            app = createApplication(pluginCl);

        attach.call(app, pluginContext);
        app.onCreate();
    }

    public int lookupActivityTheme(String activity) {
        if (p == null || StringUtils.isEmpty(activity))
            return -1;

        for (android.content.pm.PackageParser.Activity a : p.activities) {
            if (!activity.equals(a.info.name))
                continue;

            return a.info.getThemeResource();
        }

        return -1;
    }

    private Application createApplication(ClassLoader pluginCl) {
        if (pluginCl == null || p == null)
            return new Application();

        String cn = p.applicationInfo.className;
        if (StringUtils.isEmpty(cn))
            return new Application();

        try {
//            Class<?> clazz = Class.forName(cn);
            Class<?> clazz = pluginCl.loadClass(cn);
            Constructor cst = clazz.getConstructor();
            Object obj = cst.newInstance();
            if (obj instanceof Application)
                return (Application)obj;
        } catch (Exception e) {
            e.printStackTrace();
            return new Application();
        }

        return new Application();
    }
}
