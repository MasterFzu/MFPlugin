package masterfzu.myplugin.fragments;

import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import masterfzu.myplugin.R;
import masterfzu.myplugin.binder.IMyApkInterface;
import masterfzu.myplugin.binder.model.PluginInfo;
import masterfzu.myplugin.binder.provider.BinderCursor;
import masterfzu.myplugin.binder.provider.ProcessBinderProvider;
import masterfzu.myplugin.loader.MyPlugin;
import masterfzu.myplugin.loader.PluginDexClassLoaderCache;

/**
 * Created by zhengsiyuan on 2018-04-24.
 */
public class FirstFragment extends Fragment {

    @BindView(R.id.btn_get_aidl)
    Button mGetAidl;
    @BindView(R.id.btn_loaddemo1)
    Button mLoadDemo1;
    @BindView(R.id.btn_loadfanyue)
    Button mLoadFanyue;
    @BindView(R.id.btn_loaddemo3)
    Button mLoadDemo3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.btn_get_aidl)
    public void onClick4GetAidl() {
        Log.e("FirstFragment", "onClick4GetAidl!");
        Cursor cursor = null;
        try {
            cursor = getContext().getContentResolver().query(ProcessBinderProvider.URI, null, null, null, null);
            if (cursor == null)
                return;

            while (cursor.moveToNext()){
                // 空跑
            }

            IBinder binder = BinderCursor.getBinder(cursor);
            IMyApkInterface myapk = IMyApkInterface.Stub.asInterface(binder);
            PluginInfo info = myapk.install("/data/data/demo.apk");
            Toast.makeText(getContext(), info.toString(), Toast.LENGTH_SHORT).show();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }

    @OnClick(R.id.btn_loaddemo1)
    public void onClick4Loaddemo1() {
        Logger.e("FirstFragment", "onClick4Loaddemo1!");
        ComponentName cn = new ComponentName("mfplugindemo", "demo1.example.mfreplugin.mfplugindemo1.MainActivity");
        MyPlugin.startActivity(getContext(), new Intent().setComponent(cn));
    }

    @OnClick(R.id.btn_loadfanyue)
    public void onClick4LoadFanyue() {
        Logger.e("FirstFragment", "onClick4LoadFanyue!");

    }


}
