package masterfzu.myplugin.loader;

import android.app.Application;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by ryan on 2018/5/25.
 */
public class PluginAppClientTest {
    @Test
    public void testApplicationReflect() throws NoSuchMethodException {
        Application app = new Application();
        Method m = app.getClass().getDeclaredMethod("attach", Context.class);
        assertNotNull(m);
    }

    @Test
    public void testPackageParse() {
        PluginInfo info = new PluginInfo(InstrumentationRegistry.getTargetContext(), "mfplugindemo");
        assertTrue(info.available());

        PluginAppClient client = new PluginAppClient(info);
        assertNotNull(client.p);
    }

    @Test
    public void testCallAppRun() {
        Plugin p = PluginPool.getInstance().loadPlugin(InstrumentationRegistry.getTargetContext(), "mfplugindemo");
        assertNotNull(p);

        PluginAppClient client = new PluginAppClient(p.mInfo);
        client.callAppRun(p.mPluginContext, p.pluginClassLoader);
    }

}