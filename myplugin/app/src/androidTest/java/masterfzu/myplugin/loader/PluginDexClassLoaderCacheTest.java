package masterfzu.myplugin.loader;

import android.support.test.InstrumentationRegistry;
import android.widget.Toast;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ryan on 2018/5/25.
 */
public class PluginDexClassLoaderCacheTest {
    @Test
    public void getClassLoader() throws Exception {
        testDemo1();
    }


    private void testDemo1() throws Exception {
        PluginInfo info = new PluginInfo(InstrumentationRegistry.getTargetContext(), "demo1");
        ClassLoader cl = PluginDexClassLoaderCache.getInstance().getClassLoader(InstrumentationRegistry.getTargetContext(), info);
        cl.loadClass("com.qihoo360.replugin.sample.demo1.MainActivity");
        Class helper = cl.loadClass("masterfzu.myplugin.loader.PluginNativeLibsHelper");
//            Toast.makeText(InstrumentationRegistry.getTargetContext(), "优先用父classloader加载：" + helper.getName(), Toast.LENGTH_SHORT).show();
    }


}