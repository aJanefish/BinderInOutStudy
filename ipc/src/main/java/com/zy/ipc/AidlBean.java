package com.zy.ipc;

import android.os.Parcel;
import android.os.Parcelable;

public class AidlBean implements Parcelable {
    int id;
    String name;

    public AidlBean() {
        this(0, "DEFAULT");
    }

    public AidlBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "AidlBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}' + "hashCode:" + hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    protected AidlBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    //很重要
    //如果不添加将不能编译通过，在out ,inout 修饰的情况下
    public void readFromParcel(Parcel reply) {
        id = reply.readInt();
        name = reply.readString();
    }

    public static final Creator<AidlBean> CREATOR = new Creator<AidlBean>() {
        @Override
        public AidlBean createFromParcel(Parcel source) {
            return new AidlBean(source);
        }

        @Override
        public AidlBean[] newArray(int size) {
            return new AidlBean[size];
        }
    };
}
