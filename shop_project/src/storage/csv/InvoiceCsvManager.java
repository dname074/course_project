package storage.csv;

import exception.FileWriteException;
import model.Invoice;
import util.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/*
This class saves order invoices to csv file
- if file doesn't exist, program will create it
- method is synchronized, so only one thread can execute it at a time
*/
public class InvoiceCsvManager {
    private final Path filePath = Path.of(Constants.CSV_FILE_PATH);
    private final Object lockObject = new Object();

    public void saveInvoice(Invoice invoice) throws FileWriteException {
        synchronized (lockObject) {
            try {
                if (Files.notExists(filePath)) {
                    Files.createFile(filePath);
                }
                Files.writeString(filePath, invoice.toCsv(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new FileWriteException(Constants.ORDER_PROCESS_FAILURE);
            }
        }
    }
}
