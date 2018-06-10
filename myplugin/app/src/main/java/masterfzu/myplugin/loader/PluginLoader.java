package masterfzu.myplugin.loader;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;

import com.orhanobut.logger.Logger;

import masterfzu.myplugin.BuildConfig;
import masterfzu.myplugin.utils.StringUtils;

/**
 * Created by zhengsiyuan on 2018-05-18.
 * 模仿APKLoader，存放Plugin apk包相关信息
 */
public class PluginLoader {
    PackageInfo mPackageInfo;
    Resources mPkgResources;

    PluginLoader(Context ctx, PluginInfo info) {
        if (ctx == null || info == null)
            return;

        genarateResources(ctx, info);
    }

    private void genarateResources(Context ctx, PluginInfo info) {
        boolean rs = genaratePackageInfo(ctx, info);
        if (!rs) {
            Logger.e("genaratePackageInfo is null!!!");
            return;
        }

        try {
            if (BuildConfig.DEBUG) {
                // 如果是Debug模式的话，防止与Instant Run冲突，资源重新New一个
                Resources r = ctx.getPackageManager().getResourcesForApplication(mPackageInfo.applicationInfo);
                mPkgResources = new Resources(r.getAssets(), r.getDisplayMetrics(), r.getConfiguration());
            } else {
                mPkgResources = ctx.getPackageManager().getResourcesForApplication(mPackageInfo.applicationInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean genaratePackageInfo(Context ctx, PluginInfo info) {
        mPackageInfo = ctx.getPackageManager().getPackageArchiveInfo(info.getApkpath(), PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES | PackageManager.GET_PROVIDERS | PackageManager.GET_RECEIVERS | PackageManager.GET_META_DATA);
        if (mPackageInfo == null || mPackageInfo.applicationInfo == null) {
            Logger.e("genaratePackageInfo is null!!!");
            return false;
        }

        mPackageInfo.applicationInfo.sourceDir = info.getApkpath();
        mPackageInfo.applicationInfo.publicSourceDir = info.getApkpath();
        if (StringUtils.isEmpty(mPackageInfo.applicationInfo.processName)) {
            mPackageInfo.applicationInfo.processName = mPackageInfo.applicationInfo.packageName;
        }
        mPackageInfo.applicationInfo.nativeLibraryDir = PluginNativeLibsHelper.getNativeLibsDir(ctx, info.getPluginname()).getAbsolutePath();
        return true;
    }

}
