package manager;

import exception.FileWriteException;
import model.Invoice;
import model.Order;
import model.OrderRepository;
import storage.csv.InvoiceCsvManager;

public class OrderProcessor {
    private static final OrderRepository orders = new OrderRepository();
    private static final InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
    private static final InvoiceCsvManager csvManager = new InvoiceCsvManager();

    public static void takeAnOrder(Order order) throws FileWriteException {
        orders.addOrder(order);
        Invoice invoice = invoiceGenerator.generateInvoice(order);
        csvManager.saveInvoice(invoice);
    }
}