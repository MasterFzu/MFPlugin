package masterfzu.myplugin.utils;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by zhengsiyuan on 2018-05-15.
 */
@RunWith(AndroidJUnit4.class)
public class AssestsUtilsTest {
    @Test
    public void getPluginPathByName() throws Exception {
        String fullname = AssestsUtils.getPluginPathByName(InstrumentationRegistry.getContext(), "demo1");
        Log.e("TEST", fullname);
    }

}