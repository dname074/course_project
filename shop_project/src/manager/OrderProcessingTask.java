package manager;

import model.Invoice;
import model.Order;
import storage.csv.InvoiceCsvManager;

public class OrderProcessingTask implements Runnable {
    private final InvoiceCsvManager csvManager;
    private final InvoiceGenerator invoiceGenerator;
    private final Order order;

    public OrderProcessingTask(InvoiceCsvManager csvManager, InvoiceGenerator invoiceGenerator, Order order) {
        this.csvManager = csvManager;
        this.invoiceGenerator = invoiceGenerator;
        this.order = order;
    }

    @Override
    public void run() {
        Invoice invoice = invoiceGenerator.generateInvoice(order);
        csvManager.saveInvoice(invoice);
    }
}
