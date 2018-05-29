package masterfzu.myplugin.invokebyplugin;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.junit.Test;

import masterfzu.myplugin.loader.Plugin;
import masterfzu.myplugin.loader.PluginContext;
import masterfzu.myplugin.loader.PluginPool;

import static org.junit.Assert.*;

/**
 * Created by ryan on 2018/5/26.
 */
public class PluginInvokedSetTest {
    @Test
    public void createPluginActivityContext() throws Exception {
        Plugin p = PluginPool.getInstance().loadPlugin(InstrumentationRegistry.getTargetContext(), "mfplugindemo");
        assertNotNull(p);

        assertTrue(p.callAppIfneed());

        Context ctx = PluginInvokedSet.createPluginActivityContext("mfplugindemo", "demo1.example.mfreplugin.mfplugindemo1.MainActivity");
        assertNotNull(ctx);

//        assertEquals(android.R.style.Theme_Wallpaper, ctx.getTheme());
    }

}