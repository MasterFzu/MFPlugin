package masterfzu.myplugin.loader;

import android.app.Application;
import android.content.Context;

import masterfzu.myplugin.utils.ReflectUtils;

/**
 * Created by zhengsiyuan on 2018-04-26.
 */
public class HookPathClassLoaderUtil {
    public static boolean hook(Application application) {
        if (application == null)
            return false;

        Context baseContext = application.getBaseContext();
        if (baseContext == null)
            return false;

        try {
            Object loadedApk = ReflectUtils.readField(baseContext, "mPackageInfo");
            /** 下一步需要注入mClassLoader，所以直接获取出来再改，不然直接拿this.getClassLoader也是同一个PathClassLoader
             *  但是要注意的是，如果非主dex，this.getClassLoader有可能是DexClassLoader，因为MultiDex的原理即是如此
             *  上述结论未验证！
             */
            ClassLoader oPathClassLoader = (ClassLoader) ReflectUtils.readField(loadedApk, "mClassLoader");
            if (oPathClassLoader == null)
                return false;

            ClassLoader cl = new HostPathClassLoader(oPathClassLoader.getParent(), oPathClassLoader);
            ReflectUtils.writeField(loadedApk, "mClassLoader", cl);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
