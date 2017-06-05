package com.tribalscale.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by patel on 6/4/2017.
 */

public class Person implements Parcelable{
    private Name name;
    private Picture picture;
    private Location location;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(getName(), 0);
        parcel.writeParcelable(getPicture(), 0);
        parcel.writeParcelable(getLocation(), 0);
    }

    private Person(Parcel in) {
        setName((Name) in.readParcelable(Name.class.getClassLoader()));
        setPicture((Picture) in.readParcelable(Picture.class.getClassLoader()));
        setLocation((Location) in.readParcelable(Location.class.getClassLoader()));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Person> CREATOR
            = new Parcelable.Creator<Person>() {

        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
