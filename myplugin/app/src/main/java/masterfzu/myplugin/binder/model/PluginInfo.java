package masterfzu.myplugin.binder.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by zhengsiyuan on 2018-04-24.
 */

public class PluginInfo implements Serializable, Parcelable, Cloneable {
    private String path;

    public PluginInfo() {

    }

    public PluginInfo(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "PluginInfo{" +
                "path='" + path + '\'' +
                '}';
    }

    PluginInfo(Parcel in) {
        path = in.readString();
    }

    public static final Creator<PluginInfo> CREATOR = new Creator<PluginInfo>() {
        @Override
        public PluginInfo createFromParcel(Parcel in) {
            return new PluginInfo(in);
        }

        @Override
        public PluginInfo[] newArray(int size) {
            return new PluginInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
    }
}
