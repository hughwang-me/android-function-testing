package com.uwjx.function.litepad;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class Comment  extends LitePalSupport {

    private String content;

    private Date date;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
