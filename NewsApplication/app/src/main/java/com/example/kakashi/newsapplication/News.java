package com.example.kakashi.newsapplication;

public class News {
    private String webTitle;
    private String webURL;
    private String sectionName;
    private String authorName;
    private String mDate;

    public String getmDate() {
        return mDate;
    }

    public News(String webTitle, String webURL, String sectionName, String authorName, String date) {
        this.webTitle = webTitle;
        this.webURL = webURL;
        this.sectionName = sectionName;
        this.authorName= authorName;
        this.mDate= date;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getWebURL() {
        return webURL;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getAuthorName() {
        return authorName;
    }
}
