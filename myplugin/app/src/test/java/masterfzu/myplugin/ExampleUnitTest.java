package masterfzu.myplugin;

import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        new T2().attachBaseContext();
    }

    static class T1 {
        protected void attachBaseContext() {
            System.out.println(this.getClass().getName());
        }
    }

    static class T2 extends T1 {

    }


}