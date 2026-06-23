package com.jdbc.sql.controller;

import com.jdbc.sql.entity.Invoice;
import com.jdbc.sql.entity.Service;
import com.jdbc.sql.repository.InvoiceRepo;
import com.jdbc.sql.repository.ServiceRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceServiceController {

    private final InvoiceRepo invoiceRepo;
    private final ServiceRepo serviceRepo;

    public InvoiceServiceController(InvoiceRepo invoiceRepo, ServiceRepo serviceRepo) {
        this.invoiceRepo = invoiceRepo;
        this.serviceRepo = serviceRepo;
    }

    @GetMapping("/get-all-invoices")
    public List<Invoice> getAllInvoices() {
        return invoiceRepo.findAll();
    }

    @PostMapping("/post-invoice")
    public Invoice postInvoice(@RequestBody Invoice invoice) {
        if(invoice.getServices()!=null) {
            invoice.getServices().stream().forEach(s -> {
                s.setInvoice(invoice);
            });
        }
        return invoiceRepo.save(invoice);
    }

    @PostMapping("/post-service")
    public Service postService(@RequestBody Service service) {
        return serviceRepo.save(service);
    }

    @GetMapping("/get-all-services")
    public List<Service> getAllServices() {
        return serviceRepo.findAll();
    }

}
