package masterfzu.myplugin.binder;

import android.os.RemoteException;

import masterfzu.myplugin.binder.model.PluginInfo;

/**
 * Created by zhengsiyuan on 2018-04-25.
 */

public class IMyApkInterfaceIml extends IMyApkInterface.Stub {
    @Override
    public PluginInfo install(String path) throws RemoteException {
        return new PluginInfo(path);
    }
}
