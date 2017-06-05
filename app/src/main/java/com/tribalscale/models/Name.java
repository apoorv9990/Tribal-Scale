package com.tribalscale.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by patel on 6/4/2017.
 */

public class Name implements Parcelable {

    private String title;
    private String first;
    private String last;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFullName() {
        return first + " " + last;
    }

    public String getFullNameWithTitle() {
        return title + " " + first + " " + last;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getTitle());
        parcel.writeString(getFirst());
        parcel.writeString(getLast());
    }

    private Name(Parcel in) {
        setTitle(in.readString());
        setFirst(in.readString());
        setLast(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Name> CREATOR
            = new Parcelable.Creator<Name>() {

        @Override
        public Name createFromParcel(Parcel in) {
            return new Name(in);
        }

        @Override
        public Name[] newArray(int size) {
            return new Name[size];
        }
    };
}
