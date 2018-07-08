package com.example.kakashi.musicalstructure;

public class musicDetails {
    private String mName;
    private String mLength;
    musicDetails(String name, String length){
        mName = name;
        mLength= length;
    }
    public String getNameOfSong(){
        return mName;
    }
    public String getLengthOfSong(){
        return mLength;
    }
}
