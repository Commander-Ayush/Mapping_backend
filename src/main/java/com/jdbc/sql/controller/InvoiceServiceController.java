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

    @DeleteMapping("/delete-invoice/{invoice_id}")
    public String deleteInvoice(@PathVariable("invoice_id") Long invoice_id){
        invoiceRepo.deleteById(invoice_id);
        return "deleted";
    }

    @DeleteMapping("/test-remove-service/{invoice_id}/{service_id}")
    public Invoice testRemoveService(@PathVariable("invoice_id") Long invoiceId,
                                     @PathVariable("service_id") Integer serviceId) {

        Invoice invoice = invoiceRepo.findById(invoiceId).get();

        invoice.getServices().removeIf(s -> s.getServiceId() == serviceId);

        return invoiceRepo.save(invoice);
    }

}
