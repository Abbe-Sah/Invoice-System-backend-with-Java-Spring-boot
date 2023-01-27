package com.abbe.Ducklings.service;

import com.abbe.Ducklings.model.Invoice;
import com.abbe.Ducklings.model.InvoicePost;
import com.abbe.Ducklings.repository.InvoiceListRepository;

public class InvoiceListService {

    private InvoiceListRepository listRepository;

    public InvoiceListService() {
        this.listRepository = new InvoiceListRepository();
    }


    public Invoice getInvoiceList(String username) {
        Invoice list = listRepository.getInvoiceList(username);
        if (list == null) {
            list = new Invoice(username);
        }
        return list;
    }

    public void addInvoicePost(String username, InvoicePost item) {
        listRepository.addPostItem(username, item);
    }

    public void updateInvoice(InvoicePost invoicepost) {
        listRepository.updateInvoice(invoicepost);
    }

    public InvoicePost getInvoice(int id) {
        return listRepository.getInvoice(id);
    }

}


