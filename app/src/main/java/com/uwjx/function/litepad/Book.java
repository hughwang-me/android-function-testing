package com.uwjx.function.litepad;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class Book extends LitePalSupport {

    private String name;
    private double price;
    private String author;
    private Date publishDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
