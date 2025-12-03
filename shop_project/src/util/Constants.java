package util;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Constants {
    public static final String INVALID_OPTION_MESS = "Podano nieistniejącą opcję";
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static final String CSV_FILE_PATH = "data/invoices.csv";
    public static final String ORDER_PROCESS_FAILURE = "Błąd przy przetwarzaniu zamówienia";
    public static final int MAX_ORDERS_PROCESSED = 4;
    public static final List<String> RAM_OPTIONS = List.of("8 GB", "16 GB");
    public static final List<String> DISK_OPTIONS = List.of("500 GB", "1000 GB");
    public static final List<String> COMPUTER_OS = List.of("Windows 10", "Windows 11");
    public static final List<String> SMARTPHONE_OS = List.of("Android 14", "Android 15");
    public static final List<String> BATTERY_CAPACITY_OPTIONS = List.of("3000 mAh", "5000 mAh");
}
