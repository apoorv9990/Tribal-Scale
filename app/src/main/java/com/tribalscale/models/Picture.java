package com.tribalscale.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by patel on 6/4/2017.
 */

public class Picture implements Parcelable{
    private String large;
    private String medium;
    private String thumbnail;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getLarge());
        parcel.writeString(getMedium());
        parcel.writeString(getThumbnail());
    }

    private Picture(Parcel in) {
        setLarge(in.readString());
        setMedium(in.readString());
        setThumbnail(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Picture> CREATOR
            = new Parcelable.Creator<Picture>() {

        @Override
        public Picture createFromParcel(Parcel in) {
            return new Picture(in);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };
}
