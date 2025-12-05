package util;

import java.time.format.DateTimeFormatter;

public class Constants {
    public static final String INVALID_OPTION_MESS = "Podano nieistniejącą opcję";
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm X");
    public static final String CSV_FILE_PATH = "data/invoices.csv";
    public static final String ORDER_PROCESS_FAILURE_MESS = "Błąd przy przetwarzaniu zamówienia";
    public static final String PROMO_CODE_1 = "ABCDEFGH";
    public static final String PROMO_CODE_2 = "H5J4K3L2";
}
