package com.abbe.Ducklings.model;

public class InvoicePost {

    public java.sql.Date pdate;

    private String title;

    private int postId;
    private String date;
    private String content;

    private String category;

    private int price;

    private String dateText;

    private String user;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public java.sql.Date getPdate() {
        return (java.sql.Date) pdate;
    }

    public void setPdate(java.sql.Date pdate) {
        this.pdate = pdate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public String getDateText() {
        dateText = " - DATE: ";
        return dateText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}