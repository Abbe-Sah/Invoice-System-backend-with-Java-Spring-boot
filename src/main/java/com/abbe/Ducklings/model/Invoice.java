package com.abbe.Ducklings.model;

import java.util.ArrayList;
import java.util.List;

public class Invoice {

    private List<InvoicePost> itemList;
    private String owner;

    public Invoice(String owner) {
        this.owner = owner;
        this.itemList = new ArrayList<>();
    }

    public String getOwner() {
        return owner;
    }

    public List<InvoicePost> getInvoiceList() {
        return itemList;
    }
}
