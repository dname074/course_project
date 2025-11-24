package manager;

import model.Order;
import model.OrderRepository;

public class OrderProcessor {
    private static final OrderRepository orders = new OrderRepository();
    private static final InvoiceGenerator invoiceGenerator = new InvoiceGenerator();

    public static void takeAnOrder(Order order) {
        orders.addOrder(order);

        invoiceGenerator.generateInvoice(order);
    }
}