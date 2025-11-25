package util;

import java.time.format.DateTimeFormatter;

public class Constants {
    public static final String INVALID_OPTION_MESS = "Podano nieistniejącą opcję";
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static final String CSV_FILE_PATH = "data/invoices.csv";
}
