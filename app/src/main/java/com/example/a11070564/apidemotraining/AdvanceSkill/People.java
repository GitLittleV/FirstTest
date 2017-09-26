package com.example.a11070564.apidemotraining.AdvanceSkill;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 11070564 on 2017/9/25.
 */

public class People implements Parcelable {
    private String name ;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }
    public static final Parcelable.Creator<People> CREATOR = new Parcelable.Creator<People>(){

        @Override
        public People createFromParcel(Parcel source) {

            People people = new People();
            people.name=source.readString();
            people.age=source.readInt();
            return people;
        }

        @Override
        public People[] newArray(int size) {
            return new People[size];
        }
    };

}
