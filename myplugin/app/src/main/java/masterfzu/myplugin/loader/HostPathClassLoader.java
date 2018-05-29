package masterfzu.myplugin.loader;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import dalvik.system.PathClassLoader;

/**
 * Created by zhengsiyuan on 2018-04-26.
 */
public class HostPathClassLoader extends PathClassLoader {

    private ClassLoader oOrigCl;

    public HostPathClassLoader(ClassLoader parent, ClassLoader oOrigCl) {
        super("", "", parent);

        this.oOrigCl = oOrigCl;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = PluginContainers.getInstance().lookupMapping(name);
        if (clazz != null)
            return clazz;

        return oOrigCl.loadClass(name);
    }

}
