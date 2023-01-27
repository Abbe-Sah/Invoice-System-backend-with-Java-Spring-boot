package com.abbe.Ducklings.controller;

import com.abbe.Ducklings.model.Invoice;
import com.abbe.Ducklings.model.InvoicePost;
import com.abbe.Ducklings.service.InvoiceListService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {
    private InvoiceListService listService;

    public InvoiceController() {
        listService = new InvoiceListService();
    }

    @GetMapping
    protected String showInvoiceList(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        Invoice userInvoice = listService.getInvoiceList(username);
        model.addAttribute("items", userInvoice.getInvoiceList());
        return "invoicePage";

    }

    @PostMapping
    public String addPost(HttpSession session, @ModelAttribute InvoicePost invoicePost) {
        String username = (String) session.getAttribute("username");
        listService.addInvoicePost(username, invoicePost);
        return "redirect:/invoice";
    }
}
