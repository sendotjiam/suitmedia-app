package com.sendo.suitmedia.models;

import com.sendo.suitmedia.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Event implements Serializable {
    private static ArrayList<Event> eventList;

    private int eventImg;
    private String eventName;
    private String eventDate;

    public Event(int eventImg, String eventName, String eventDate) {
        this.eventImg = eventImg;
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public static ArrayList<Event> getListEventData() {
        eventList = new ArrayList<>();
        eventList.add(new Event(R.drawable.sample_1, "Programming is Fun","12 Agustus 2021"));
        eventList.add(new Event(R.drawable.sample_2, "Android is not Fun","13 Agustus 2021"));
        eventList.add(new Event(R.drawable.sample_3, "iOS is Fun","14 Agustus 2021"));
        eventList.add(new Event(R.drawable.sample_4, "Computer Science","15 Agustus 2021"));
        return eventList;
    }

    public int getEventImg() {
        return eventImg;
    }

    public void setEventImg(int eventImg) {
        this.eventImg = eventImg;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}
