package masterfzu.myplugin.binder.provider;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by zhengsiyuan on 2018-04-25.
 */

public class BinderCursor extends MatrixCursor {
    static final String BINDER_KEY = "binder";
    Bundle mBundle = new Bundle();

    public BinderCursor(String[] columnNames, IBinder binder) {
        super(columnNames);

        if (binder != null) {
            BinderParcelable parcelable = new BinderParcelable(binder);
            mBundle.putParcelable(BINDER_KEY, parcelable);
        }
    }

    @Override
    public Bundle getExtras() {
        return mBundle;
    }

    public static Cursor queryBinderCursor(IBinder binder) {
        return new BinderCursor(new String[]{}, binder);
    }

    public static IBinder getBinder(Cursor cursor) {
        if (cursor == null)
            return null;

        Bundle extra = cursor.getExtras();
        extra.setClassLoader(BinderCursor.class.getClassLoader());
        Parcelable p = extra.getParcelable(BINDER_KEY);
        if (p == null || !(p instanceof BinderParcelable))
            return null;

        return ((BinderParcelable) p).mBinder;
    }



    static class BinderParcelable implements Parcelable {

        IBinder mBinder;

        BinderParcelable(IBinder binder) {
            this.mBinder = binder;
        }

        protected BinderParcelable(Parcel in) {
            this.mBinder = in.readStrongBinder();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeStrongBinder(mBinder);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<BinderParcelable> CREATOR = new Creator<BinderParcelable>() {
            @Override
            public BinderParcelable createFromParcel(Parcel in) {
                return new BinderParcelable(in);
            }

            @Override
            public BinderParcelable[] newArray(int size) {
                return new BinderParcelable[size];
            }
        };
    }

}
