package com.sendo.suitmedia.models;

import com.google.gson.annotations.SerializedName;
import com.sendo.suitmedia.R;

public class Guest {
    @SerializedName("id")
    private int guestId;

    @SerializedName("name")
    private String guestName;

    @SerializedName("birthdate")
    private String guestBirthdate;
    private int guestImg;

    public Guest(int id, String name, String birthdate) {
        this.guestId = id;
        this.guestName = name;
        this.guestBirthdate = birthdate;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestBirthdate() {
        return guestBirthdate;
    }

    public void setGuestBirthdate(String guestBirthdate) {
        this.guestBirthdate = guestBirthdate;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getGuestImg() {
        return guestImg;
    }

    public void setGuestImg(int guestImg) {
        switch (guestImg) {
            case 1:
                this.guestImg = R.drawable.sample_1;
                break;
            case 2:
                this.guestImg = R.drawable.sample_2;
                break;
            case 3:
                this.guestImg = R.drawable.sample_3;
                break;
            case 4:
                this.guestImg = R.drawable.sample_4;
                break;
            case 5:
                this.guestImg = R.drawable.sample_5;
                break;
        }
    }
}
