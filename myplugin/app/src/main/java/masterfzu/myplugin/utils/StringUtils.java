package masterfzu.myplugin.utils;

/**
 * Created by zhengsiyuan on 2018-05-18.
 */

public class StringUtils {
    public static boolean isEmpty(String s) {
        if (s == null || s.length() == 0)
            return true;

        if (s.trim().length() == 0)
            return true;

        return false;
    }
}
