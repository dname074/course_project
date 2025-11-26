package storage.csv;

import exception.FileWriteException;
import model.Invoice;
import util.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

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
                throw new FileWriteException("Błąd przy zapisie zamówienia");
            }
        }
    }
}
