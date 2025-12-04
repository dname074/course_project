package manager;

import model.Invoice;
import model.Order;
import storage.csv.InvoiceCsvManager;

import java.util.concurrent.CompletableFuture;

public class OrderProcessor {
    private final InvoiceGenerator invoiceGenerator;
    private final InvoiceCsvManager csvManager;

    public OrderProcessor(InvoiceGenerator invoiceGenerator, InvoiceCsvManager csvManager) {
        this.invoiceGenerator = invoiceGenerator;
        this.csvManager = csvManager;
    }

    public void takeAnOrder(Order order) {
        CompletableFuture.runAsync(() -> {
            Invoice invoice = invoiceGenerator.generateInvoice(order);
            csvManager.saveInvoice(invoice);
        }).exceptionally(e -> {
            System.err.println(e.getMessage());
            return null;
        });
    }
}