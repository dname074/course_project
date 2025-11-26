package manager;

import exception.FileWriteException;
import model.Order;
import storage.csv.InvoiceCsvManager;

import java.util.concurrent.ExecutorService;

public class OrderProcessor {
    private final InvoiceGenerator invoiceGenerator;
    private final InvoiceCsvManager csvManager;
    private final ExecutorService executorService;

    public OrderProcessor(InvoiceGenerator invoiceGenerator, InvoiceCsvManager csvManager, ExecutorService executor) {
        this.invoiceGenerator = invoiceGenerator;
        this.csvManager = csvManager;
        this.executorService = executor;
    }

    public void takeAnOrder(Order order) throws FileWriteException {
        executorService.submit(new OrderProcessingTask(csvManager, invoiceGenerator, order));
    }
}