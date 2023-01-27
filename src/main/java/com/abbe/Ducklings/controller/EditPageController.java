package com.abbe.Ducklings.controller;

import com.abbe.Ducklings.model.InvoicePost;
import com.abbe.Ducklings.service.InvoiceListService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/editPayment")
public class EditPageController {

    private InvoiceListService listService;

    public EditPageController() {
        listService = new InvoiceListService();
    }

    @GetMapping("/editPost/{id}")
    public String editPost(@PathVariable("id") int id, Model model, HttpSession session) {
        InvoicePost invoice = listService.getInvoice(id);
        model.addAttribute("invoice", invoice);
        model.addAttribute("user", session.getAttribute("username"));
        return "editPaymentPage";
    }


    @PostMapping("/updateMode")
    public String updatePost(@ModelAttribute InvoicePost invoice) {
        listService.updateInvoice(invoice);
        return "redirect:/invoice";
    }


}
