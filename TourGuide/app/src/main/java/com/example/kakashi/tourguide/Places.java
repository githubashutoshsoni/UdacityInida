package com.example.kakashi.tourguide;

public class Places {
    private String mName;
    private String mTime;
    private String mLocation;
    private int mImageResourceId= CHECK_IMAGE_STATUS;
    private static final int CHECK_IMAGE_STATUS=-1;
    private String mDetails;

    public String getmDetails() {
        return mDetails;
    }

    public Places(String mName, String mTime, String mLocation, int mImageResourceId, String mDetails) {
        this.mName = mName;
        this.mTime = mTime;
        this.mLocation = mLocation;
        this.mImageResourceId = mImageResourceId;
        this.mDetails = mDetails;
    }

    Places(String name, String time, String location, int imageResourceID){
        mName=name;
        mTime=time;
        mLocation=location;
        mImageResourceId = imageResourceID;
    }
    Places(String name, String time, String location){
        mName=name;
        mTime=time;
        mLocation=location;

    }
    public String returnName(){
        return mName;
    }
    public String returnTime(){
        return mTime;
    }
    public String returnLocation(){
        return mLocation;
    }
    public int returnImageResourceId(){
        return mImageResourceId;
    }
    public boolean hasImage(){
        return mImageResourceId!=CHECK_IMAGE_STATUS;
    }
}
