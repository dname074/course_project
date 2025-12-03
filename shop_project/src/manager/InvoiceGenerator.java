package manager;

import model.Invoice;
import model.Order;

public class InvoiceGenerator {
    public Invoice generateInvoice(Order order) {
        String firstName = order.getClient().getFirstName();
        String lastName = order.getClient().getLastName();
        String address = order.getClient().getAddress();
        return new Invoice(firstName, lastName, address, order.getCart().toString(), order.getOrderDate(), order.getTotalPrice());
    }
}
