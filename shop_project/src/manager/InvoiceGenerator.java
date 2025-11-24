package manager;

import model.Order;

public class InvoiceGenerator {
    public void generateInvoice(Order order) {
        System.out.println(order.toString());
    }
}
