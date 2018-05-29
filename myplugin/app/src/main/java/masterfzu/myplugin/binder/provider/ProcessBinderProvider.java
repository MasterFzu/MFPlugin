package masterfzu.myplugin.binder.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import masterfzu.myplugin.BuildConfig;
import masterfzu.myplugin.binder.IMyApkInterfaceIml;

/**
 * Created by zhengsiyuan on 2018-04-24.
 */
public class ProcessBinderProvider extends ContentProvider {
    private static final String PROCESS_BINDER_AUTHORITY = BuildConfig.APPLICATION_ID + ".provider.p1";
    public static final Uri URI = Uri.parse("content://" + PROCESS_BINDER_AUTHORITY + "/process");

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return BinderCursor.queryBinderCursor(new IMyApkInterfaceIml());
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
