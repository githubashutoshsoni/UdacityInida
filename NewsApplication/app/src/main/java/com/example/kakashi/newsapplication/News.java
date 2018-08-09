package com.example.kakashi.newsapplication;

public class News {
    private String webTitle;
    private String webURL;
    private String SectionName;

    public News(String webTitle, String webURL, String sectionName) {
        this.webTitle = webTitle;
        this.webURL = webURL;
        SectionName = sectionName;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getWebURL() {
        return webURL;
    }

    public String getSectionName() {
        return SectionName;
    }
}
