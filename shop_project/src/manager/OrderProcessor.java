package manager;

import exception.FileWriteException;
import model.Invoice;
import model.Order;
import storage.csv.InvoiceCsvManager;

public class OrderProcessor {
    private static final InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
    private static final InvoiceCsvManager csvManager = new InvoiceCsvManager();

    public static void takeAnOrder(Order order) throws FileWriteException {
        Invoice invoice = invoiceGenerator.generateInvoice(order);
        csvManager.saveInvoice(invoice);
    }
}