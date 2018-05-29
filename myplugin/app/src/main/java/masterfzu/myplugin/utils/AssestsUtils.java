package masterfzu.myplugin.utils;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhengsiyuan on 2018-05-15.
 */

public class AssestsUtils {

    /**
     * 提取文件到目标位置
     * @param context
     * @param name asset名称（asset的相对路径，可包含子路径）
     * @param dir 目标文件夹（asset的输出目录）
     * @return
     */
    public static final boolean extractTo(Context context, final String name, final String dir) {
        File file = new File(dir);
        InputStream is = FileUtils.openInputStreamFromAssetsQuietly(context, name);
        if (is == null) {
            Logger.e("extractTo: Fail to open " + name + "from Assets");
            return false;
        }

        try {
            FileUtils.copyInputStreamToFile(is, file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(is);
        }

        return false;
    }

    public static final String getPluginPathByName(Context ctx, String pluginName) {
        try {
            for (String name : ctx.getAssets().list("plugins")) {
                if (name.contains(pluginName)) {
                    return "plugins/" + name;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
